package com.gs.lshly.biz.support.trade.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
* <p>
* 交易评论表
* </p>
*
* @author Starry
* @since 2020-11-16
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_trade_comment")
public class TradeComment extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * 交易评论ID
    */
    private String id;

    /**
    * 订单ID
    */
    private String tradeId;

    /**
    * 订单商品ID
    */
    private String tradeGoodsId;

    /**
    * 会员ID
    */
    private String userId;

    /**
    * 店铺ID
    */
    private String shopId;

    /**
    * 商家ID
    */
    private String merchantId;

    /**
    * 商品ID
    */
    private String goodsId;

    /**
    * 商品名称
    */
    private String goodsName;

    /**
    * SKU ID
    */
    private String skuId;

    /**
    * 格规值
    */
    private String skuSpecValue;

    /**
    * 用户头像
    */
    private String userHeadImg;

    /**
    * 用户昵称
    */
    private String userName;

    /**
    * 订单创建时间
    */
    private LocalDateTime tradeCreateTime;

    /**
    * 商品描述分
    */
    private Integer describeGrade;

    /**
    * 物流服务分
    */
    private Integer deliveryGrade;

    /**
    * 服务态度分
    */
    private Integer serviceGrade;

    /**
     * 商品评分
     * */
    private Integer goodsGrade;

    /**
    * 是否公开头像昵称
    */
    private Integer openInfo;

    /**
    * 会员评论
    */
    private String userComment;

    /**
    * 会员追加评论
    */
    private String userAppendComment;

    /**
     * 会员追加评论时间
     */
    private LocalDateTime appendCommentTime;

    /**
    * 店铺回复
    */
    private String shopReply;

    /**
    * 店铺回复时间
    */
    private LocalDateTime replyTime;

    /**
     * 店铺追评回复
     */
    private String shopAppendReply;

    /**
     * 店铺追评回复时间
     */
    private LocalDateTime appendReplyTime;

    /**
    * 申诉状态:未申诉、商家申诉、驳回、通过
    */
    private Integer appealState;

    /**
    * 申诉类型：删除评论、修改评论
    */
    private Integer appealType;

    /**
     * 申诉次数
     */
    private Integer appealCount;

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
