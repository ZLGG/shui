package com.gs.lshly.biz.support.foundation.entity;

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
 * 支付方式设置
 * </p>
 *
 * @author 陈奇
 * @since 2020-10-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_settings_pay_method")
public class SettingsPayMethod extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 支付方式名称
     */
    private String name;

    /**
     * 支付方式key
     */
    private String payKey;

    /**
     * 商户号
     */
    private String merchantNumber;

    /**
     * 签名证书
     */
    private String signatureCertificate;

    /**
     * 签名证书密码
     */
    private String signatureCertificatePwd;

    /**
     * 排序
     */
    private Integer idx;

    /**
     * 描述
     */
    private String description;

    /**
     * 平台操作日志保存天数
     */
    private Integer platOperatLogDays;

    /**
     * 是否在线支付[10=是 20=否]
     */
    private Integer isPayOnline;

    /**
     * 是否开启此支付方式[10=是 20=否]
     */
    private Integer isEnablePaytype;

    /**
     * 是否设为默认支付方式[10=是 20=否]
     */
    private Integer isDefaultPayment;

    /**
     * 支持平台[10=PC 20=H5 30=小程序]
     */
    private Integer support;

    /**
     * 是否应用于后台[10=是 20=否]
     */
    private Integer isApplyBack;

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
