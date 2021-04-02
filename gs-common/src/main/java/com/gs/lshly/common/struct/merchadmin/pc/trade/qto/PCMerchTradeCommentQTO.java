package com.gs.lshly.common.struct.merchadmin.pc.trade.qto;
import com.fasterxml.jackson.annotation.JsonFormat;
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
* @since 2020-11-16
*/
public abstract class PCMerchTradeCommentQTO implements Serializable {

    @Data
    @ApiModel("PCMerchTradeCommentQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {
        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("状态[10=有晒图 20=有回复 30=有内容]")
        private Integer state;

        @ApiModelProperty("评价开始时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime startTime;

        @ApiModelProperty("评价结束时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime endTime;

        @ApiModelProperty("评论分类[10=全部 20=好评 30=中评 40=差评]")
        private Integer comment;

    }

    @Data
    @ApiModel("PCMerchTradeCommentQTO.AppealCommentQTO")
    @Accessors(chain = true)
    public static class AppealCommentQTO extends BaseQTO {

        @ApiModelProperty("申诉进度(1=一次申述 2=二次申述")
        private Integer progress;

        @ApiModelProperty("申诉结果 申诉状态:商家申诉、驳回、通过，关闭")
        private Integer appealState;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("申诉时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime cdate;


    }

}
