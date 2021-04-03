package com.gs.lshly.common.struct.platadmin.merchant.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-14
*/
public abstract class MerchantApplyDTO implements Serializable {

    @Data
    @ApiModel("MerchantApplyDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("法人类型[10=个人 20=企业]")
        private Integer legalType;

        @ApiModelProperty("公司名称")
        private String corpName;

        @ApiModelProperty("营业执照注册号")
        private String corpLicenseNum;

        @ApiModelProperty("法人姓名")
        private String personName;

        @ApiModelProperty("法人身份类型[10=中国大陆 20=非中国大陆]")
        private Integer personIdcardType;

        @ApiModelProperty("法人身份证号或护照号")
        private String personIdcardNo;

        @ApiModelProperty("法人身份证正面(文件上传路径)")
        private String personIdcardFront;

        @ApiModelProperty("法人身份证反面(文件上传路径)")
        private String personIdcardBack;

        @ApiModelProperty("公司成立日期")
        private String corpEstablishDate;

        @ApiModelProperty("营业执照有效期")
        private String corpLicenseIndate;

        @ApiModelProperty("法定经营范围")
        private String corpLicenseScope;

        @ApiModelProperty("公司所在地址（省市区）")
        private String corpCityAddress;

        @ApiModelProperty("公司详细地址")
        private String corpRealAddress;

        @ApiModelProperty("公司电话")
        private String corpTelephone;

        @ApiModelProperty("公司联系人")
        private String corpPersal;

        @ApiModelProperty("公司联系人手机号")
        private String corpPhone;

        @ApiModelProperty("注册资本")
        private String corpCapital;

        @ApiModelProperty("公司官网")
        private String corpSite;

        @ApiModelProperty("组织机构代码")
        private String tissueCode;

        @ApiModelProperty("组织机构代码证复印件")
        private String tissueCodeImage;

        @ApiModelProperty("税务登记号")
        private String taxCode;

        @ApiModelProperty("税务登记证复印件")
        private String taxCodeImage;

        @ApiModelProperty("银行开户公司名")
        private String bankCorpName;

        @ApiModelProperty("银行开户帐号")
        private String bankAccount;

        @ApiModelProperty("开户行所在地")
        private String bankCityAdress;

        @ApiModelProperty("开户银行")
        private String bankName;

        @ApiModelProperty("店铺商家类型[10=2b 20=2c]")
        private String shopMerchantType;

        @ApiModelProperty("店铺类型[10=品牌旗舰店 20=品牌专卖店 30=类目专营店 40=运营商自营 50=多品类通用型]")
        private Integer shopType;

        @ApiModelProperty("店铺经营类目ID")
        private List<String> shopCategoryIdList;

        @ApiModelProperty("店铺经营品牌")
        private List<InnerBrandListDTO> brandList;

        @ApiModelProperty("品牌经营授权复印件")
        private String brandCert;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("店铺描述")
        private String shopDesc;

        @ApiModelProperty("店主姓名")
        private String shopManName;

        @ApiModelProperty("店主身份证复印件(正)")
        private String shopManIdcardFront;

        @ApiModelProperty("店主身份证复印件(反)")
        private String shopManIdcardBack;

        @ApiModelProperty("店铺地址")
        private String shopAddress;

        @ApiModelProperty("店铺联系人手机号")
        private String shopManPhone;

        @ApiModelProperty("店铺联系人邮箱")
        private String shopManEmail;

        @ApiModelProperty("商家来源[10=平台入驻]")
        private String shopMerchantFrom;

        @ApiModelProperty("商家ID(审核通过,开店成功关联)")
        private String merchantId;

        @ApiModelProperty("店铺ID(审核通过,开店成功关联)")
        private String shopId;

        @ApiModelProperty("拒绝时间")
        private LocalDateTime rejectTime;

        @ApiModelProperty("同意时间")
        private LocalDateTime okpassTime;

        @ApiModelProperty("拒绝通过原因")
        private String rejectWhy;

        @ApiModelProperty("审核状态[10=待审 20=通过 30=拒审]")
        private Integer state;
    }


    @Data
    @ApiModel("MerchantApplyDTO.IdDTO")
    @AllArgsConstructor
    public static class InnerBrandListDTO extends BaseDTO {

        @ApiModelProperty(value = "品牌ID")
        private String brandId;

        @ApiModelProperty(value = "品牌名称")
        private String brandName;

        @ApiModelProperty(value = "是否新增")
        private Integer isNew;

    }


    @Data
    @ApiModel("MerchantApplyDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("MerchantApplyDTO.IdListDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IdListDTO extends BaseDTO {
        @ApiModelProperty(value = "ID数组")
        private List<String> idList;
    }

    @Data
    @ApiModel("MerchantApplyDTO.ApplyDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ApplyDTO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty(value = "拉卡拉商户号")
        private String lakalaNo;

        @ApiModelProperty(value = "审核状态[20=通过 30=拒审]")
        private Integer state;

        @ApiModelProperty(value = "拒审原因")
        private String rejectWhy;
        
        @ApiModelProperty(value = "拒审原因 格式2021-03-31")
        private String expirationTime;
        @ApiModelProperty(value = "协议号")
        private String agreementCode;
        @ApiModelProperty(value = "供应商纳税性质(10=一般纳税人 20=小规模纳税人")
        private Integer taxType;
        @ApiModelProperty(value = "税率 double")
        private Double taxRate;
        @ApiModelProperty(value = "商户类别（10=积分商户 20=普通商户）")
        private Integer type;
        
    }

    @Data
    @ApiModel("MerchantApplyDTO.CheckApplyDTO")
    public static class CheckApplyDTO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty(value = "拉卡拉商户号")
        private String lakalaNo;

        @ApiModelProperty(value = "审核状态[20=通过 30=拒审]")
        private Integer state;

        @ApiModelProperty(value = "拒审原因")
        private String rejectWhy;
    }

    @Data
    @ApiModel("MerchantApplyDTO.HandBrandDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class HandBrandDTO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty(value = "品牌别名")
        private String brandAlias;

        @ApiModelProperty(value = "品牌logo")
        private String brandLogo;

        @ApiModelProperty(value = "顺序")
        private Integer idx;
    }

}
