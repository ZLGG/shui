package com.gs.lshly.common.struct.platadmin.foundation.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author Starry
* @since 2021-03-20
*/
public abstract class SysMailVO implements Serializable {

    @Data
    @ApiModel("SysMailVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("主键id")
        private String id;


        @ApiModelProperty("邮件服务器的SMTP地址")
        private String host;


        @ApiModelProperty("邮件服务器的SMTP端口")
        private String post;


        @ApiModelProperty("发件人（必须正确，否则发送失败）")
        private String from;


        @ApiModelProperty("用户名，默认为发件人邮箱前缀")
        private Integer user;


        @ApiModelProperty("密码")
        private String pass;




    }

    @Data
    @ApiModel("SysMailVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
