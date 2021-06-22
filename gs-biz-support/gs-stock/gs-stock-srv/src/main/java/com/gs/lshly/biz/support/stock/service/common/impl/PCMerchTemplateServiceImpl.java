package com.gs.lshly.biz.support.stock.service.common.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.stock.entity.Template;
import com.gs.lshly.biz.support.stock.entity.TemplatePrice;
import com.gs.lshly.biz.support.stock.entity.TemplateRegion;
import com.gs.lshly.biz.support.stock.mapper.TemplateMapper;
import com.gs.lshly.biz.support.stock.repository.ITemplatePriceRepository;
import com.gs.lshly.biz.support.stock.repository.ITemplateRegionRepository;
import com.gs.lshly.biz.support.stock.repository.ITemplateRepository;
import com.gs.lshly.biz.support.stock.service.common.IPCMerchTemplateService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.stock.CommonTemplateDTO;
import com.gs.lshly.common.struct.common.stock.TemplateVO;
import com.gs.lshly.common.struct.common.qto.TemplateQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;
import com.gs.lshly.common.utils.BeanUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 运费模板service
 *
 * @author chenyang
 */
@Component
public class PCMerchTemplateServiceImpl implements IPCMerchTemplateService {

    @Autowired
    private ITemplateRepository repository;

    @Autowired
    private ITemplatePriceRepository priceRepository;

    @Autowired
    private ITemplateRegionRepository regionRepository;

