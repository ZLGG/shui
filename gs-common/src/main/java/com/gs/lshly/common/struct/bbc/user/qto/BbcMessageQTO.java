package com.gs.lshly.common.struct.bbc.user.qto;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author yangxi
 * @create 2021/4/6 14:50
 */
public abstract class BbcMessageQTO implements Serializable {

    @Data
    @ApiModel("BbcMessageQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseDTO {

    }

    @Data
    @ApiModel("BbcMessageQTO.MessageQTO")
    @Accessors(chain = true)
    public static class MessageQTO extends BaseQTO {
        @ApiModelProperty("消息类型(1-系统消息，2-活动消息)")
        @NotNull(message = "消息类型不能为空")
        private Integer type;
    }

    @Data
    @ApiModel("BbcMessageQTO.NoticeListQTO")
    @Accessors(chain = true)
    public static class NoticeListQTO extends BaseQTO {
    }

    @Data
    @ApiModel("BbcMessageQTO.DetailQTO")
    @Accessors(chain = true)
    public static class DetailQTO extends BaseDTO {
        @ApiModelProperty("消息id")
        private String id;
    }

}
