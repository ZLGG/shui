package com.gs.lshly.biz.support.merchant.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.merchant.entity.ShopCustomArea;
import com.gs.lshly.biz.support.merchant.repository.IShopCustomAreaRepository;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchShopCustomAreaService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopCustomAreaDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopCustomAreaQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopCustomAreaVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-10-30
*/
@Component
public class PCMerchShopCustomAreaServiceImpl implements IPCMerchShopCustomAreaService {

    @Autowired
    private IShopCustomAreaRepository repository;


    @Override
    public void editShopCustomArea(PCMerchShopCustomAreaDTO.ETO eto) {
        QueryWrapper<ShopCustomArea> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("shop_id",eto.getJwtShopId());
        ShopCustomArea shopCustomArea =  repository.getOne(queryWrapper);
        if(null == shopCustomArea){
            shopCustomArea = new ShopCustomArea();
            BeanCopyUtils.copyProperties(eto, shopCustomArea);
            repository.save(shopCustomArea);
        }else{
            BeanCopyUtils.copyProperties(eto, shopCustomArea);
            repository.updateById(shopCustomArea);
        }
    }

    @Override
    public PCMerchShopCustomAreaVO.DetailVO detailShopCustomArea(PCMerchShopCustomAreaQTO.QTO qto) {
        QueryWrapper<ShopCustomArea> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("shop_id", qto.getJwtShopId());
        ShopCustomArea shopCustomArea = repository.getOne(queryWrapper);
        if (null == shopCustomArea) {
            return null;
        }
        PCMerchShopCustomAreaVO.DetailVO detailVO = new PCMerchShopCustomAreaVO.DetailVO();
        BeanCopyUtils.copyProperties(shopCustomArea, detailVO);
        return detailVO;
    }

}
