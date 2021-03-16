package com.gs.lshly.common.struct.fy.wallet.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zhaoqiang
 * @Description
 * @date 2021/1/4 下午2:29
 */
@Data
@Accessors(chain = true)
public abstract class FyWalletUserDTO implements Serializable {

    @Data
    @ApiModel("FyWalletUserDTO.DTO")
    @Accessors(chain = true)
    public static class ETO implements Serializable {

        private String openId;

        private String phone;

        private String accountNo;

        private String fuMerchantNum;

        private String sign;

        private String signStr;

    }

}
