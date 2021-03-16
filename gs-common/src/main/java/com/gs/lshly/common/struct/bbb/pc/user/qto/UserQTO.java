package com.gs.lshly.common.struct.bbb.pc.user.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author xxfc
* @since 2020-10-05
*/
public abstract class UserQTO implements Serializable {

    @Data
    @ApiModel("BBB.UserQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("查询类型[10=用户名 20=手机号  30=真实姓名 40=标签 50=邮箱]")
        private Integer queryType;

        @ApiModelProperty("查询内容")
        private String queryValue;

        @ApiModelProperty(value = "会员状态[10=可用 20=禁用]",hidden = true)
        private Integer state;

        @ApiModelProperty(value = "会员类型[10=2b 20=2c]",hidden = true)
        private Integer type;

    }

}
