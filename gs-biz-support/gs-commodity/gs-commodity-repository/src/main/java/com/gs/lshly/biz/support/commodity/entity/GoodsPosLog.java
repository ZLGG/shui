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
@TableName("gs_goods_pos_log")
public class GoodsPosLog extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * pos素材库id
    */
    private String id;

    /**
    * 商品主图地址
    */
    private String picUrl;

    /**
    * 商品数量
    */
    private Integer num;

    /**
    * 上架时间
    */
    private Date listTime;

    /**
    * 下架时间
    */
    private Date delistTime;

    /**
    * 商品价格
    */
    private BigDecimal price;

    /**
    * 商品图片列表
    */
    private String itemImgs;

    /**
    * 是否是虚拟商品
    */
    private String isVirtual;

    /**
    * 商品编号
    */
    private String numIid;

    /**
    * 商品标题
    */
    private String title;

    /**
    * 商品描述
    */
    @TableField("`desc`")
    private String desc;

    /**
    * 规格值，格式为：pid1:vid1:pid_name1:vid_name1; pid2:vid2:pid_name2:vid_name2
    */
    private String propsName;

    /**
    * 发布时间
    */
    private Date created;

    /**
    * 商品计量单位
    */
    private String unit;

    /**
    * 是否拍下减库存 0拍下减库存 1付款减库存
    */
    private Integer subStock;

    /**
    * 商品的重量 单位 克
    */
    private BigDecimal itemWeight;

    /**
    * 成本价,单位元，保留2位小数
    */
    private BigDecimal costPrice;

    /**
    * 市场价，单位元，保留2位小数
    */
    private BigDecimal mktPrice;

    /**
    * 关键属性，格式为：epid1:evid1:epid_name1:evid_name1;epid2:evid2:epid_name2:evid_name2
    */
    private String extProps;

    /**
    * 属性参数 ， 格式有两种：第一种：groupName1:name1:value1;groupName2:name2:value2;groupName2:name3:value4，第二种：文本格式
    */
    private String paramProps;

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
