package com.gs.lshly.common.struct.platadmin.merchant.vo;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.common.LegalDictVO;
import com.gs.lshly.common.utils.StringManageUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* @author xxfc
* @since 2020-10-14
*/
public abstract class MerchantApplyVO implements Serializable {

    @Setter
    @ApiModel("MerchantApplyVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("审核状态[10=待审 20=通过 30=拒审]")
        private Integer state;

        @ApiModelProperty("店铺类型[10=品牌旗舰店 20=品牌专卖店 30=类目专营店 40=运营商自营 50=多品类通用型]")
        private Integer shopType;

        @ApiModelProperty("商家来源[10=平台入驻]")
        private Integer shopMerchantFrom;

        @ApiModelProperty("商家帐号")
        private String merchantAccount;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("店主姓名")
        private String shopManName;

        @ApiModelProperty("公司名称")
        private String corpName;

        @ApiModelProperty(value = "品牌ID")
        private String brandId;

        @ApiModelProperty(value = "品牌名称")
        private String brandName;

        @ApiModelProperty(value = "是否新增[0=否 1=是]")
        private Integer brandIsNew;

        @ApiModelProperty("品牌经营授权复印件")
        private String brandCert;

        @ApiModelProperty("修改时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime udate;

        @ApiModelProperty("拒绝时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime rejectTime;

        @ApiModelProperty("同意时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime okpassTime;

        @ApiModelProperty("拒绝通过原因")
        private String rejectWhy;

        @ApiModelProperty("店铺是否已经开通")
        private Integer isOpen;

		public String getId() {
			return id;
		}

		public Integer getState() {
			return state;
		}

		public Integer getShopType() {
			return shopType;
		}

		public Integer getShopMerchantFrom() {
			return shopMerchantFrom;
		}

		public String getMerchantAccount() {
			return StringManageUtil.hideUserName(merchantAccount);
		}

		public String getShopName() {
			return shopName;
		}

		public String getShopManName() {
			return shopManName;
		}

		public String getCorpName() {
			return corpName;
		}

		public String getBrandId() {
			return brandId;
		}

		public String getBrandName() {
			return brandName;
		}

		public Integer getBrandIsNew() {
			return brandIsNew;
		}

		public String getBrandCert() {
			return brandCert;
		}

		public LocalDateTime getUdate() {
			return udate;
		}

		public LocalDateTime getRejectTime() {
			return rejectTime;
		}

		public LocalDateTime getOkpassTime() {
			return okpassTime;
		}

		public String getRejectWhy() {
			return rejectWhy;
		}

		public Integer getIsOpen() {
			return isOpen;
		}

        
    }

    @Data
    @ApiModel("MerchantApplyVO.DetailVO")
    public static class DetailVO implements  Serializable {

        @ApiModelProperty(value = "入驻申请ID",position = 1)
        private String id;

        @ApiModelProperty(value = "拉卡拉商户号",position = 1)
        private String lakalaNo;

        @ApiModelProperty(value = "公司信息",position = 2)
        private LegalDictVO.CompanyVO companyVO;

        @ApiModelProperty(value = "银行信息",position = 3)
        private LegalDictVO.BankVO bankVO;

        @ApiModelProperty(value = "店铺信息",position = 4)
        private CommonShopVO.ListVO shopVO;

        @ApiModelProperty(value = "证照信息数组",position = 5)
        private List<LegalDictVO.CertVO> certListVO;

    }

    @Data
    @ApiModel("MerchantApplyVO.BrandVO")
    public static class BrandVO implements  Serializable {

        @ApiModelProperty(value = "入驻申请ID",position = 1)
        private String id;

        @ApiModelProperty(value = "商品分类ID")
        private String goodsCategoryId;

        @ApiModelProperty("商品分类名称")
        private String goodsCategoryName;

        @ApiModelProperty(value = "品牌ID")
        private String brandId;

        @ApiModelProperty(value = "品牌名称")
        private String brandName;

        @ApiModelProperty(value = "是否新增")
        private Integer brandIsNew;

        @ApiModelProperty("品牌经营授权复印件")
        private String brandCert;

    }


}
