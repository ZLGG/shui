package com.gs.lshly.common.struct.platadmin.merchant.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-16
*/
public abstract class MerchantShopCategoryApplyDTO implements Serializable {

    @Data
    @ApiModel("MerchantShopCategoryApplyDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("商品类目ID")
        private String goodsCategoryId;

        @ApiModelProperty("审核状态[10=待审 20=通过 30=拒审]")
        private Integer state;

        @ApiModelProperty("拒审原因")
        private String revokeWhy;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家ID")
        private String merchantId;
    }

    @Data
    @ApiModel("MerchantShopCategoryApplyDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("MerchantShopCategoryApplyDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {

        @ApiModelProperty(value = "ID数组")
        private List<String> idList;
    }

    @Data
    @ApiModel("MerchantShopCategoryApplyDTO.ApplyDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ApplyDTO extends BaseDTO {

        @ApiModelProperty(value = "ID",hidden = true)
        private String id;

        @ApiModelProperty(value = "审核状态[10=待审 20=通过 30=拒审]")
        private Integer state;

        @ApiModelProperty(value = "拒审原因")
        private String revokeWhy;

    }

}
