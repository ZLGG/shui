package com.gs.lshly.biz.support.merchant.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.merchant.entity.ShopSignboard;
import com.gs.lshly.biz.support.merchant.repository.IShopSignboardRepository;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchShopSignboardService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopSignboardDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopSignboardQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopSignboardVO;
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
public class PCMerchShopSignboardServiceImpl implements IPCMerchShopSignboardService {

    @Autowired
    private IShopSignboardRepository repository;


    @Override
    public PCMerchShopSignboardVO.DetailVO detailSignboard(PCMerchShopSignboardQTO.QTO qto) {
        QueryWrapper<ShopSignboard> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("shop_id",qto.getJwtShopId());
        ShopSignboard shopSignboard = repository.getOne(queryWrapper);
        if(null == shopSignboard){
            return null;
        }
        PCMerchShopSignboardVO.DetailVO detailVO = new PCMerchShopSignboardVO.DetailVO();
        BeanCopyUtils.copyProperties(shopSignboard,detailVO);
        return detailVO;
    }

    @Override
    public void editShopSignboard(PCMerchShopSignboardDTO.ETO eto) {
        QueryWrapper<ShopSignboard> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("shop_id",eto.getJwtShopId());
        ShopSignboard  shopSignboard = repository.getOne(queryWrapper);
        if(null == shopSignboard){
            shopSignboard = new ShopSignboard();
            BeanCopyUtils.copyProperties(eto, shopSignboard);
            shopSignboard.setShopId(eto.getJwtShopId());
            shopSignboard.setMerchantId(eto.getJwtMerchantId());
            repository.save(shopSignboard);
        }else {
            BeanCopyUtils.copyProperties(eto, shopSignboard);

            repository.updateById(shopSignboard);
        }
    }


}
