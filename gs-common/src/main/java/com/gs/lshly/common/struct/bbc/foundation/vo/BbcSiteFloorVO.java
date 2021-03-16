package com.gs.lshly.common.struct.bbc.foundation.vo;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;
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
* @since 2020-11-02
*/
public abstract class BbcSiteFloorVO implements Serializable {



    @Data
    @ApiModel("BbcSiteFloorVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("小图标")
        private String icon;

        @ApiModelProperty("楼层名")
        private String name;

        @ApiModelProperty("显示更多链接")
        private String moreUrl;

        @ApiModelProperty("商品最大显示数量")
        private Integer goodsMax;

        @ApiModelProperty("顶部广告图")
        private String topImage;

        @ApiModelProperty("顶部广告图跳转地址")
        private String jumpUrl;

        @ApiModelProperty(value = "楼层的商品列表")
        private List<BbcGoodsInfoVO.HomeAndShopInnerServiceVO> GoodsInfoList = new ArrayList<>();

    }

    @Data
    @ApiModel("BbcSiteFloorVO.DetailVO")
    public static class DetailVO extends ListVO {

    }

    @Data
    @ApiModel("BbcSiteFloorVO.List2VO")
    @Accessors(chain = true)
    public static class List2VO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("小图标")
        private String icon;

        @ApiModelProperty("楼层名")
        private String name;

        @ApiModelProperty("商品最大显示数量")
        private Integer goodsMax;

        @ApiModelProperty("广告图")
        private String topImage;

        @ApiModelProperty("广告图跳转地址")
        private String jumpUrl;

    }

}
