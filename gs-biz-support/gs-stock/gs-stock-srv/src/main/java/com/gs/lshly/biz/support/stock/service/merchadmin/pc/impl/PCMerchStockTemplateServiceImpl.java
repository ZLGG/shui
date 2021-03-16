package com.gs.lshly.biz.support.stock.service.merchadmin.pc.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.stock.entity.*;
import com.gs.lshly.biz.support.stock.enums.StockTemplateRegionLeveEnum;
import com.gs.lshly.biz.support.stock.enums.StockTemplateRegionTpEnum;
import com.gs.lshly.biz.support.stock.enums.StockTemplateTypeEnum;
import com.gs.lshly.biz.support.stock.repository.*;
import com.gs.lshly.biz.support.stock.service.common.ICommonStockTemplateService;
import com.gs.lshly.biz.support.stock.service.merchadmin.pc.IPCMerchStockTemplateService;
import com.gs.lshly.common.enums.stock.StockTemplateStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateDTO;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateVO;
import com.gs.lshly.common.struct.merchadmin.pc.stock.qto.PCMerchStockTemplateQTO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lxus
 * @since 2020-10-24
 */
@Component
public class PCMerchStockTemplateServiceImpl implements IPCMerchStockTemplateService {

    @Autowired
    private IStockTemplateRepository repository;

    @Autowired
    private IStockTemplateQuotaRepository quotaRepository;

    @Autowired
    private IStockTemplateQuotaPriceRepository quotaPriceRepository;

    @Autowired
    private IStockTemplateRegionRepository regionRepository;

    @Autowired
    private IStockTemplateUnitRepository unitRepository;

    @Autowired
    private IStockTemplateFreeRepository freeRepository;

    @Autowired
    private ICommonStockTemplateService commonStockTemplateService;

    private StockTemplate uniqueCheck(CommonStockTemplateDTO.ETO dto) {
        StockTemplate stockTemplate = null;
        if (StringUtils.isBlank(dto.getId())) {
            stockTemplate = new StockTemplate();
        } else {
            stockTemplate = ownerCheck(dto, dto.getId());
        }
        QueryWrapper<StockTemplate> queryWrapper = MybatisPlusUtil.queryContainShopId(dto);
        queryWrapper.eq("template_name", dto.getTemplateName());
        if (StringUtils.isNotBlank(dto.getId())) {
            queryWrapper.ne("id", dto.getId());
        }
        Integer count = repository.count(queryWrapper);
        if (count > 0) {
            throw new BusinessException("该名称已被您使用");
        }
        BeanCopyUtils.copyProperties(dto, stockTemplate);
        return stockTemplate;
    }

    private StockTemplateRegion provinceRegion(String templateId, String childTemplateId, StockTemplateRegionTpEnum childTemplateType, CommonStockTemplateDTO.RegionDTO province) {
        StockTemplateRegion region = new StockTemplateRegion();
        region.setTemplateId(templateId);
        region.setChildTemplateId(childTemplateId);
        region.setChildTemplateType(childTemplateType.getCode());
        region.setProvince(province.getProvince());
        region.setProvinceId(province.getId());
        return region;
    }
    /**
     * 保存运费计算条件所应用的区域
     * @param regions
     * @param templateId
     * @param childTemplateId
     * @param childTemplateType
     */
    private void saveRegion(List<CommonStockTemplateDTO.RegionDTO> regions, String templateId, String childTemplateId, StockTemplateRegionTpEnum childTemplateType) {
        if (ObjectUtils.isEmpty(regions)) {
            return;
        }
        List<StockTemplateRegion> stockTemplateRegionsList = new ArrayList<>();
        for (CommonStockTemplateDTO.RegionDTO regionDTO : regions) {
            if (ObjectUtils.isNotEmpty(regionDTO.getRegionCDTO())) {
                //地区在市按市设置
                for (CommonStockTemplateDTO.RegionCDTO cityDto : regionDTO.getRegionCDTO()) {
                    StockTemplateRegion region = provinceRegion(templateId, childTemplateId, childTemplateType, regionDTO);
                    region.setCityId(cityDto.getId());
                    region.setCity(cityDto.getCity());
                    region.setRegionLeve(StockTemplateRegionLeveEnum.市.getCode());
                    stockTemplateRegionsList.add(region);
                }
            } else {
                //地区在省按省设置
                StockTemplateRegion region = provinceRegion(templateId, childTemplateId, childTemplateType, regionDTO);
                region.setRegionLeve(StockTemplateRegionLeveEnum.省.getCode());
                stockTemplateRegionsList.add(region);
            }
        }
        regionRepository.saveBatch(stockTemplateRegionsList);
    }

