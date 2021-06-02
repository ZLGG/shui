package com.gs.lshly.common.struct.bbc.user.qto;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @Author yangxi
 * @create 2021/3/22 14:22
 */
public class BbcInUserCouponQTO implements Serializable {
    @Data
    @ApiModel("BbcInUserCouponQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseDTO {
        @ApiModelProperty("优惠券类型（0-全部 1-IN会员抵扣券 2-店铺券 3-平台券 4-个人券）")
        private Integer couponType;
    }

    @Data
    @ApiModel("BbcInUserCouponQTO.CardQTO")
    public static class CardQTO extends BaseDTO {

    }

    @Data
    @ApiModel("BbcInUserCouponQTO.BuyCouponQTO")
    @Accessors(chain = true)
    public static class BuyCouponQTO implements Serializable {
        @ApiModelProperty("in会员userId")
        @NotBlank(message = "userId不能为空")
        private String userId;

        @ApiModelProperty("会员类型（0-年度会员，1-月度会员）")
        @NotNull(message = "会员类型不能为空")
        private Integer vipType;
    }

    @Data
    @ApiModel("BbcInUserCouponQTO.ShareCouponQTO")
    @Accessors(chain = true)
    public static class ShareCouponQTO implements Serializable {
        @ApiModelProperty("in会员userId")
        @NotBlank(message = "userId不能为空")
        private String userId;
    }

    @Data
    @ApiModel("BbcInUserCouponQTO.MyCouponQTO")
    public static class MyCouponQTO extends BaseDTO {
        @ApiModelProperty("区间id")
        private String zoneId;

        @ApiModelProperty("商品id")
        @NotBlank(message = "商品id不能为空")
        private String goodsId;

        @ApiModelProperty("类目id")
        @NotBlank(message = "类目id不能为空")
        private String categoryId;
    }

    @Data
    @ApiModel("BbcInUserCouponQTO.ZoneQTO")
    public static class ZoneQTO implements Serializable {
        @ApiModelProperty("适用维度id")
        private String levelId;

        @ApiModelProperty("维度类型（1-专区id，2-类目id，3-商品id）")
        private Integer type;
    }

}
