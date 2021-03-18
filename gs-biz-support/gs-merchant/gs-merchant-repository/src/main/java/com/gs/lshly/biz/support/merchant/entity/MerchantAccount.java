package com.gs.lshly.biz.support.merchant.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
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
 * 商家帐号
 * </p>
 *
 * @author xxfc
 * @since 2020-10-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_merchant_account")
public class MerchantAccount extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 帐号
     */
    private String userName;

    /**
     * 密码
     */
    private String userPwd;

    /**
     * 头像
     */
    private String headImg;

    /**
     * 微信id
     */
    private String wxOpenid;

    /**
     * 微信名
     */
    private String wxName;


    /**
     * 用户姓名
     */
    private String realName;


    /**
     *手机号码
     */
    private String phone;

    /**
     *邮箱
     */
    private String email;

    /**
     * 帐号类型[10=主帐号 20=子帐号]
     */
    private Integer accountType;

    /**
     * 帐号状态[10=正常 20=禁用]
     */
    private Integer accountState;

    /**
     * 终端类型[10=2c 20=2b]
     */
    private Integer terminal;

    /**
     * 主帐号ID
     */
    private String mainAccountId;

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
