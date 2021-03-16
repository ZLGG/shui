package com.gs.lshly.common.struct.platadmin.commodity.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author Starry
* @since 2020-10-29
*/
public abstract class GoodsAuditRecordVO implements Serializable {

    @Data
    @ApiModel("GoodsAuditRecordVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("主键id")
        private String id;


        @ApiModelProperty("商品id")
        private String goodsId;


        @ApiModelProperty("审核状态")
        private Integer state;


        @ApiModelProperty("审核拒绝原因")
        private String refuseRemark;


        @ApiModelProperty("操作人")
        private String operator;


        @ApiModelProperty("审核时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime cdate;


    }

    @Data
    @ApiModel("GoodsAuditRecordVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
