package com.gs.lshly.common.struct.bbc.trade.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author oy
 * @since 2020-12-06
 */
public abstract class BbcTradeRightsLogVO implements Serializable {

    @Data
    @ApiModel("BbcTradeRightsLogVO.listVO")
    @Accessors(chain = true)
    public static class listVO implements Serializable {
        @ApiModelProperty("状态(10:待处理,20:商家同意,30:商户驳回,40:买家二次申诉,50:平台同意,60:平台驳回,70:换货完成,80:商家确认收货并退款,90:用户取消)")
        private Integer state;
        @ApiModelProperty("状态说明")
        private String content;

        @ApiModelProperty("申请时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;
    }
}
