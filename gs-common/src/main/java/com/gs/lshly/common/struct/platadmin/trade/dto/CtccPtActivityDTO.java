package com.gs.lshly.common.struct.platadmin.trade.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author yangxi
 * @create 2021/5/7 16:17
 */
public class CtccPtActivityDTO implements Serializable {
    @Data
    @ApiModel("CtccPtActivityDTO.AddDTO")
    public static class AddDTO extends BaseDTO {

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

        @ApiModelProperty("banner图片组地址")
        private String bannerImagesUrl;

        @ApiModelProperty("跳转链接")
        private String jumpLinkUrl;
    }

    @ApiModel("CtccPtActivityDTO.ModifyDTO")
    @Data
    public static class ModifyDTO extends BaseDTO {

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

        @ApiModelProperty("banner图片组地址")
        private String bannerImagesUrl;

        @ApiModelProperty("跳转链接")
        private String jumpLinkUrl;
    }

    @ApiModel("CtccPtActivityDTO.CateGoryListDTO")
    @Data
    public static class CateGoryListDTO implements Serializable {
        @ApiModelProperty("商品名称")
        private String goodsName;
    }

    @Data
    @ApiModel("CtccPtActivityDTO.AddActivityGoodsDTO")
    public static class AddActivityGoodsDTO implements Serializable {
        @ApiModelProperty("商品id")
        private String goodsId;

        @ApiModelProperty("类别id")
        private String categoryId;
    }
}
