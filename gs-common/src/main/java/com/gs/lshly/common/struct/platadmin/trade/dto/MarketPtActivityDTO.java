package com.gs.lshly.common.struct.platadmin.trade.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.stock.dto.MarketPtActivityGoodsCategoryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author zdf
* @since 2020-11-28
*/
public abstract class MarketPtActivityDTO implements Serializable {

    @Data
    @ApiModel("MarketPtActivityDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("活动名称")
        private String name;

        @ApiModelProperty("标签")
        private String label;

        @ApiModelProperty("描述")
        private String activityDescribe;

        @ApiModelProperty("店铺类型")
        private String typeCode;

        @ApiModelProperty("折扣范围")
        private String discountRange;

        @ApiModelProperty("是否提醒[10=是 20=否]")
        private Integer smsIsTell;

        @ApiModelProperty("提醒方式")
        private String reminders;

        @ApiModelProperty("开销提醒提前分钟数")
        private Integer smsBefore;

        @ApiModelProperty("报名开始时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime signStartTime;

        @ApiModelProperty("报名结束时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime signEndTime;

        @ApiModelProperty("活动上线时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime onlineStartTime;

        @ApiModelProperty("开售开始时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime activityStartTime;

        @ApiModelProperty("开售结束时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime activityEndTime;

        @ApiModelProperty("会员限购数量上限")
        private Integer buyMax;

        @ApiModelProperty("店铺参加商品数上限")
        private Integer goodsMax;

        @ApiModelProperty(value = "创建时间",hidden = true)
        private LocalDateTime cdate;

        @ApiModelProperty("活动封面图")
        private String coverImage;


        @ApiModelProperty("商品类目id")
        private List<String> categoryIds;
    }

    @Data
    @ApiModel("MarketPtActivityDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "活动id")
        private String id;
    }
    @Data
    @ApiModel("MarketPtActivityDTO.IdListDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IdListDTO extends BaseDTO {
        @ApiModelProperty(value = "活动id")
        private List<String> idList;
    }


}
