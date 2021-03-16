package com.gs.lshly.biz.support.commodity.entity;

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
 * 
 * </p>
 *
 * @author Starry
 * @since 2020-10-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_goods_qa")
public class GoodsQa extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 商品问答id
     */
    private String id;

    /**
     * 商品id
     */
    private String goodId;

    /**
     * 商家id
     */
    private String merchantId;

    /**
     * 店铺id
     */
    private String shopId;

    /**
     * 咨询类型（10=商品咨询，20=库存配送，30=支付方式，40=发票保修）
     */
    private Integer quizType;

    /**
     * 咨询内容
     */
    private String quizContent;

    /**
     * 回答内容
     */
    private String content;

    /**
     * 联系方式
     */
    private String contactWay;

    /**
     * 是否匿名（默认为10=实名，20=匿名）
     */
    private Integer isAnonymous;

    /**
     * 是否将咨询内容显示在商品详情页（默认为10=不显示，20=显示）
     */
    private Integer isShowQuizContent;

    /**
     * 是否将回答内容显示在商品详情页（默认为10=不显示，20=显示）
     */
    private Integer isShowContent;

    /**
     * 是否已回复(10=未回复，20=已回复）
     */
    private Integer isReply;

    /**
     * ip地址
     */
    private String ip;

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
