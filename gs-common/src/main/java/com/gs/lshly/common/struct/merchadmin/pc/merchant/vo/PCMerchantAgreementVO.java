package com.gs.lshly.common.struct.merchadmin.pc.merchant.vo;

import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantAgreementVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author Starry
 * @Date 11:10 2021/1/5
 */
public abstract class PCMerchantAgreementVO implements Serializable {

    @Data
    @ApiModel("PCMerchantAgreementVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("协议内容")
        private String content;




    }

    @Data
    @ApiModel("PCMerchantAgreementVO.DetailVO")
    public static class DetailVO extends MerchantAgreementVO.ListVO {

    }
}
