package com.gs.lshly.biz.support.foundation.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 站点专题配置信息
 * </p>
 *
 * @author yingjun
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_site_topic")
public class SiteTopic extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;
    
    
    private String remark;

    /**
     * 名称
     */
    private String name;

    /**
     * 上下架 0 1
     */
    private Integer onoff;
    
    private String imageUrl;

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
     * 逻辑删除标记
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean flag;
    
    
    /**
     * 是否PC显示[10=是 20=否]
     */
    private Integer pcShow;

    /**
     * 终端[10=2b 20=2c]
     */
    private Integer terminal;

    /**
     * 专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏 50=积分商城]
     */
    private Integer subject;
    
    /**
     * 所属分类，可多选 10：电信甄选 20：名品集市
     */
    private String category;
    
    
    private Integer idx;


}
