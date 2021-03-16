package com.gs.lshly.common.struct.bbc.trade.dto;

import com.gs.lshly.common.struct.common.CommonStockDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * 提交订单商品计算DTO
 *
 * @author shinl
 * @date 2021/3/15 15:56
 */
@Data
public class BbcTradeCommitGoodsCalculateDTO implements Serializable {

    @ApiModelProperty(value = "店铺商品总金额（商城商品）")
    private BigDecimal shopProductAmount;

    @ApiModelProperty(value = "积分总金额（积分商城商品）")
    private BigDecimal shopProductPointAmount;

    @ApiModelProperty(value = "SKUID/购买数量")
    private List<CommonStockDTO.InnerChangeStockItem> subtractStockItems;

    @ApiModelProperty(value = "交易商品数据")
    private Set<BbcTradeGoodsDTO.ETO> tradeGoodsDTOSet;

    @ApiModelProperty(value = "购物车ID")
    private List<String> cartIdList;
}
