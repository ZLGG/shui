package com.gs.lshly.biz.support.merchant.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.merchant.entity.ShopAdvert;
import com.gs.lshly.biz.support.merchant.repository.IShopAdvertRepository;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchShopAdvertService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopAdvertDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopAdvertQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopAdvertVO;
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
public class PCMerchShopAdvertServiceImpl implements IPCMerchShopAdvertService {

    @Autowired
    private IShopAdvertRepository repository;

    @Override
    public void editShopAdvert(PCMerchShopAdvertDTO.ETO eto) {
        QueryWrapper<ShopAdvert> shopAdvertQueryWrapper = MybatisPlusUtil.query();
        shopAdvertQueryWrapper.eq("shop_id",eto.getJwtShopId());
        repository.remove(shopAdvertQueryWrapper);
        ShopAdvert shopAdvert = new ShopAdvert();
        BeanCopyUtils.copyProperties(eto, shopAdvert);
        repository.save(shopAdvert);
    }

    @Override
    public PCMerchShopAdvertVO.DetailVO detailShopAdvert(PCMerchShopAdvertQTO.QTO qto) {
        QueryWrapper<ShopAdvert>  queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("shop_id",qto.getJwtShopId());
        queryWrapper.eq("advert_type",qto.getAdvertType());
        ShopAdvert shopAdvert = repository.getOne(queryWrapper);
        if(null == shopAdvert){
            return null;
        }
        PCMerchShopAdvertVO.DetailVO detailVo = new PCMerchShopAdvertVO.DetailVO();
        BeanCopyUtils.copyProperties(shopAdvert, detailVo);
        return detailVo;
    }

}
