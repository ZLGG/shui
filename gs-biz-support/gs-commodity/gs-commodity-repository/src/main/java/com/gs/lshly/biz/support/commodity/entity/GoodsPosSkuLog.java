package com.gs.lshly.biz.support.commodity.entity;

import java.math.BigDecimal;
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
* @since 2020-12-15
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_goods_pos_sku_log")
public class GoodsPosSkuLog extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * skupos素材库id
    */
    private String id;

    /**
    * sku商品编号
    */
    private String skuId;

    /**
    * sku对应的商品编号
    */
    private String numIid;

    /**
    * sku库存
    */
    private Integer quantity;

    /**
    * sku商品价格
    */
    private BigDecimal price;

    /**
    * sku商品状态（normal=正常，delete=删除）
    */
    private String status;

    /**
    * sku对应的属性名称，（格式为：pid1:vid1:pid_name1:vid_name1; pid2:vid2:pid_name2:vid_name2）
    */
    private String propertiesName;

    /**
    * 条形码
    */
    private String barcode;

    /**
    * 成本价
    */
    private BigDecimal costPrice;

    /**
    * sku的销售属性组合字符串（颜色，大小，等等，可通过类目API获取某类目下的销售属性）,格式是p1:v1;p2:v2
    */
    private String properties;

    /**
    * 卖点（副标题）
    */
    private String sellPoint;

    /**
    * sku图片列表
    */
    private String skuImgs;

    /**
    * sku市场价
    */
    private BigDecimal marketPrice;

    /**
    * sku重量
    */
    private BigDecimal weight;

    /**
    * 对sku的操作是否成功
    */
    private Boolean isSuccess;

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
