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
 * 商家店铺商品类目申请
 * </p>
 *
 * @author xxfc
 * @since 2020-10-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_merchant_apply_category")
public class MerchantApplyCategory extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 入驻申请ID
     */
    private String applyId;

    /**
     * 申请方式[10=入驻申请 20=店铺申请]
     */
    private Integer applyType;

    /**
     * 申请原因
     */
    private String applyWhy;

    /**
     * 商品类目ID
     */
    private String goodsCategoryId;

    /**
     * 商品类目名称
     */
    private String goodsCategoryName;

    /**
     * 终端
     */
    private Integer terminal;

    /**
     * 审核状态[10=待审 20=通过 30=拒审]
     */
    private Integer state;

    /**
     * 拒审原因
     */
    private String revokeWhy;

    /**
     * 审核时间
     */
    private LocalDateTime verifyTime;

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
