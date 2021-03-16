package com.gs.lshly.common.struct.bbb.pc.user.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author zdf
* @since 2021-01-13
*/
public abstract class PCBbbUserIntegralVO implements Serializable {

    @Data
    @ApiModel("PCBbbUserIntegralVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("")
        private String id;


        @ApiModelProperty("数量")
        private Integer quantity;


        @ApiModelProperty("积分来源[10=平台添加 20=订单 30=签到]")
        private Integer fromType;


        @ApiModelProperty("会员ID")
        private String userId;


        @ApiModelProperty("业务ID")
        private String fromId;


        @ApiModelProperty("到期时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime endDate;




    }

    @Data
    @ApiModel("PCBbbUserIntegralVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
