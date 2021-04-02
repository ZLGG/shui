package com.gs.lshly.common.struct.platadmin.foundation.qto;
import com.gs.lshly.common.struct.BaseQTO;
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
public abstract class SysSmTemplateQTO implements Serializable {

    @Data
    @ApiModel("SysSmTemplateQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("")
        private String smId;

        @ApiModelProperty("模板类型")
        private Integer templateType;

        @ApiModelProperty("模板code")
        private String templateCode;
    }
}
