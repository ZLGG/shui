package com.gs.lshly.common.struct.common.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author xxfc
* @since 2021-01-14
*/
public abstract class CommonUserAgreementVO implements Serializable {


    @Data
    @ApiModel("CommonUserAgreementVO.DetailVO")
    public static class DetailVO implements Serializable {

        @ApiModelProperty("会员入驻协议")
        private String content;

    }
}