    /**
     * 保存包邮条件
     * @param frees
     * @param templateId
     */
    private void saveFree(List<CommonStockTemplateDTO.FreeDTO> frees, String templateId) {
        if (ObjectUtils.isNotEmpty(frees)) {
            for (CommonStockTemplateDTO.FreeDTO freeDTO : frees) {
                StockTemplateFree stockTemplateFree = new StockTemplateFree();
                stockTemplateFree.setIsDefault(freeDTO.getIsDefault());
                stockTemplateFree.setTemplateId(templateId);
                stockTemplateFree.setMathType(freeDTO.getMathType());
                stockTemplateFree.setQuantity(freeDTO.getUnitValue());
                stockTemplateFree.setPrice(freeDTO.getPriceValue());
                freeRepository.save(stockTemplateFree);
                //2.1包邮条件对应的应用区域
                saveRegion(freeDTO.getRegionDTOList(), templateId, stockTemplateFree.getId(), StockTemplateRegionTpEnum.包邮条件);
            }
        }
    }

    private void savePrice(List<CommonStockTemplateDTO.QuotaPriceDTO> prices, StockTemplateQuota stockTemplateQuota) {
        if (ObjectUtils.isEmpty(prices)) {
            throw new BusinessException("金额范围为明确阶梯运费价格");
        }
        List<StockTemplateQuotaPrice> quotaPrices = new ArrayList<>();
        for (CommonStockTemplateDTO.QuotaPriceDTO priceDTO : prices) {
            StockTemplateQuotaPrice price = new StockTemplateQuotaPrice();
            BeanCopyUtils.copyProperties(priceDTO, price);
            price.setTemplateId(stockTemplateQuota.getTemplateId()).setQuotaId(stockTemplateQuota.getId());
            quotaPrices.add(price);
        }
        quotaPriceRepository.saveBatch(quotaPrices);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editStockTemplate(CommonStockTemplateDTO.ETO dto) {
        //新增，或者修改
        StockTemplate stockTemplate = uniqueCheck(dto);
        //如果是修改，则先清理所有子表数据
        if (StringUtils.isNotBlank(stockTemplate.getId())) {
            clearChildRecord(stockTemplate.getId());
        }
        //保存主表的信息
        repository.saveOrUpdate(stockTemplate);
        //完全买家承担
        if (StockTemplateTypeEnum.卖家承担.getCode().equals(dto.getTemplateType())) {
            return;
        }
        //1，计件或计重
        if (StockTemplateTypeEnum.计件.getCode().equals(dto.getTemplateType()) || StockTemplateTypeEnum.计重.getCode().equals(dto.getTemplateType())) {
            if (ObjectUtils.isEmpty(dto.getUnitDTOList())) {
                throw new BusinessException(StockTemplateTypeEnum.text(dto.getTemplateType()) + "-条件未设置");
            }
            //1.1，首件（重），续件（重）主记录
            for (CommonStockTemplateDTO.UnitDTO unitDTO : dto.getUnitDTOList()) {
                StockTemplateUnit stockTemplateUnit = new StockTemplateUnit();
                BeanCopyUtils.copyProperties(unitDTO, stockTemplateUnit);
                stockTemplateUnit.setTemplateId(stockTemplate.getId());
                unitRepository.save(stockTemplateUnit);
                //1.1.1，主记录所对应的应用区域
                saveRegion(unitDTO.getRegionDTOList(), stockTemplate.getId(), stockTemplateUnit.getId(), StockTemplateRegionTpEnum.计重计件);
            }
            //1.2，包邮条件
            saveFree(dto.getFreeDTOList(), stockTemplate.getId());
        }
        //2，按金额范围
        if (StockTemplateTypeEnum.金额范围.getCode().equals(dto.getTemplateType())) {
            if (ObjectUtils.isEmpty(dto.getQuotaDTOList())) {
                throw new BusinessException("金额范围-条件未设置");
            }
            //2.1，金额范围 主记录
            for (CommonStockTemplateDTO.QuotaDTO quotaDTO : dto.getQuotaDTOList()) {
                StockTemplateQuota stockTemplateQuota = new StockTemplateQuota();
                stockTemplateQuota.setTemplateId(stockTemplate.getId());
                stockTemplateQuota.setIsDefault(quotaDTO.getIsDefault());
                quotaRepository.save(stockTemplateQuota);
                //2.1.1，主记录所对应的应用区域
                saveRegion(quotaDTO.getRegionDTOList(), stockTemplate.getId(), stockTemplateQuota.getId(), StockTemplateRegionTpEnum.金额范围);
                //2.1.2，主记录所对应的价格区间
                savePrice(quotaDTO.getPriceDTOList(), stockTemplateQuota);
            }
            //2.2，包邮条件
            saveFree(dto.getFreeDTOList(), stockTemplate.getId());
        }
    }

    @Override
    public CommonStockTemplateVO.ListDetailVO stockTemplateDetail(CommonStockTemplateDTO.IdDTO dto) {
        return commonStockTemplateService.getDetail(dto.getId());
    }

    @Override
    public List<CommonStockTemplateVO.IdNameVO> idNames(BaseDTO dto) {
        QueryWrapper<StockTemplate> queryWrapper = MybatisPlusUtil.queryContainShopId(dto);
        queryWrapper.select("id", "template_name").eq("state", StockTemplateStateEnum.启用.getCode());
        List<StockTemplate> stockTemplateList = repository.list(queryWrapper);
        List<CommonStockTemplateVO.IdNameVO> templateList = new ArrayList<>();
        for (StockTemplate item : stockTemplateList) {
            templateList.add(new CommonStockTemplateVO.IdNameVO().setTemplateId(item.getId()).setTemplateName(item.getTemplateName()));
        }
        return templateList;
    }

    @Override
    public CommonStockTemplateVO.IdNameVO getIdNameVo(String shopId, String templateName) {
        QueryWrapper<StockTemplate> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.select("id", "template_name").eq("state", StockTemplateStateEnum.启用.getCode())
                .eq("template_name",templateName)
                .eq("shop_id",shopId);
        StockTemplate stockTemplate = repository.getOne(queryWrapper);
        if (ObjectUtils.isEmpty(stockTemplate)){
            return null;
        }
        CommonStockTemplateVO.IdNameVO nameVO = new CommonStockTemplateVO.IdNameVO();
        nameVO.setTemplateId(stockTemplate.getId());
        nameVO.setTemplateName(stockTemplate.getTemplateName());
        return nameVO;
    }

    @Override
    public int checkTemplate(String shopId, String templateName) {
        QueryWrapper<StockTemplate> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("template_name",templateName)
                .eq("shop_id",shopId)
                .eq("state",StockTemplateStateEnum.启用.getCode());
        int count = repository.count(queryWrapper);
        return count;
    }

    @Override
    public List<CommonStockTemplateVO.ListDetailVO> detailList(PCMerchStockTemplateQTO.QTO qto) {
        if (qto.getTemplateType() == null) {
            throw new BusinessException("请指定计费类型");
        }
        QueryWrapper<StockTemplate> queryWrapper = MybatisPlusUtil.queryContainShopId(qto);
        queryWrapper.eq("template_type", qto.getTemplateType());
        List<StockTemplate> stockTemplateList = repository.list(queryWrapper);

        List<CommonStockTemplateVO.ListDetailVO> templateList = new ArrayList<>();

        for (StockTemplate item : stockTemplateList) {
            CommonStockTemplateVO.ListDetailVO listDetailVO = new CommonStockTemplateVO.ListDetailVO();
            BeanCopyUtils.copyProperties(item, listDetailVO);
            commonStockTemplateService.fillComplexTypeTemplate(listDetailVO);
            templateList.add(listDetailVO);
        }

        return templateList;
    }

    @Override
    public void delete(CommonStockTemplateDTO.IdDTO idDTO) {
        ownerCheck(idDTO, idDTO.getId());
        //使用中的的物流模板不能直接删除
        Integer count = commonStockTemplateService.usedCount(idDTO);
        if (count!=null && count > 0) {
            throw new BusinessException("物流模板使用中，不能直接删除");
        }
        repository.removeById(idDTO.getId());
        clearChildRecord(idDTO.getId());
    }

    private StockTemplate ownerCheck(BaseDTO dto, String stockTemplateId) {
        StockTemplate stockTemplate = repository.getById(stockTemplateId);
        if (ObjectUtils.isEmpty(stockTemplate)) {
            throw new BusinessException("数据未找到");
        }
        if (!stockTemplate.getShopId().equals(dto.getJwtShopId())) {
            throw new BusinessException("越权访问");
        }
        return stockTemplate;
    }

    private void clearChildRecord(String stockTemplateId) {
        //清理子表-计件计重
        unitRepository.remove(new QueryWrapper<StockTemplateUnit>().eq("template_id", stockTemplateId));
        //清理子表-包邮条件
        freeRepository.remove(new QueryWrapper<StockTemplateFree>().eq("template_id", stockTemplateId));
        //清理子表-区域设置
        regionRepository.remove(new QueryWrapper<StockTemplateRegion>().eq("template_id", stockTemplateId));
        //清理子表-金额范围设置
        quotaRepository.remove(new QueryWrapper<StockTemplateQuota>().eq("template_id", stockTemplateId));
        //清理子表-金额范围价格设置
        quotaPriceRepository.remove(new QueryWrapper<StockTemplateQuotaPrice>().eq("template_id", stockTemplateId));
    }
}
