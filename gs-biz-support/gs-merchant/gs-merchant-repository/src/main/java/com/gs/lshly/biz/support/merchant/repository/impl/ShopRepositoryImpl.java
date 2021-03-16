package com.gs.lshly.biz.support.merchant.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.merchant.entity.Shop;
import com.gs.lshly.biz.support.merchant.mapper.ShopMapper;
import com.gs.lshly.biz.support.merchant.repository.IShopRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商家店铺 服务实现类
 * </p>
 *
 * @author xxfc
 * @since 2020-10-13
 */
@Service
public class ShopRepositoryImpl extends ServiceImpl<ShopMapper, Shop> implements IShopRepository {

    @Override
    public boolean checkIdExist(String id) {
        Shop entity =  this.getById(id);
        if(ObjectUtils.isNull(entity)){
            return false;
        }
        return true;
    }

    @Override
    public ShopMapper getBaseMapper() {
        return super.getBaseMapper();
    }

    @Override
    public String selectMerchantId(String shopId, BaseDTO baseDTO) {
        Shop  shop =  this.getById(shopId);
        if(ObjectUtils.isNotNull(shop)){
            return shop.getMerchantId();
        }
        return null;
    }

    @Override
    public List<Shop> selectShopByMerchantId(String merchantId, BaseDTO baseDTO) {

        QueryWrapper<Shop> wrapperBoost = MybatisPlusUtil.query();

        return null;
    }
}
