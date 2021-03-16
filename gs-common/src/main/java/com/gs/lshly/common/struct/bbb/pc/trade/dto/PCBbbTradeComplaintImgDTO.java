package com.gs.lshly.common.struct.bbb.pc.trade.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author Starry
* @since 2020-12-23
*/
public abstract class PCBbbTradeComplaintImgDTO implements Serializable {

    @Data
    @ApiModel("PCBbbTradeComplaintImgDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "图片id",hidden = true)
        private String id;

        @ApiModelProperty("订单投诉id")
        private String complaintId;

        @ApiModelProperty("图片地址")
        private String imgUrl;
    }

    @Data
    @ApiModel("PCBbbTradeComplaintImgDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "图片id")
        private String id;
    }


}
