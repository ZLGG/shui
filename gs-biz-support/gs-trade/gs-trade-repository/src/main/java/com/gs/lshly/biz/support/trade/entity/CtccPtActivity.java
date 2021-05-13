package com.gs.lshly.biz.support.trade.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author yangxi
 * @create 2021/5/7 16:03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_ctcc_pt_activity")
public class CtccPtActivity extends Model {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
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

    @ApiModelProperty("banner图片地址")
    private String bannerImagesUrl;

    @ApiModelProperty("跳转链接")
    private String jumpLinkUrl;

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("创建时间")
    private Date gmtCreate;

    @ApiModelProperty("更新时间")
    private Date gmtModify;

    @ApiModelProperty("删除标记")
    private Boolean flag;


}
