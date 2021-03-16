package com.gs.lshly.common.struct.platadmin.trade.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
* @author zst
* @since 2020-12-09
*/
public abstract class TradeMarginVO implements Serializable {

    @Data
    @ApiModel("TradeMarginVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("店铺ID ")
        private String shopId;


        @ApiModelProperty("店铺名称 ")
        private String shopName;


        @ApiModelProperty("店铺类型[10品牌旗舰店 20品牌专卖店 30类目专营店 40运营商自营 50多品类通用型] ")
        private Integer shopType;


        @ApiModelProperty("保证金额度 ")
        private BigDecimal marginQuota;


        @ApiModelProperty("保证金余额 ")
        private BigDecimal marginBalance;


        @ApiModelProperty("保证金账户状态 (10正常 20预警 30欠费)")
        private Integer marginType;


        @ApiModelProperty("最后更新时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime udate;

    }

    @Data
    @ApiModel("TradeMarginVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
