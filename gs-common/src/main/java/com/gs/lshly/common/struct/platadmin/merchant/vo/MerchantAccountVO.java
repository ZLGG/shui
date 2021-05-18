package com.gs.lshly.common.struct.platadmin.merchant.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.gs.lshly.common.utils.StringManageUtil;

/**
* @author xxfc
* @since 2020-10-08
*/
@SuppressWarnings("serial")
public abstract class MerchantAccountVO implements Serializable {

    @Setter
    @ApiModel("MerchantAccountVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("帐号ID")
        private String id;

        @ApiModelProperty("用户名")
        private String userName;

        @ApiModelProperty("商家帐号类型[10=主帐号 20=子帐号]")
        private Integer accountType;

        @ApiModelProperty("所属店铺")
        private String shopName;

        @ApiModelProperty("店铺类型[10=品牌旗舰店 20=品牌专卖店 30=类目专营店 40=运营商自营 50=多品类通用型]")
        private Integer shopType;

        @ApiModelProperty("手机号")
        private String phone;

        @ApiModelProperty("姓名")
        private String realName;

        @ApiModelProperty("邮箱")
        private String email;

        @ApiModelProperty("帐号状态[10=正常 20=禁用]")
        private Integer accountState;

		public String getId() {
			return id;
		}

		public String getUserName() {
			return userName = StringManageUtil.hideUserName(userName);
		}

		public Integer getAccountType() {
			return accountType;
		}

		public String getShopName() {
			return shopName;
		}

		public Integer getShopType() {
			return shopType;
		}

		public String getPhone() {
			return	phone = StringManageUtil.hidePhone(phone);
		}

		public String getRealName() {
			return StringManageUtil.hideUserName(realName);
		}

		public String getEmail() {
			return StringManageUtil.hideMail(email);
		}

		public Integer getAccountState() {
			return accountState;
		}
        
    }

    @Data
    @ApiModel("MerchantAccountVO.DetailVO")
    public static class DetailVO implements Serializable {

    }

    @Data
    @ApiModel("MerchantAccountVO.MerchShopVO")
    public static class MerchShopVO implements Serializable {

        @ApiModelProperty("商家ID")
        private String merchantId;

        @ApiModelProperty("店铺ID")
        private String shopId;

    }

    public static void main(String args[]){
    	String userName = "YIn";
    	userName = userName.substring(0, 1)+"**"+userName.substring(userName.length()-1, userName.length());
		System.out.println(userName);
    }
}
