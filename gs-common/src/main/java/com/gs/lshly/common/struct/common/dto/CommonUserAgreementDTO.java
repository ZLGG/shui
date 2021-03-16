package com.gs.lshly.common.struct.common.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author xxfc
* @since 2021-01-14
*/
public abstract class CommonUserAgreementDTO implements Serializable {

    @Data
    @ApiModel("CommonUserAgreementVO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty("会员入驻协议")
        private String content;

        @ApiModelProperty(value = "10=2b 20=2c",hidden = true)
        private Integer userType;
    }

    @Data
    @ApiModel("CommonUserAgreementVO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        private String id;
    }


}
