package com.gs.lshly.biz.support.commodity.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Starry
 * @since 2020-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_goods_params")
@ApiModel(value="GoodsParams对象", description="")
public class GoodsParams implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "参数组id")
    private String id;

    @ApiModelProperty(value = "类目id")
    private String categoryId;

    @ApiModelProperty(value = "参数组名")
    private String name;

    @ApiModelProperty(value = "操作人")
    private String operator;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime cdate;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime udate;

    /**
     * 删除标记
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean flag;

    @TableField(exist = false)
    private List<GoodsParamInfo> paramInfos;
}
