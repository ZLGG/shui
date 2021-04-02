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
public abstract class SysSmTemplateVO implements Serializable {

    @Data
    @ApiModel("SysSmTemplateVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("主键id")
        private String id;


        @ApiModelProperty("")
        private String smId;


        @ApiModelProperty("模板code")
        private String templateCode;




    }

    @Data
    @ApiModel("SysSmTemplateVO.DetailVO")
    public static class DetailVO extends ListVO {
        @ApiModelProperty("签名名称")
        private String signName;
    }
}
