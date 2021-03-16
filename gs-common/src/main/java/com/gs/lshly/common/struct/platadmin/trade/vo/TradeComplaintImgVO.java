package com.gs.lshly.common.struct.platadmin.trade.vo;
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
public abstract class TradeComplaintImgVO implements Serializable {

    @Data
    @ApiModel("TradeComplaintImgVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("图片id")
        private String id;


        @ApiModelProperty("订单投诉id")
        private String complaintId;


        @ApiModelProperty("图片地址")
        private String imgUrl;




    }

    @Data
    @ApiModel("TradeComplaintImgVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
