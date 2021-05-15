package com.gs.lshly.biz.support.trade.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Author yangxi
 * @create 2021/5/14 16:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_ctcc_pt_activity_images")
public class CtccPtActivityImages extends Model {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("活动id")
    private String activityId;

    @ApiModelProperty("活动id")
    private String imageUrl;

    @ApiModelProperty("活动id")
    private String jumpUrl;

}
