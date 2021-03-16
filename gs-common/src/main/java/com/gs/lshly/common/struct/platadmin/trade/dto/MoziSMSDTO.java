package com.gs.lshly.common.struct.platadmin.trade.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
* @author zst
* @since 2021-2-26
*/
public abstract class MoziSMSDTO implements Serializable {


    @Data
    @ApiModel("MoziSMSDTO.SendContentDTO")
    public static class SendContentDTO extends BaseDTO{

        @ApiModelProperty("商户订单号")
        private String merchantOrderId;

        @ApiModelProperty("已审核短信签名")
        private String sign;

        @ApiModelProperty("短信内容")
        private String content;

        @ApiModelProperty("支持11位手机号,一次最多100个手机号，使用英文,分隔")
        private String mobiles;
    }


    @Data
    @ApiModel("MoziSMSDTO.TemplateDTO")
    public static class TemplateDTO extends BaseDTO{

        @ApiModelProperty("商户订单号")
        private String merchantOrderId;

        @ApiModelProperty("模板 id")
        private String tplId;

        @ApiModelProperty("参数健值对")
        private Map<String,Object> parameter;

        @ApiModelProperty("支持11位手机号,一次最多100个手机号，使用英文,分隔")
        private String mobiles;
    }


    @Data
    @ApiModel("MoziSMSDTO.BalanceQueryDTO")
    public static class BalanceQueryDTO extends BaseDTO{

        @ApiModelProperty("对应商户id")
        private String merchantId;
    }

}
