package com.gs.lshly.common.struct.platadmin.trade.qto;
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
* @since 2020-12-24
*/
public abstract class TradeComplaintImgQTO implements Serializable {

    @Data
    @ApiModel("TradeComplaintImgQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("订单投诉id")
        private String complaintId;

        @ApiModelProperty("图片地址")
        private String imgUrl;
    }
}
