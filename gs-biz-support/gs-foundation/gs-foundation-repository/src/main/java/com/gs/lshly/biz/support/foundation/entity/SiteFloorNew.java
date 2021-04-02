package com.gs.lshly.biz.support.foundation.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

/**
* <p>
* 
* </p>
*
* @author Starry
* @since 2021-03-10
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_site_floor_new")
public class SiteFloorNew extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * 楼层配置id
    */
    private String id;

    /**
    * 楼层配置名称
    */
    private String floorName;

    /**
    * 楼层配置名称别名
    */
    private String floorAlias;

    /**
    * 是否在PC端显示(10=pc 20=h5)
    */
    private Integer pcShow;

    /**
    * 展示终端(10=2b 20=2c)
    */
    private Integer terminal;

    /**
    * 排序
    */
    private Integer idx;

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
    * 逻辑删除
    */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean flag;


}
