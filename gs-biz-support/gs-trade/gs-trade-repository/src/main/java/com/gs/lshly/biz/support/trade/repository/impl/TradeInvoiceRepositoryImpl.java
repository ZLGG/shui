package com.gs.lshly.biz.support.trade.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.trade.entity.TradeInvoice;
import com.gs.lshly.biz.support.trade.mapper.TradeInvoiceMapper;
import com.gs.lshly.biz.support.trade.repository.ITradeInvoiceRepository;
import org.springframework.stereotype.Service;

/**
* <p>
 *  服务实现类
 * </p>
*
* @author tangxu
 * @since 2020-12-14
*/
@Service
public class TradeInvoiceRepositoryImpl extends ServiceImpl<TradeInvoiceMapper, TradeInvoice> implements ITradeInvoiceRepository {

    private final TradeInvoiceMapper tradeInvoiceMapper;

    public TradeInvoiceRepositoryImpl(TradeInvoiceMapper tradeInvoiceMapper) {
        this.tradeInvoiceMapper = tradeInvoiceMapper;
    }

}