package com.gs.lshly.common.struct.platadmin.trade.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author zst
* @since 2020-12-18
*/
public abstract class TradeRankingVO implements Serializable {

    @Data
    @ApiModel("TradeRankingVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("ID")
        private String id;


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
    @ApiModel("TradeRankingVO.DetailVO")
    public static class DetailVO extends ListVO {

    }


    @Data
    @ApiModel("TradeRankingVO.RankingVO")
    public static class RankingVO implements Serializable {

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("点击量")
        private Integer clickRate;

        @ApiModelProperty("销售数量(spu)")
        private Integer markeNumSpu;

        @ApiModelProperty("转化率")
        private Integer conversionRate;
    }
}
