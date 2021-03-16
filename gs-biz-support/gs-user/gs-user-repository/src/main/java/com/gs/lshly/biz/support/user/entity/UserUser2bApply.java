package com.gs.lshly.biz.support.user.entity;
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
 * 会员入驻申请
 * </p>
 *
 * @author xxfc
 * @since 2020-12-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_user_user2b_apply")
public class UserUser2bApply extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 法人类型[10=个人 20=企业]
     */
    private Integer legalType;

    /**
     * 商业类型[10=买家 20=卖家 30=买家卖家]
     */
    private Integer businessType;

    /**
     * 企业类型ID
     */
    private String corpTypeId;

    /**
     * 企业类型名称
     */
    private String corpTypeName;

    /**
     * 公司名称
     */
    private String corpName;

    /**
     * 公司成立日期
     */
    private String corpEstablishDate;

    /**
     * 营业执照注册号
     */
    private String corpLicenseNum;

    /**
     * 营业执照有效期
     */
    private String corpLicenseIndate;

    /**
     * 营业执照证照
     */
    private String corpLicenseCert;

    /**
     * 法定经营范围
     */
    private String corpLicenseScope;

    /**
     * 省代码
     */
    private String corpProvince;

    /**
     * 市代码
     */
    private String corpCity;

    /**
     * 县代码
     */
    private String corpCounty;

    /**
     * 公司所在地址（省）
     */
    private String corpProvinceAddress;

    /**
     * 公司所在地址（市）
     */
    private String corpCityAddress;

    /**
     * 公司所在地址（县区）
     */
    private String corpCountyAddress;

    /**
     * 公司详细地址
     */
    private String corpRealAddress;

    /**
     * 公司电话
     */
    private String corpTelephone;

    /**
     * 企业邮箱
     */
    private String corpEmail;

    /**
     * 公司联系人
     */
    private String corpPersal;

    /**
     * 公司联系人手机号
     */
    private String corpPhone;

    /**
     * 注册资本
     */
    private String corpCapital;

    /**
     * 公司官网
     */
    private String corpSite;

    /**
     * 法人姓名
     */
    private String personName;

    /**
     * 法人身份类型[10=中国大陆 20=非中国大陆]
     */
    private Integer personIdcardType;

    /**
     * 法人身份证号或护照号
     */
    private String personIdcardNo;

    /**
     * 法人身份证正面(文件上传路径)
     */
    private String personIdcardFront;

    /**
     * 法人身份证反面(文件上传路径)
     */
    private String personIdcardBack;

    /**
     * 组织机构代码
     */
    private String tissueCode;

    /**
     * 组织机构代码证复印件
     */
    private String tissueCodeCert;

    /**
     * 税务登记号
     */
    private String taxCode;

    /**
     * 统一社会信用代码
     */
    private String unifiedSocialCreditCode;

    /**
     * 税务登记证复印件
     */
    private String taxCodeCert;

    /**
     * 银行开户公司名
     */
    private String bankCorpName;

    /**
     * 银行开户帐号
     */
    private String bankAccount;

    /**
     * 开户行所在地
     */
    private String bankCityAdress;

    /**
     * 开户银行
     */
    private String bankName;

    /**
     * 审核状态[10=待审 20=通过 30=拒审]
     */
    private Integer state;

    /**
     * 拒审原因
     */
    private String revokeWhy;

    /**
     * 拒审时间
     */
    private LocalDateTime reovkeTime;

    /**
     * 审核通过时间
     */
    private LocalDateTime okpassTime;

    /**
     * 店铺ID
     */
    private String fromShopId;

    /**
     * 商家ID
     */
    private String fromMerchantId;

    /**
     * 会员编号
     */
    private String userId;

    /**
     * 邀请码
     */
    private String code;

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