    @Autowired
    private TemplateMapper templateMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addTemplate(CommonTemplateDTO.AddETO eto) {
        if (ObjectUtil.isEmpty(eto)) {
            throw new BusinessException("请输入运费模版信息");
        }
        if(ObjectUtil.isEmpty(eto.getTemplateName())){
            throw new BusinessException("请输入运费模版名称");
        }
        if(ObjectUtil.isEmpty(eto.getPriceDTOList())){
            throw new BusinessException("请输入运费区间");
        }

        Template template = new Template();
        template.setTemplateName(eto.getTemplateName());
        template.setShopId(eto.getJwtShopId());
        template.setMerchantId(eto.getJwtMerchantId());
        template.setCdate(new Date());
        template.setUdate(new Date());
        template.setFlag(false);
        boolean result = repository.save(template);
        String templateId = template.getId();

        TemplatePrice templatePrice;
        for (CommonTemplateDTO.PriceDTO priceDTO : eto.getPriceDTOList()) {
            templatePrice = new TemplatePrice();
            templatePrice.setAddress(priceDTO.getAddress());
            templatePrice.setTemplateId(templateId);
            templatePrice.setPrice(priceDTO.getPrice());
            templatePrice.setCdate(new Date());
            templatePrice.setUdate(new Date());
            templatePrice.setFlag(false);

            priceRepository.save(templatePrice);
            String templatePriceId = templatePrice.getId();

            if(ObjectUtil.isNotEmpty(priceDTO.getRegionDTOList())){
                List<TemplateRegion> regionList = new ArrayList<>();
                TemplateRegion templateRegion;
                for (CommonTemplateDTO.RegionDTO regionDTO : priceDTO.getRegionDTOList()) {
                    templateRegion = new TemplateRegion();
                    templateRegion.setTemplateId(templateId);
                    templateRegion.setTemplatePrcieId(templatePriceId);
                    templateRegion.setRegionId(regionDTO.getRegionId());
                    templateRegion.setRegionName(regionDTO.getRegionName());
                    templateRegion.setRegionLeve(regionDTO.getRegionLevel());
                    templateRegion.setCdate(new Date());
                    templateRegion.setUdate(new Date());
                    templateRegion.setFlag(false);

                    regionList.add(templateRegion);
                }
                if(ObjectUtil.isNotEmpty(regionList)){
                    regionRepository.saveBatch(regionList);
                }
            }
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean editTemplate(CommonTemplateDTO.UpdateETO eto) {
        if (ObjectUtil.isEmpty(eto)) {
            throw new BusinessException("请输入运费模版信息");
        }
        if(ObjectUtil.isEmpty(eto.getId())){
            throw new BusinessException("缺少参数id");
        }
        if(ObjectUtil.isEmpty(eto.getTemplateName())){
            throw new BusinessException("请输入运费模版名称");
        }
        if(ObjectUtil.isEmpty(eto.getPriceDTOList())){
            throw new BusinessException("请输入运费区间");
        }

        UpdateWrapper<Template> updateWrapper = MybatisPlusUtil.update();
        updateWrapper.eq("id",eto.getId());
        Template template = new Template();
        template.setTemplateName(eto.getTemplateName());
        template.setShopId(eto.getJwtShopId());
        template.setMerchantId(eto.getJwtMerchantId());
        template.setUdate(new Date());
        boolean result = repository.update(template,updateWrapper);

        QueryWrapper<TemplatePrice> priceWrapper = MybatisPlusUtil.query();
        priceWrapper.eq("template_id",eto.getId());
        priceRepository.remove(priceWrapper);

        QueryWrapper<TemplateRegion> regionWrapper = MybatisPlusUtil.query();
        regionWrapper.eq("template_id",eto.getId());
        regionRepository.remove(regionWrapper);

        TemplatePrice templatePrice;
        for (CommonTemplateDTO.PriceDTO priceDTO : eto.getPriceDTOList()) {
            templatePrice = new TemplatePrice();
            templatePrice.setAddress(priceDTO.getAddress());
            templatePrice.setTemplateId(eto.getId());
            templatePrice.setPrice(priceDTO.getPrice());
            templatePrice.setCdate(new Date());
            templatePrice.setUdate(new Date());
            templatePrice.setFlag(false);

            priceRepository.save(templatePrice);
            String templatePriceId = templatePrice.getId();

            if(ObjectUtil.isNotEmpty(priceDTO.getRegionDTOList())){
                List<TemplateRegion> regionList = new ArrayList<>();
                TemplateRegion templateRegion;
                for (CommonTemplateDTO.RegionDTO regionDTO : priceDTO.getRegionDTOList()) {
                    templateRegion = new TemplateRegion();
                    templateRegion.setTemplateId(eto.getId());
                    templateRegion.setTemplatePrcieId(templatePriceId);
                    templateRegion.setRegionId(regionDTO.getRegionId());
                    templateRegion.setRegionName(regionDTO.getRegionName());
                    templateRegion.setRegionLeve(regionDTO.getRegionLevel());
                    templateRegion.setCdate(new Date());
                    templateRegion.setUdate(new Date());
                    templateRegion.setFlag(false);

                    regionList.add(templateRegion);
                }
                if(ObjectUtil.isNotEmpty(regionList)){
                    regionRepository.saveBatch(regionList);
                }
            }
        }
        return result;
    }

    @Override
    public TemplateVO.DetailVO detail(String id) {

        TemplateVO.DetailVO detailVO = new TemplateVO.DetailVO();
        Template template = repository.getById(id);
        if(ObjectUtil.isEmpty(template)){
            throw new BusinessException("运费模板不存在");
        }
        detailVO.setId(template.getId());
        detailVO.setTemplateName(template.getTemplateName());


        QueryWrapper<TemplatePrice> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("template_id",id);
        List<TemplatePrice> priceList = priceRepository.getBaseMapper().selectList(queryWrapper);
        if(ObjectUtil.isNotEmpty(priceList)){
            List<CommonTemplateDTO.PriceDTO> priceDTOList = new ArrayList<>();
            CommonTemplateDTO.PriceDTO priceDTO;
            QueryWrapper<TemplateRegion> regionWrapper;
            for (TemplatePrice price : priceList) {
                priceDTO = new CommonTemplateDTO.PriceDTO();
                priceDTO.setAddress(price.getAddress());
                priceDTO.setPrice(price.getPrice());

                regionWrapper = MybatisPlusUtil.query();
                regionWrapper.eq("price_id",price.getId());
                List<TemplateRegion> regionList = regionRepository.getBaseMapper().selectList(regionWrapper);
                List<CommonTemplateDTO.RegionDTO> regionDTOS = BeanUtils.copyList(CommonTemplateDTO.RegionDTO.class,regionList);
                priceDTO.setRegionDTOList(regionDTOS);

                priceDTOList.add(priceDTO);
            }
            detailVO.setPriceDTOList(priceDTOList);
        }
        return detailVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(String id) {
        int result = repository.getBaseMapper().deleteById(id);
        QueryWrapper<TemplatePrice> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("template_id",id);
        priceRepository.getBaseMapper().delete(queryWrapper);
        QueryWrapper<TemplateRegion> regionWrapper = MybatisPlusUtil.query();
        regionWrapper.eq("template_id",id);
        regionRepository.getBaseMapper().delete(regionWrapper);
        if(result > 0 ){
            return true;
        }
        return false;
    }

    @Override
    public PageData<TemplateVO.ListVO> list(TemplateQTO.QTO qto) {

        IPage<TemplateVO.ListVO> page = MybatisPlusUtil.pager(qto);

        QueryWrapper<TemplateQTO.QTO> qw = MybatisPlusUtil.query();
        qw.eq("shop_id",qto.getJwtShopId());
        qw.eq("merchant_id",qto.getJwtMerchantId());
        IPage<TemplateVO.ListVO> templatePage = templateMapper.list(page,qw);
        if(ObjectUtil.isNotEmpty(templatePage)){
            for (TemplateVO.ListVO templateListRecord : templatePage.getRecords()) {
                QueryWrapper<TemplatePrice> queryWrapper = MybatisPlusUtil.query();
                queryWrapper.eq("template_id",templateListRecord.getId());
                List<TemplatePrice> priceList = priceRepository.getBaseMapper().selectList(queryWrapper);
                if(ObjectUtil.isNotEmpty(priceList)){
                    List<CommonTemplateDTO.PriceDTO> priceDTOList = new ArrayList<>();
                    CommonTemplateDTO.PriceDTO priceDTO;
                    QueryWrapper<TemplateRegion> regionWrapper;
                    for (TemplatePrice price : priceList) {
                        priceDTO = new CommonTemplateDTO.PriceDTO();
                        priceDTO.setAddress(price.getAddress());
                        priceDTO.setPrice(price.getPrice());

                        regionWrapper = MybatisPlusUtil.query();
                        regionWrapper.eq("price_id",price.getId());
                        List<TemplateRegion> regionList = regionRepository.getBaseMapper().selectList(regionWrapper);
                        List<CommonTemplateDTO.RegionDTO> regionDTOS = BeanUtils.copyList(CommonTemplateDTO.RegionDTO.class,regionList);
                        priceDTO.setRegionDTOList(regionDTOS);

                        priceDTOList.add(priceDTO);
                    }
                    templateListRecord.setPriceDTOList(priceDTOList);
                }
            }
        }
        return MybatisPlusUtil.toPageData(qto, TemplateVO.ListVO.class, templatePage);
    }
}
