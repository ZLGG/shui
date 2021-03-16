package com.gs.lshly.common.struct.platadmin.trade.qto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.BaseQTO;
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
public abstract class TradeMarginQTO implements Serializable {

    @Data
    @ApiModel("TradeMarginQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

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
    @ApiModel("TradeMarginQTO.marginQTO")
    @Accessors(chain = true)
    public static class marginQTO extends BaseQTO {

        @ApiModelProperty("保证金账户状态\n" +
                " (10正常 20预警 30欠费)")
        private Integer marginType;

        @ApiModelProperty("保证金额度")
        private BigDecimal marginQuota;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("筛选(10大于 20小于 30等于 40大于等于 50小于等于 60介于)")
        private Integer marginScreen;

        @ApiModelProperty("大于等于")
        private BigDecimal betweenGe;

        @ApiModelProperty("小于等于")
        private BigDecimal betweenLe;
    }

}
