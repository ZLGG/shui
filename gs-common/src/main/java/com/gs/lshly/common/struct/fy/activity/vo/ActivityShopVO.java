package com.gs.lshly.common.struct.fy.activity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/24 下午2:22
 */
public abstract class ActivityShopVO implements Serializable {

    @Data
    @ApiModel("ActivityShopVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        /**
         * 店铺Logo
         */
        @ApiModelProperty("店铺Logo")
        private String shopLogo;

        /**
         * 店铺名称
         */
        @ApiModelProperty("店铺名称")
        private String shopName;

        /**
         * 店铺类型名称
         */
        @ApiModelProperty("店铺类型名称")
        private String shopTypeName;

        /**
         * 店铺超级管理员
         */

        /**
         * 店主姓名
         */
        @ApiModelProperty("店主姓名")
        private String shopManName;

        /**
         * 店铺联系人手机号
         */
        @ApiModelProperty("店铺联系人手机号")
        private String shopManPhone;

        /**
         * 店铺描述
         */
        @ApiModelProperty("店铺描述")
        private String shopDesc;

    }
}
