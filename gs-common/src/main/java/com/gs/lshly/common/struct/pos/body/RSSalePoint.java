package com.gs.lshly.common.struct.pos.body;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class RSSalePoint implements Serializable {
    @ApiModelProperty("积分数")
    private long points;
    @ApiModelProperty("积分过期时间")
    private Date expiredTime;
}