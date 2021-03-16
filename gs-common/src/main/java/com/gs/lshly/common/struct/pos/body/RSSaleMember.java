package com.gs.lshly.common.struct.pos.body;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class RSSaleMember extends RSEntity {
    private static final long serialVersionUID = -4144154987139896821L;
    @ApiModelProperty("会员编码")
    private String code;
    @ApiModelProperty("会员姓名")
    private String name;
    @ApiModelProperty("会员电话")
    private String phone;
    @ApiModelProperty("会员生日")
    private Date birthday;
    @ApiModelProperty("会员等级名称")
    private String levelName;
}
