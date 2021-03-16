package com.gs.lshly.common.struct.bbc.merchant.qto;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-13
*/
public abstract class BbcShopQTO implements Serializable {

    @Data
    @ApiModel("BBC.ShopQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("店铺名称")
        private String shopName;

    }

    @Data
    @ApiModel("BBC.ShopQTO.ScopeQTO")
    @Accessors(chain = true)
    public static class ScopeQTO extends BaseQTO {

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("用户当前所在的经度(保留小数点后6位),默认公司位置")
        private String userLongitude;

        @ApiModelProperty("用户当前所在的纬度(保留小数点后6位),默认公司位置")
        private String userLatitude;

        @ApiModelProperty("距离范围(千米)默认三公里")
        private Integer len;

    }

    @Data
    @ApiModel("BbcShopQTO.InnerShopQTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class InnerShopQTO extends BaseDTO {
        /**
         * 店铺ID
         */
        private String shopId;

    }

    @Data
    @ApiModel("BbcShopQTO.InnerListShopQTO")
    @AllArgsConstructor
    public static class InnerListShopQTO extends BaseDTO {
        /**
         * 店铺ID
         */
        private List<String> shopIdList;
    }


}
