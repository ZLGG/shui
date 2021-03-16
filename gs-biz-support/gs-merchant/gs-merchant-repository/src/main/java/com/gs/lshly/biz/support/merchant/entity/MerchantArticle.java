package com.gs.lshly.biz.support.merchant.entity;

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
* 商家文章
* </p>
*
* @author hyy
* @since 2020-11-07
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_merchant_article")
public class MerchantArticle extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private String id;

    /**
    * 标题
    */
    private String title;

    /**
    * 内容
    */
    private String content;

    /**
    * 文章栏目ID           
    */
    private String categoryId;

    /**
    * 阅读量
    */
    private Integer readCount;

    /**
    * 排序
    */
    private Integer idx;

    /**
     * 显示类型[10=pc 20=h5 30=全部]
     */
    private Integer pcShow;


    /**
    * 发布时间
    */
    private LocalDateTime sendTime;

    /**
    * 状态[10=待审 20=通过 30=拒审]
    */
    private Integer state;

    /**
     * 拒审原因
     */
    private String rejectWhy;

    /**
    * 店铺ID
    */
    private String shopId;

    /**
    * 商家ID
    */
    private String merchantId;

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


}
