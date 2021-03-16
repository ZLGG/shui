package com.gs.lshly.common.struct.platadmin.foundation.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.List;

/**
* @author 陈奇
* @since 2020-10-12
*/
public abstract class SettingsPayMethodDTO implements Serializable {

    @Data
    @ApiModel("SettingsPayMethodDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty(value = "支付方式名称")
        private String name;

        @ApiModelProperty(value = "支付方式key")
        private String payKey;

        @ApiModelProperty(value = "商户号")
        private String merchantNumber;

        @ApiModelProperty(value = "签名证书")
        private String signatureCertificate;

        @ApiModelProperty(value = "签名证书密码")
        private String signatureCertificatePwd;

        @ApiModelProperty(value = "排序")
        private Integer idx;

        @ApiModelProperty(value = "描述")
        private String description;

        @ApiModelProperty(value = "平台操作日志保存天数")
        private Integer platOperatLogDays;

        @ApiModelProperty(value = "是否在线支付[10=是 20=否]")
        private Integer isPayOnline;

        @ApiModelProperty(value = "是否开启此支付方式[10=是 20=否]")
        private Integer isEnablePaytype;

        @ApiModelProperty(value = "是否设为默认支付方式[10=是 20=否]")
        private Integer isDefaultPayment;

        @ApiModelProperty(value = "支持平台[10=PC 20=H5 30=小程序]")
        private Integer support;

        @ApiModelProperty(value = " 是否应用于后台[10=是 20=否]")
        private Integer isApplyBack;

    }

    @Data
    @ApiModel("SettingsPayMethodDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("SettingsPayMethodDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {

        @ApiModelProperty(value = "ID数组")
        private List<String> idList;
    }

}
