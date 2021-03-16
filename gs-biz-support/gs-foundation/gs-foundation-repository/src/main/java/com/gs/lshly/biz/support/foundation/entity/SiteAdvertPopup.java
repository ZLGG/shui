package com.gs.lshly.biz.support.foundation.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 站点广告弹窗
 * </p>
 *
 * @author yingjun
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_site_advert_popup")
public class SiteAdvertPopup extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 广告弹窗名称
     */
    private String name;

    /**
     * 图片地址
     */
    private String imageUrl;

    /**
     * 上下架 0下 1上
     */
    private Integer onoff;

    /**
     * 跳转地址
     */
    private String jumpUrl;

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
     * 专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]
     */
    private Integer subject;


}
