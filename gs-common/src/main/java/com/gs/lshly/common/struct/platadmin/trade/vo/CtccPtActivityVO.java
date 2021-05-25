package com.gs.lshly.common.struct.platadmin.trade.vo;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.CtccPtActivityDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
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
        @ApiModelProperty("电信国际类目id")
        private String id;

        @ApiModelProperty("类目名称")
        private String name;

        @ApiModelProperty("封面图")
        private String imageUrl;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("商品列表")
        private List<BbcGoodsInfoVO.CtccGoodsDetailVO> goodsList;

    }

    @Data
    @ApiModel("CtccPtActivityVO.ActivityListVO")
    public static class ActivityListVO implements Serializable {
        @ApiModelProperty("活动id")
        private String id;

        @ApiModelProperty("活动名称")
        private String name;

        @ApiModelProperty("活动开始时间")
        private Date startTime;

        @ApiModelProperty("活动结束时间")
        private Date endTime;

        @ApiModelProperty("抵扣类型（0-电信积分 1-in会员抵扣券）")
        private Integer deductionType;

        @ApiModelProperty("限购类型（0-不限购 1-限购）")
        private Integer limitType;

        @ApiModelProperty("每人限购数量")
        private Integer limitCount;

        @ApiModelProperty("商品列表")
        private List<BbcGoodsInfoVO.CtccGoodsDetailVO> goodsList;
    }

    @ApiModel("CtccPtActivityVO.DetailVO")
    @Data
    public static class DetailVO {

        @ApiModelProperty("活动id")
        private String id;

        @ApiModelProperty("活动名称")
        private String name;

        @ApiModelProperty("活动开始时间")
        private Date startTime;

        @ApiModelProperty("活动结束时间")
        private Date endTime;

        @ApiModelProperty("抵扣类型（0-电信积分 1-in会员抵扣券）")
        private Integer deductionType;

        @ApiModelProperty("限购类型（0-不限购 1-限购）")
        private Integer limitType;

        @ApiModelProperty("每人限购数量")
        private Integer limitCount;

        @ApiModelProperty("banner图片")
        List<CtccPtActivityDTO.ImageGroupDTO> imageGroupDTOList;
    }
}
