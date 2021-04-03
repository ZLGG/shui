package com.gs.lshly.common.struct.bb.trade.dto;
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
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月29日 下午11:53:17
 */
public abstract class RechargeOrderDTO implements Serializable {

    @Data
    @ApiModel("RechargeOrderDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "充值手机号码")
        private String phone;

        @ApiModelProperty("充值金额")
        private BigDecimal phoneBille;

    }

}
