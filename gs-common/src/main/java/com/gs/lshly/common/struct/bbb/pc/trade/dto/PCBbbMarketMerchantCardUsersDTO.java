package com.gs.lshly.common.struct.bbb.pc.trade.dto;
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
* @since 2021-01-08
*/
public abstract class PCBbbMarketMerchantCardUsersDTO implements Serializable {

    @Data
    @ApiModel("PCBbbMarketMerchantCardUsersDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("会员ID")
        private String userId;

        @ApiModelProperty("商家ID")
        private String merchantId;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家优惠卷ID")
        private String cardId;

        @ApiModelProperty("优惠卷名称")
        private String cardName;

        @ApiModelProperty("描述")
        private String userDescribe;

        @ApiModelProperty("适用平台[10=2b 20=2c]")
        private Integer terminal;

        @ApiModelProperty("适用会员等级(1,2,3,4,5,6)")
        private String onUserLeve;

        @ApiModelProperty("满多少")
        private BigDecimal toPrice;

        @ApiModelProperty("减多少")
        private BigDecimal cutPrice;

        @ApiModelProperty("有效时间开始")
        private LocalDateTime validStartTime;

        @ApiModelProperty("有效时间结束")
        private LocalDateTime validEndTime;

        @ApiModelProperty("使用状态[10=未使用 20=已使用]")
        private Integer state;
    }

    @Data
    @ApiModel("PCBbbMarketMerchantCardUsersDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
