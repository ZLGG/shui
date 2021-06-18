package com.gs.lshly.biz.support.stock.service.merchadmin.pc.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gs.lshly.biz.support.stock.entity.Template;
import com.gs.lshly.biz.support.stock.entity.TemplatePrice;
import com.gs.lshly.biz.support.stock.entity.TemplateRegion;
import com.gs.lshly.biz.support.stock.repository.ITemplatePriceRepository;
import com.gs.lshly.biz.support.stock.repository.ITemplateRegionRepository;
import com.gs.lshly.biz.support.stock.repository.ITemplateRepository;
import com.gs.lshly.biz.support.stock.service.merchadmin.pc.IPCMerchTemplateService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.common.stock.CommonTemplateDTO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
}
