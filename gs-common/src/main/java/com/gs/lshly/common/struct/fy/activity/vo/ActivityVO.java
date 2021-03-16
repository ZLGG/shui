package com.gs.lshly.common.struct.fy.activity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/24 下午2:17
 */
public abstract class ActivityVO implements Serializable {

    @Data
    @ApiModel("ActivityVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable {

        /**
         * id
         */
        @ApiModelProperty("id")
        private String id;

        /**
         * 活动名称
         */
        @ApiModelProperty("活动名称")
        private String name;

        /**
         * 活动范围：0 全部、1 PC端、2 wap端
         */
        @ApiModelProperty("活动范围：0 全部、1 PC端、2 wap端")
        private Integer range;

        /**
         * 活动开始时间
         */
        @ApiModelProperty("活动开始时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime startDate;

        /**
         * 活动结束时间
         */
        @ApiModelProperty("活动结束时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime endDate;

        /**
         * 长期有效: 0 否，1 是
         */
        @ApiModelProperty("长期有效: 0 否，1 是")
        private Integer longTermValidity;

        @ApiModelProperty("活动关联商家")
        private String shopName;

        /**
         * 创建时间
         */
        @ApiModelProperty("创建时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;

        /**
         * 修改时间
         */
        @ApiModelProperty("修改时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime udate;


        /**
         * 活动状态：0 有效、1 失效
         */
        @ApiModelProperty("活动状态：0 有效、1 失效")
        private Integer status;

    }

    @Data
    @ApiModel("ActivityVO.DetailVO")
    @Accessors(chain = true)
    public static class DetailVO extends ListVO implements Serializable {

        @ApiModelProperty("活动关联商家id")
        private String shopIds;

    }


}
