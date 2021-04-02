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
public abstract class RemindMerchantQTO implements Serializable {

    @Data
    @ApiModel("RemindMerchantQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("消息提醒业务类型,不传就是查全部(具体枚举查看外部文档)")
        private Integer cType;

        @ApiModelProperty("10=待读 20=已读,不传或0值查全部")
        private Integer state;
    }

}
