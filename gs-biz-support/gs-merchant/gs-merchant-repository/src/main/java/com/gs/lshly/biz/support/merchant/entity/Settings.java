package com.gs.lshly.biz.support.merchant.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.time.LocalDateTime;

/**
* <p>
* 基本设置
* </p>
*
* @author Starry
* @since 2020-10-30
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_settings")
public class Settings extends Model {

private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private String id;

    /**
    * 支付密码停用时间
    */
    private Integer pwdStopTime;

    /**
    * 支付密码停用错误次数
    */
    private Integer pwdStopError;

    /**
    * 支付密码停用提示次数
    */
    private Integer pwdStopTips;

    /**
    * 会员登录日志保留天数
    */
    private Integer userLogDays;

    /**
    * 多账号类型注册
    */
    private String regAccountType;

    /**
    * 商家操作日志保存天数
    */
    private Integer mercOperLogDays;

    /**
    * 平台操作日志保存天数
    */
    private Integer platOperLogDays;

    /**
    * 短信日志保存天数
    */
    private Integer messageLogDays;

    /**
    * 商家登录日志保存天数
    */
    private Integer mercRegLogDays;

    /**
    * 评价追评时间限制天数
    */
    private Integer addAssessDays;

    /**
    * 是否开启货到付款[10=是 20=否]
    */
    private Integer isCod;

    /**
    * 是否开启商品上架审核[10=是 20=否]
    */
    private Integer isReviewProduct;

    /**
    * 是否开启商家营销活动审核[10=是 20=否]
    */
    private Integer isReviewMarketing;

    /**
    * 是否开启商家发布文章平台审核[10=是 20=否]
    */
    private Integer isReviewArticle;

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
