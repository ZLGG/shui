package com.gs.lshly.biz.support.trade.mapper.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeCommentRecordVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeGoodsVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author Starry
 * @Date 17:38 2020/11/19
 */
@Data
public class TradeAppealCommentView {

    @ApiModelProperty("评价id")
    private String id;

    @ApiModelProperty("评价等级")
    private String commentLevel;

    @ApiModelProperty("订单编号")
    private String tradeCode;

    @ApiModelProperty("商品名称")
    private String goodsName;

    @ApiModelProperty("商品销售金额")
    private BigDecimal salePrice;

    @ApiModelProperty("评价人")
    private String userName;

    @ApiModelProperty("进度")
    private String progress;

    @ApiModelProperty("申诉结果")
    private Integer appealState;

    @ApiModelProperty("是否开启二次申诉")
    private boolean openReAppeal;
}
