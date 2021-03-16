package com.gs.lshly.biz.support.trade.repository.impl;

import com.gs.lshly.biz.support.trade.entity.TradeRights;
import com.gs.lshly.biz.support.trade.entity.TradeRightsGoods;
import com.gs.lshly.biz.support.trade.entity.TradeSettlement;
import com.gs.lshly.biz.support.trade.mapper.TradeSettlementMapper;
import com.gs.lshly.biz.support.trade.repository.ITradeSettlementRepository;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* <p>
 *  服务实现类
 * </p>
*
* @author zst
 * @since 2020-11-30
*/
@Service
public class TradeSettlementRepositoryImpl extends ServiceImpl<TradeSettlementMapper, TradeSettlement> implements ITradeSettlementRepository {

    @Autowired
    private TradeSettlementMapper tradeSettlementMapper;

    @Override
    public List<Map<String,String>> queryEffectiveShop() {
        return tradeSettlementMapper.queryEffectiveShop();
    }

    @Override
    public Map<String, Object> queryTabulateData(String shopId, LocalDateTime startDate, LocalDateTime endDate) {
        return tradeSettlementMapper.queryTabulateData(shopId,startDate,endDate);
    }

    @Override
    public BigDecimal querySumDiscountAmount(String shopId,LocalDateTime startDate,LocalDateTime endDate) {
        return tradeSettlementMapper.querySumDiscountAmount(shopId,startDate,endDate);
    }

    @Override
    public BigDecimal queryGoodsRefundAmount(String shopId, String tradeId, String goodsId) {
        return tradeSettlementMapper.queryGoodsRefundAmount(shopId,tradeId,goodsId);
    }

    @Override
    public List<TradeRights> queryRightsData(String shopId,LocalDateTime startDate, LocalDateTime endDate) {
        return tradeSettlementMapper.queryRightsData(shopId,startDate,endDate);
    }

    @Override
    public Map<String, Object> queryStatementData(LocalDateTime startDate, LocalDateTime endDate) {
        return tradeSettlementMapper.queryStatementData(startDate,endDate);
    }


    @Override
    public Map<String, Object> queryStatementTradeNumData(LocalDateTime startDate, LocalDateTime endDate) {
        return tradeSettlementMapper.quantityqueryStatementTradeNumData(startDate,endDate);
    }

    @Override
    public  List<TradeSettlementVO.SaleslVO> queryStatementTradeNumData2(LocalDateTime startDate, LocalDateTime endDate) {
        return tradeSettlementMapper.quantityqueryStatementTradeNumData2(startDate,endDate);
    }

    @Override
    public List<TradeSettlement> queryStatementTradeNumList(LocalDateTime startDate, LocalDateTime endDate) {
        return tradeSettlementMapper.quantityqueryStatementTradeNumList(startDate,endDate);
    }

}