package com.gs.lshly.biz.support.merchant.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商家入驻申请
 * </p>
 *
 * @author xxfc
 * @since 2020-10-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_merchant_apply")
public class MerchantApply extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 帐号ID
     */
    private String accountId;

    /**
     * pos门店id
     */
    private String posShopId;

    /**
     * 法人类型[10=个人 20=企业]
     */
    private Integer legalType;

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
     * 营业执照注册号
     */
    private String corpLicenseNum;

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
     * 公司成立日期
     */
    private String corpEstablishDate;

    /**
     * 营业执照有效期
     */
    private String corpLicenseIndate;

    /**
     * 法定经营范围
     */
    private String corpLicenseScope;

    /**
     * 公司所在地址（省市区）
     */
    private String corpCityAddress;

    /**
     * 公司详细地址
     */
    private String corpRealAddress;

    /**
     * 公司电话
     */
    private String corpTelephone;

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
     * 店铺商家类型[10=2b 20=2c]
     */
    private Integer shopMerchantType;

    /**
     * 店铺类型[10=品牌旗舰店 20=品牌专卖店 30=类目专营店 40=运营商自营 50=多品类通用型]
     */
    private Integer shopType;

    /**
     * 店铺经营类目ID多个
     */
    private String shopCategoryIds;

    /**
     * 店铺经营类目名称多个
     */
    private String shopCategoryNames;


    /**
     * 店铺经营品牌名称
     */
    private String brandName;

    /**
     * 店铺经营品牌ID
     */
    private String brandId;

    /**
     * 是否新增品牌
     */
    private Integer brandIsNew;

    /**
     * 品牌经营授权复印件
     */
    private String brandCert;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 店铺描述
     */
    private String shopDesc;

    /**
     * 店主姓名
     */
    private String shopManName;

    /**
     * 店主身份证复印件(正)
     */
    private String shopManIdcardFront;

    /**
     * 店主身份证复印件(反)
     */
    private String shopManIdcardBack;

    /**
     * 店铺地址
     */
    private String shopAddress;

    /**
     * 店铺联系人手机号
     */
    private String shopManPhone;

    /**
     * 店铺联系人邮箱
     */
    private String shopManEmail;

    /**
     * 商家来源[10=平台入驻]
     */
    private Integer shopMerchantFrom;

    /**
     * 商家ID(审核通过,开店成功关联)
     */
    private String merchantId;

    /**
     * 店铺ID(审核通过,开店成功关联)
     */
    private String shopId;

    /**
     * 拉卡拉商户号
     */
    private String lakalaNo;

    /**
     * 流程进度[10=企业 20=银行 30=店铺 40=证照 50=审核]
     */
    private Integer progress;

    /**
     * 拒绝时间
     */
    private LocalDateTime rejectTime;

    /**
     * 同意时间
     */
    private LocalDateTime okpassTime;

    /**
     * 拒绝通过原因
     */
    private String rejectWhy;

    /**
     * 审核状态[10=待审 20=通过 30=拒审]
     */
    private Integer state;

    /**
     * 是否以开通店铺
     */
    private Integer isOpen;

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
