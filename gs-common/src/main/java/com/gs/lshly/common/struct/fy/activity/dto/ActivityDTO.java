package com.gs.lshly.common.struct.fy.activity.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/24 下午11:22
 */
public abstract class ActivityDTO implements Serializable {

    @Data
    @ApiModel("ActivityDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {
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
        private LocalDateTime startDate;

        /**
         * 活动结束时间
         */
        @ApiModelProperty("活动结束时间")
        private LocalDateTime endDate;

        /**
         * 长期有效: 0 否，1 是
         */
        @ApiModelProperty("长期有效: 0 否，1 是")
        private Integer longTermValidity;

        @ApiModelProperty("活动关联商家id-集合")
        private List<String> shopIds;

    }

    @Data
    @ApiModel("ActivityDTO.PCUDTO")
    @Accessors(chain = true)
    public static class PCUDTO extends ETO {

        /**
         * id
         */
        @ApiModelProperty("id")
        private String id;
    }


    @Data
    @ApiModel("ActivityDTO.ActivityIdListDTO")
    @AllArgsConstructor
    public static class ActivityIdListDTO extends BaseDTO {

        @ApiModelProperty(value = "活动id集合")
        private List<String> activityIds;
    }

}

