package com.gs.lshly.common.struct;

import com.gs.lshly.common.struct.platadmin.foundation.vo.rbac.SysFuncVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
public class AuthDTO implements Serializable {

	@ApiModelProperty("token")
	private String token;
	
    @ApiModelProperty("用户id")
    private String id;

    @ApiModelProperty("用户类型")
    private Integer type;

    @ApiModelProperty("登陆名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("商家id")
    private String merchantId;

    @ApiModelProperty("店铺id")
    private String shopId;

    @ApiModelProperty("头像")
    private String headImg;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("账号状态")
    private Integer state;

    @ApiModelProperty("功能权限集")
    private List<SysFuncVO.List> permitFuncs;

}