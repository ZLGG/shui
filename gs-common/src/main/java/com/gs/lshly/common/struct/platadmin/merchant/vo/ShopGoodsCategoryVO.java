package com.gs.lshly.common.struct.platadmin.merchant.vo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gs.lshly.common.utils.BigDecimalSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-16
*/
public abstract class ShopGoodsCategoryVO implements Serializable {

    @Data
    @ApiModel("ShopGoodsCategoryVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("商品类目ID")
        private String categoryId;

        @ApiModelProperty("商品类目名称")
        private String categoryName;


    }
    @Data
    @ApiModel("ShopGoodsCategoryVO.GoodsCategoryOneVO")
    @Accessors(chain = true)
    public static class GoodsCategoryOneVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("商品类目ID")
        private String categoryId;

        @ApiModelProperty("商品类目名称")
        private String categoryName;

        @ApiModelProperty("二级分类名称")
        private List<GoodsCategoryTwoVO> childList = new ArrayList<>();

    }


    @Data
    @ApiModel("ShopGoodsCategoryVO.GoodsCategoryTwoVO")
    @Accessors(chain = true)
    public static class GoodsCategoryTwoVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("商品类目ID")
        private String categoryId;

        @ApiModelProperty("商品类目名称")
        private String categoryName;

        @ApiModelProperty(value = "父级ID",hidden = true)
        @JsonIgnore
        private String categoryPid;

        @ApiModelProperty("三级分类名称")
        private List<GoodsCategoryThreeVO> childList = new ArrayList<>();

    }

    @Data
    @ApiModel("ShopGoodsCategoryVO.GoodsCategoryThreeVO")
    @Accessors(chain = true)
    public static class GoodsCategoryThreeVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("商品类目ID")
        private String categoryId;

        @ApiModelProperty("商品类目名称")
        private String categoryName;

        @ApiModelProperty("商品类别服务费率(分成费率)")
        @JsonSerialize(using = BigDecimalSerialize.class)
        private BigDecimal fee;

        @ApiModelProperty(value = "父级ID",hidden = true)
        @JsonIgnore
        private String categoryPid;

    }

    @Data
    @ApiModel("ShopGoodsCategoryVO.DetailVO")
    public static class DetailVO extends ListVO {

    }

    @Data
    @ApiModel("ShopGoodsCategoryVO.IdListForLv1VO")
    @Accessors(chain = true)
    public static class IdListForLv1VO implements Serializable{

        @ApiModelProperty("店铺商品一级类目ID数组")
        private List<String> categoryIdList;

    }

}
