package com.gs.lshly.common.struct.platadmin.trade.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author Starry
* @since 2020-12-24
*/
public abstract class TradeComplaintDTO implements Serializable {

    @Data
    @ApiModel("TradeComplaintDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "投诉id",hidden = true)
        private String id;

        @ApiModelProperty("处理状态 20=投诉处理完成 30 =关闭投诉 ")
        private Integer handleState;

        @ApiModelProperty("处理备注")
        private String platformReply;
    }

    @Data
    @ApiModel("TradeComplaintDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "投诉id")
        private String id;
    }

    @Data
    @ApiModel("TradeComplaintDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {

        @ApiModelProperty(value = "投诉id列表")
        private List<String> idList;
    }


}
