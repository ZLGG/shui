package com.gs.lshly.biz.support.commodity.entity;

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
* @since 2020-12-10
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_goods_material_library")
public class GoodsMaterialLibrary extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * 商品素材库id
    */
    private String id;

    /**
    * 商品类目id
    */
    private String categoryId;

    /**
    * 商品品牌id
    */
    private String brandId;

    /**
    * 商品标题（商品名称）
    */
    private String goodsName;

    /**
    * 商品副标题
    */
    private String goodsTitle;

    /**
    * 商品移动端描述
    */
    private String goodsH5Desc;

    /**
    * 商品电脑端描述
    */
    private String goodsPcDesc;

    /**
    * 操作人
    */
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
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean flag;


}
