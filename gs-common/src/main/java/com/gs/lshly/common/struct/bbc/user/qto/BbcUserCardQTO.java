package com.gs.lshly.common.struct.bbc.user.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author xxfc
* @since 2020-10-27
*/
public abstract class BbcUserCardQTO implements Serializable {

    @Data
    @ApiModel("BbcUserCardQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("优惠卷ID")
        private String cardId;

        @ApiModelProperty("活动卷类型[10=平台购物卷 20=商家优惠卷]")
        private Integer cardType;

        @ApiModelProperty("会员ID")
        private String userId;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家ID")
        private String merchantId;
    }
}
