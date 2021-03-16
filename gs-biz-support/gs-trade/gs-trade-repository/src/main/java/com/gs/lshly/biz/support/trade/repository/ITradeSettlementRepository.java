package com.gs.lshly.biz.support.trade.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.lshly.biz.support.trade.entity.TradeRights;
import com.gs.lshly.biz.support.trade.entity.TradeSettlement;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementVO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zst
 * @since 2020-11-30
 */
public interface ITradeSettlementRepository extends IService<TradeSettlement> {

    List<Map<String,String>> queryEffectiveShop();

    Map<String,Object> queryTabulateData(String shopId,LocalDateTime startDate,LocalDateTime endDate);

    BigDecimal querySumDiscountAmount(String shopId,LocalDateTime startDate,LocalDateTime endDate);

    BigDecimal queryGoodsRefundAmount(String shopId,String tradeId,String goodsId);

    List<TradeRights> queryRightsData(String shopId,LocalDateTime startDate,LocalDateTime endDate);

    Map<String,Object> queryStatementData(LocalDateTime startDate,LocalDateTime endDate);

    Map<String,Object> queryStatementTradeNumData(LocalDateTime startDate,LocalDateTime endDate);

    List<TradeSettlementVO.SaleslVO> queryStatementTradeNumData2(LocalDateTime startDate,LocalDateTime endDate);

    List<TradeSettlement> queryStatementTradeNumList(LocalDateTime startDate, LocalDateTime endDate);
}
