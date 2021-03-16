package com.gs.lshly.biz.support.trade.entity;

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
* @author zst
* @since 2020-12-18
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_trade_ranking")
public class TradeRanking extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * ID
    */
    private String id;

    /**
    * 商品ID
    */
    private String goodsId;

    /**
    * 店铺ID
    */
    private String shopId;

    /**
    * 商品名称
    */
    private String goodsName;

    /**
    * 店铺名称
    */
    private String shopName;

    /**
    * 点击量
    */
    private Integer clickRate;

    /**
    * 销售数量(spu)
    */
    private Integer markeNumSpu;

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
