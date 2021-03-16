package com.gs.lshly.common.struct.bbb.pc.user.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
* @author zdf
* @since 2021-01-13
*/
public abstract class PCBbbUserIntegralDTO implements Serializable {

    @Data
    @ApiModel("PCBbbUserIntegralDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty("订单金额")
        private BigDecimal tradeAmount;

        @ApiModelProperty("积分来源[10=平台添加 20=订单 30=签到]")
        private Integer fromType;

        @ApiModelProperty("会员ID")
        private String userId;

        @ApiModelProperty("业务ID")
        private String fromId;
    }

    @Data
    @ApiModel("PCBbbUserIntegralDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        private String id;
    }


}
