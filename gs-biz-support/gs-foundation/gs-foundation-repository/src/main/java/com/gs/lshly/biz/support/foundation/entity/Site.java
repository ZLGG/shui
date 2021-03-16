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
* 站点基本信息
* </p>
*
* @author hyy
* @since 2020-11-11
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_site")
public class Site extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private String id;

    /**
    * 站点名称
    */
    private String name;

    /**
    * 商家登录背景图
    */
    private String merchantLoginBackimage;

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
    * 是否启用手机号验证[10=开启  20=关闭]
    */
    private Integer state;
}
