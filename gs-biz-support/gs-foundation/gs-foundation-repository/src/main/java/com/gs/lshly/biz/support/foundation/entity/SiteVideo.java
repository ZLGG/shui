package com.gs.lshly.biz.support.foundation.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 站点宣传视频
 * </p>
 *
 * @author 陈奇
 * @since 2020-10-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_site_video")
public class SiteVideo extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 视频封面图片
     */
    private String imageUrl;

    /**
     * 视视网络地址
     */
    private String videoUrl;

    /**
     * 封面图宽
     */
    private Integer imageW;

    /**
     * 封面图高
     */
    private Integer imageH;

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

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime cdate;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime udate;

    /**
     * 删除标记
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean flag;



}
