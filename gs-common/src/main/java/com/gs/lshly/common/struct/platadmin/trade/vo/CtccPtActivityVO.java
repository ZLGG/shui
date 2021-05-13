package com.gs.lshly.common.struct.platadmin.trade.vo;

import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author yangxi
 * @create 2021/5/12 15:58
 */
public class CtccPtActivityVO implements Serializable {

    @Data
    @ApiModel("CtccPtActivityVO.CategoryListVO")
    public static class CategoryListVO implements Serializable {
        @ApiModelProperty("电信国际类目")
        private List<CtccCategoryVO> categorys;
    }

    @Data
    @ApiModel("CtccPtActivityVO.CtccAdvertVO")
    public static class CtccAdvertVO implements Serializable {
        @ApiModelProperty("名称")
        private String name;

        @ApiModelProperty("封面图")
        private String imageUrl;

        @ApiModelProperty("封面图跳转")
        private String jumpUrl;

        @ApiModelProperty("是否是类目商品")
        private Integer isCategory;

        @ApiModelProperty("类目ID")
        private String categoryId;

        @ApiModelProperty("备注")
        private String remark;
    }

    @Data
    @ApiModel("CtccPtActivityVO.CtccCategoryVO")
    public static class CtccCategoryVO implements Serializable {
        @ApiModelProperty("电信国际分类ID")
        private String id;

        @ApiModelProperty("名称")
        private String name;

        @ApiModelProperty("封面图")
        private String imageUrl;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("商品列表")
        private List<BbcGoodsInfoVO.CtccGoodsDetailVO> goodsList;

    }
}
