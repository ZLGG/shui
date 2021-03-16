package com.gs.lshly.common.struct.merchadmin.h5.merchant.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-23
*/
public abstract class H5MerchMerchantAccountVO implements Serializable {

    @Data
    @ApiModel("H5MerchMerchantAccountVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("帐号")
        private String userName;

        @ApiModelProperty("角色名称")
        private String roleName;

        @ApiModelProperty("手机号")
        private String phone;

        @ApiModelProperty("邮箱")
        private String email;

        @ApiModelProperty("店铺id")
        private String shopId;

    }

    @Data
    @ApiModel("H5MerchMerchantAccountVO.DetailVO")
    public static class DetailVO implements Serializable {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("用户名")
        private String userName;

        @ApiModelProperty("姓名")
        private String realName;

        @ApiModelProperty("手机号")
        private String phone;

        @ApiModelProperty("邮箱")
        private String email;

        @ApiModelProperty("角色ID")
        private String roleId;

        @ApiModelProperty("角色名称")
        private String roleName;

        @ApiModelProperty("商家所有的角色数组")
        private List<RoleItemVO> roleList;

    }

    @Data
    @ApiModel("H5MerchMerchantAccountVO.RoleItemVO")
    public static class RoleItemVO implements  Serializable{

        @ApiModelProperty("角色ID")
        private String id;
        /**
         * 角色名称
         */
        private String roleName;
    }

    @Data
    @ApiModel("H5MerchMerchantAccountVO.CheckShopVO")
    @Accessors(chain = true)
    public static class CheckShopVO implements Serializable{

        @ApiModelProperty("店铺ID")
        private String shopId;

    }

    @Data
    @ApiModel("H5MerchMerchantAccountVO.checkShopByShopId")
    @Accessors(chain = true)
    public static class checkShopByShopId implements Serializable{

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家ID")
        private String jwtMerchantId;

        @ApiModelProperty("商家ID")
        private ShopSimpleVO shopSimpleVO;


    }


    @Data
    @ApiModel("H5MerchMerchantAccountVO.ShopSimpleVO")
    public static class ShopSimpleVO implements Serializable {

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("店铺Log")
        private String shopLogo;

        @ApiModelProperty("店铺描述")
        private String shopDesc;

        @ApiModelProperty("店铺类型[10=品牌旗舰店 20=品牌专卖店 30=类目专营店 40=运营商自营 50=多品类通用型]")
        private Integer shopType;

        @ApiModelProperty("邀请码")
        private String shareCode;

        @ApiModelProperty("pos门店ID")
        private String posShopId;


    }

}
