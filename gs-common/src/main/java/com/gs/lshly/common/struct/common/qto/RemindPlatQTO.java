package com.gs.lshly.common.struct.common.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author xxfc
* @since 2021-02-05
*/
public abstract class RemindPlatQTO implements Serializable {

    @Data
    @ApiModel("RemindPlatQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("消息提醒业务类型(具体枚举查看外部文档)")
        private Integer cType;

    }
}
