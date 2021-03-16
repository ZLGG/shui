package com.gs.lshly.common.struct.platadmin.trade.dto;
import com.gs.lshly.common.struct.BaseDTO;
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
public abstract class TradeRankingDTO implements Serializable {

    @Data
    @ApiModel("TradeRankingDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "ID",hidden = true)
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
    @ApiModel("TradeRankingDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "ID")
        private String id;
    }


}
