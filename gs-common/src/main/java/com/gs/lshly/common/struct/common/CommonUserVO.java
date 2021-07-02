package com.gs.lshly.common.struct.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
* @author xxfc
* @since 2020-10-05
*/
@SuppressWarnings("serial")
public abstract class CommonUserVO implements Serializable {


	@Data
    @ApiModel("CommonUserVO.DetailVO")
    public static class DetailVO implements Serializable {

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("用户名")
        private String userName;

        @ApiModelProperty("密码")
        private String userPwd;

        @ApiModelProperty("头像")
        private String headImg;

        @ApiModelProperty("会员积分")
        private Integer integral;

        @ApiModelProperty("真实姓名")
        private String realName;
        
        @ApiModelProperty("会员类型")
        private Integer memberType;

        @ApiModelProperty("是否为IN会员")
        private Integer isInUser;

    }
    @Data
    @ApiModel("CommonUserVO.selectUserIdByShopIdVO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class selectUserIdByShopIdVO implements Serializable {
        @ApiModelProperty("店铺id")
        private String shopId;

    }


}
