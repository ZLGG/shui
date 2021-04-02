package com.gs.lshly.common.struct.platadmin.merchant.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.common.CommonShopDTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsCategoryVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-16
*/
public abstract class MerchantShopCategoryApplyVO implements Serializable {

    @Data
    @ApiModel("MerchantShopCategoryApplyVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("商品类目ID")
        private String goodsCategoryId;

        @ApiModelProperty("商品类目名称")
        private String goodsCategoryName;

        @ApiModelProperty("审核状态[10=待审 20=通过 30=拒审]")
        private Integer state;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("申请时间")
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;

        @ApiModelProperty("审核时间")
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime verifyTime;

        @ApiModelProperty("拒审原因")
        private String revokeWhy;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家ID")
        private String merchantId;
    }

    @Data
    @ApiModel("MerchantShopCategoryApplyVO.DetailVO")
    public static class DetailVO implements Serializable {

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("商品类目ID")
        private String goodsCategoryId;

        @ApiModelProperty("商品类目名称")
        private String goodsCategoryName;

        @ApiModelProperty("申请原因")
        private String applyWhy;

        @ApiModelProperty("审核状态[10=待审 20=通过 30=拒审]")
        private Integer state;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("店铺类型[10=品牌旗舰店 20=品牌专卖店 30=类目专营店 40=运营商自营 50=多品类通用型]")
        private Integer shopType;

        @ApiModelProperty("店铺已有的商品分类数组")
        private List<GoodsCategoryVO.CategoryTreeVO> categoryTreeVOS = new ArrayList<>();

    }
}
