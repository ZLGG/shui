package com.gs.lshly.common.struct.platadmin.trade.qto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author zst
* @since 2020-12-18
*/
public abstract class TradeRankingQTO implements Serializable {

    @Data
    @ApiModel("TradeRankingQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("商品ID")
        private String goodsId;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("点击量")
        private Integer clickRate;

        @ApiModelProperty("销售数量(spu)")
        private Integer markeNumSpu;
    }


    @Data
    @ApiModel("TradeRankingQTO.RankingQTO")
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class RankingQTO extends BaseQTO {

        @ApiModelProperty("开始时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime startTime;

        @ApiModelProperty("结束时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime endTime;

        @ApiModelProperty("选择查询时间")
        private Integer queryTimes;

    }
}
