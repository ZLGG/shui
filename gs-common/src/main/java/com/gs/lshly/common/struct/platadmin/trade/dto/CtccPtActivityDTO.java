package com.gs.lshly.common.struct.platadmin.trade.dto;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author yangxi
 * @create 2021/5/7 16:17
 */
public class CtccPtActivityDTO implements Serializable {

    @Data
    @ApiModel("CtccPtActivityDTO.RemoveGoodsDTO")
    public static class RemoveGoodsDTO implements Serializable {
        @ApiModelProperty("商品id")
        private String goodsId;

        @ApiModelProperty("商品状态（10-未上架，20-已上架）")
        private Integer goodsState;
    }

    @Data
    @ApiModel("CtccPtActivityDTO.DeleteGoodsDTO")
    public static class DeleteGoodsDTO implements Serializable {
        @ApiModelProperty("商品id列表")
        private List<String> goodsIdList;

    }
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

        @ApiModelProperty("banner图片")
        List<ImageGroupDTO> imageGroupDTOList;
    }

    @ApiModel("CtccPtActivityDTO.ImageGroupDTO")
    @Data
    public static class ImageGroupDTO implements Serializable {
        @ApiModelProperty("banner图片地址")
        private String bannerImageUrl;

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

        @ApiModelProperty("banner图片")
        List<ImageGroupDTO> imageGroupDTOList;
    }

    @ApiModel("CtccPtActivityDTO.CateGoryListDTO")
    @Data
    public static class CateGoryListDTO implements Serializable {
        @ApiModelProperty("商品名称")
        private String goodsName;
    }

    @Data
    @ApiModel("CtccPtActivityDTO.AddGoodsDTO")
    public static class AddGoodsDTO implements Serializable {
        @ApiModelProperty("商品id")
        private String goodsId;

        @ApiModelProperty("类别id")
        private String categoryId;

        @ApiModelProperty("排序")
        private Integer idx;

    }

    @Data
    @ApiModel("CtccPtActivityDTO.AddCategoryDTO")
    public static class AddCategoryDTO extends BaseDTO {
        @ApiModelProperty("类目名称")
        @NotBlank(message = "类目名称不能为空")
        private String name;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("封面图")
        private String imageUrl;

        @ApiModelProperty("备注")
        private String remark;
    }

    @Data
    @ApiModel("CtccPtActivityDTO.AddCategoryGoodsDTO")
    public static class AddCategoryGoodsDTO implements Serializable {
        @ApiModelProperty("类目id")
        @NotBlank(message = "类目id不能为空")
        private String categoryId;

        @ApiModelProperty("商品id")
        @NotBlank(message = "商品id不能为空")
        private String goodsId;

        @ApiModelProperty("排序")
        private Integer idx;
    }

    @Data
    @ApiModel("CtccPtActivityDTO.ActivityListDTO")
    public static class ActivityListDTO extends BaseQTO {
        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("类别id")
        private String categoryId;
    }
}
