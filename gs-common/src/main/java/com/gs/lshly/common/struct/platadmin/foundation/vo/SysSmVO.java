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
public abstract class SysSmVO implements Serializable {

    @Data
    @ApiModel("SysSmVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("主键id")
        private String id;


        private String key;

        private String secret;

        private String signName;




    }

    @Data
    @ApiModel("SysSmVO.DetailVO")
    public static class DetailVO extends ListVO {

    }


    @Data
    @ApiModel("SysSmVO.PageVO")
    public static class PageVO extends ListVO {

        @ApiModelProperty("发布项目类型")
        private Integer projectType;

        @ApiModelProperty("短信模板设置")
        private ListVO smsListVo;

        @ApiModelProperty("邮件模板设置")
        private SysMailVO.ListVO mailVO;
    }
}
