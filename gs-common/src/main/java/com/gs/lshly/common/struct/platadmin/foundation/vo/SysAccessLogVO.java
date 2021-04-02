package com.gs.lshly.common.struct.platadmin.foundation.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lxus
 */
@Data
@Accessors(chain = true)
@ApiModel("SysAccessLogVO")
public class SysAccessLogVO implements Serializable {

    @Data
    @ApiModel("SysAccessLogVO.Operator")
    @Accessors(chain = true)
    public static class Operator implements Serializable {

        @ApiModelProperty("日志id")
        String id;

        @ApiModelProperty("管理员用户名")
        String userName;

        @ApiModelProperty("操作时间")
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        LocalDateTime cdate;

        @ApiModelProperty("操作内容")
        String func;

        @ApiModelProperty("操作结果")
        Boolean success;

        @ApiModelProperty("ip")
        String remoteAddr;

    }

    @Data
    @ApiModel("SysAccessLogVO.Operator")
    @Accessors(chain = true)
    public static class OperatorLogin implements Serializable {

        @ApiModelProperty("日志id")
        String id;

        @ApiModelProperty("管理员id")
        String userId;

        @ApiModelProperty("管理员用户名")
        String userName;

        @ApiModelProperty("登陆时间")
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        LocalDateTime cdate;

        @ApiModelProperty("ip")
        String remoteAddr;

    }

    @Data
    @ApiModel("SysAccessLogVO.UserLogin")
    @Accessors(chain = true)
    public static class UserLogin implements Serializable {
        @ApiModelProperty("日志id")
        String id;

        @ApiModelProperty("会员id")
        String userId;

        @ApiModelProperty("会员账号")
        String userName;

        @JsonIgnore
        String func;

        @ApiModelProperty("登陆时间")
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        LocalDateTime cdate;

        @ApiModelProperty("登陆方式")
        String loginType;

        @ApiModelProperty("登陆平台")
        String loginPlatform;

        @ApiModelProperty("ip")
        String remoteAddr;
    }

    @Data
    @ApiModel("SysAccessLogVO.MerchantLogin")
    @Accessors(chain = true)
    public static class MerchantLogin implements Serializable {

        @ApiModelProperty("日志id")
        String id;

        @ApiModelProperty("店员id")
        String userId;

        @ApiModelProperty("店员用户名")
        String userName;

        @ApiModelProperty("店铺id")
        String shopId;

        @ApiModelProperty("店铺名称")
        String shopName;

        @ApiModelProperty("登陆时间")
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        LocalDateTime cdate;

    }

}
