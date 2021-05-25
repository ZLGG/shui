package com.gs.lshly.biz.support.trade.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author yangxi
 * @create 2021/5/24 21:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_ctcc_pt_activity_goods_relation")
public class CtccActivityGoods extends Model {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("活动")
    private String activityId;

    @ApiModelProperty("类目id")
    private String categoryId;

    @ApiModelProperty("类目id")
    private String goodsId;

    @ApiModelProperty("商品状态（10-未上架，20-已上架）")
    private Integer goodsState;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime cdate;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime udate;

    @ApiModelProperty("是否删除（0-未删除，1-已删除）")
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean flag;

}
