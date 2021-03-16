package com.gs.lshly.biz.support.commodity.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
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
* @since 2021-02-23
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_goods_pos_temporary")
public class GoodsPosTemporary extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * 主键id
    */
    private String id;

    /**
    * POS店编号
    */
    private String posCode;

    /**
     *店铺商品spuId
     */
    private String spuId;

    /**
     *商品规格名称
     */
    private String specName;

    /**
     *商品规格值
     */
    private String specValue;

    /**
    * 店铺商品sku 69码
    */
    @TableField("pos_sKU69_code")
    private String posSku69Code;

    /**
    * 商品sku名称
    */
    private String name;

    /**
    * 商品图片
    */
    private String images;

    /**
    * 商品价格
    */
    private BigDecimal price;

    /**
    * 库存变动流水号
    */
    private String stockChangeSerialNo;

    /**
    * 库存总量
    */
    private Integer totalStock;

    /**
     * 6位随机字符
     */
    private String nonce;

    /**
     * 当前时间毫秒数
     */
    private String timestamp;

    /**
     * 是否已发布 10=未发布 20= 已发布
     */
    private Integer isRelease;

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
