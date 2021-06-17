package com.gs.lshly.common.struct.platadmin.user.qto;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-05
*/
public abstract class UserQTO implements Serializable {

    @Data
    @ApiModel("UserQTO.QTO")
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

    @Data
    @ApiModel("FullSearcQTO.FullSearcQTO")
    @Accessors(chain = true)
    public static class FullSearchQTO extends BaseQTO {

        @ApiModelProperty(value = "会员类型[10=2b 20=2c]",hidden = true)
        private Integer type;

        @ApiModelProperty("昵称")
        private String nick;

        @ApiModelProperty("真实姓名")
        private String realName;

        @ApiModelProperty("来源店铺ID")
        private String fromShopId;

        @ApiModelProperty("性别[10=男 20=女]")
        private Integer sex;

        @ApiModelProperty("地区")
        private String countyText;

        @ApiModelProperty("注册时间1")
        private String cdate1;

        @ApiModelProperty("注册时间2")
        private String cdate2;

        @ApiModelProperty(value = "时间介词 10=早于 20=晚于 30=是 40=介于")
        private Integer timeProp;

        @ApiModelProperty("地址")
        private String realAddress;

        @ApiModelProperty("手机号")
        private String phone;

        @ApiModelProperty("星级")
        private String telecomsLevel;

        @ApiModelProperty("是否为in会员(1-是 0-否)")
        private Integer isInUser;

        @ApiModelProperty("城市")
        private String city;

        @ApiModelProperty("标签id")
        private String labelId;
    }

}
