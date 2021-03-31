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
 * 商家
 * </p>
 *
 * @author xxfc
 * @since 2020-10-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_merchant")
public class Merchant extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 商家名称
     */
    private String merchantName;

    /**
     * 商家头像
     */
    private String merchantHeadImg;

    /**
     * 业务终端(10-2b,20-2c)
     */
    private Integer terminal;

    /**
     * 法人单位ID
     */
    private String legalId;

    /**
     * 是否自营商家
     */
    private Integer isPlatform;

    /**
     * 拉卡拉商户号
     */
    private String  lakalaNo;

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


    /**
     * 联系地址
     */
    private String address;

    /**
     * 商户名称
     */
    private String name;

    /**
     *商户类别（10=积分商户 20=普通商户）
     */
    private Integer type;

    /**
     *商户属地（省名称）
     */
    private String province;

    /**
     *商户属地（市名称）
     */
    private String city;

    /**
     *协议到期时间
     */
    private Date expirationTime;

    /**
     *协议号
     */
    private String agreementCode;

    /**
     *供应商纳税性质(10=一般纳税人 20=小规模纳税人
     */
    private Integer taxType;

    /**
     *税率(%)
     */
    private Double taxRate;
}
