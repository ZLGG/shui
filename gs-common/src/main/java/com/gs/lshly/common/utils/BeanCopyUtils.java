package com.gs.lshly.common.utils;

import com.gs.lshly.common.enums.UserTypeEnum;
import com.gs.lshly.common.struct.BaseDTO;
import org.springframework.beans.BeanUtils;

public class BeanCopyUtils {

    public static void copyProperties(Object source, Object target) {
        if (source == null) {
            return;
        }
        BeanUtils.copyProperties(source, target);
        if (source instanceof BaseDTO) {
            BaseDTO baseDTO = (BaseDTO) source;

            if (isMerchantAccount(baseDTO)) {
                BU.setValue(target, "shopId", baseDTO.getJwtShopId());
                BU.setValue(target, "merchantId", baseDTO.getJwtMerchantId());
            }

            if (isUserAccount(baseDTO)) {
                BU.setValue(target, "wxOpenid", baseDTO.getJwtWxOpenid());
            }
        }
    }

    private static boolean isUserAccount(BaseDTO baseDTO) {
        return baseDTO.getJwtUserType() != null
                && (baseDTO.getJwtUserType().equals(UserTypeEnum._2B用户.getCode())
                || baseDTO.getJwtUserType().equals(UserTypeEnum._2C用户.getCode()));
    }
    private static boolean isMerchantAccount(BaseDTO baseDTO) {
        return baseDTO.getJwtUserType() != null
                &&
                (
                        baseDTO.getJwtUserType().equals(UserTypeEnum._2B商家主账号.getCode())
                            || baseDTO.getJwtUserType().equals(UserTypeEnum._2B商家子账号.getCode())
                            || baseDTO.getJwtUserType().equals(UserTypeEnum._2C商家主账号.getCode())
                            || baseDTO.getJwtUserType().equals(UserTypeEnum._2C商家子账号.getCode())
                );
    }

}
