package com.gs.lshly.common.struct.platadmin.foundation.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author 陈奇
* @since 2020-10-12
*/
public abstract class SettingsPayMethodVO implements Serializable {

    @Data
    @ApiModel("SettingsPayMethodVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("支付方式名称")
        private String name;


        @ApiModelProperty("支付方式key")
        private String payKey;


        @ApiModelProperty("商户号")
        private String merchantNumber;


        @ApiModelProperty("签名证书")
        private String signatureCertificate;


        @ApiModelProperty("签名证书密码")
        private String signatureCertificatePwd;


        @ApiModelProperty("排序")
        private Integer idx;


        @ApiModelProperty("描述")
        private String description;


        @ApiModelProperty("平台操作日志保存天数")
        private Integer platOperatLogDays;


        @ApiModelProperty("是否在线支付[10=是 20=否]")
        private Integer isPayOnline;


        @ApiModelProperty("是否开启此支付方式[10=是 20=否]")
        private Integer isEnablePaytype;


        @ApiModelProperty("是否设为默认支付方式[10=是 20=否]")
        private Integer isDefaultPayment;


        @ApiModelProperty("支持平台[10=PC 20=H5 30=小程序]")
        private Integer support;


        @ApiModelProperty("是否应用于后台[10=是 20=否]")
        private Integer isApplyBack;




    }

    @Data
    @ApiModel("SettingsPayMethodVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
