package com.gs.lshly.middleware.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.utils.BU;

/**
 * 商家数据权限工具类
 * 1，增加查询条件
 * 2，判断数据是否有权限
 */
public class MerchantPermitUtil {

    public static  <T> boolean hasMerchantPermit(T merchantData, BaseDTO baseDTO) {
        if (baseDTO.getJwtMerchantId() != null && baseDTO.getJwtMerchantId().equals(BU.getValue(merchantData, "merchantId"))) {
            return true;
        }
        return false;
    }

    public static <T> boolean hasShopPermit(T shopData, BaseDTO baseDTO){
        if (baseDTO.getJwtShopId() != null && baseDTO.getJwtShopId().equals(BU.getValue(shopData, "shopId"))) {
            return true;
        }
        return false;
    }

    public static <T> void permitShop(QueryWrapper<T> boost, BaseDTO dto) {
        boost.eq("shop_id",dto.getJwtShopId());
    }

    public static <T> void permitMerchant(QueryWrapper<T> boost, BaseDTO dto) {
        boost.eq("merchant_id",dto.getJwtMerchantId());
    }

    public static <T> void permitMerchantAndShop(QueryWrapper<T> boost, BaseDTO dto) {
        permitShop(boost, dto);
        permitMerchant(boost, dto);
    }

    public static <T> void permitShopWithAlias(QueryWrapper<T> boost, BaseDTO dto, String alias) {
        boost.eq(alias + ".shop_id", dto.getJwtShopId());
    }

    public static <T> void permitMerchantWithAlias(QueryWrapper<T> boost, BaseDTO dto, String alias) {
        boost.eq(alias + ".merchant_id",dto.getJwtMerchantId());
    }

    public static <T> void permitMerchantAndShopWithAlias(QueryWrapper<T> boost, BaseDTO dto, String alias) {
        permitShopWithAlias(boost, dto, alias);
        permitMerchantWithAlias(boost, dto, alias);
    }

}
