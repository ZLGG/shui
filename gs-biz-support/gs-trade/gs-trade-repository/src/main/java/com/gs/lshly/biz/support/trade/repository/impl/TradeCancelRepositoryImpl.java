package com.gs.lshly.biz.support.trade.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.TradeCancel;
import com.gs.lshly.biz.support.trade.mapper.TradeCancelMapper;
import com.gs.lshly.biz.support.trade.repository.ITradeCancelRepository;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeDeliveryQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeDeliveryVO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeCancelQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeCancelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
* <p>
 *  服务实现类
 * </p>
*
* @author oy
 * @since 2020-11-20
*/
@Service
public class TradeCancelRepositoryImpl extends ServiceImpl<TradeCancelMapper, TradeCancel> implements ITradeCancelRepository {

    @Autowired
    private TradeCancelMapper tradeCancelMapper;

    @Override
    public IPage<TradeCancelVO.ListVO> selectListPage(IPage<TradeCancelVO.ListVO> page, QueryWrapper<TradeCancelQTO.QTO> qw) {
        return tradeCancelMapper.selectListPage(page,qw);
    }

    @Override
    public IPage<TradeCancel> selectCancelListPage(IPage<TradeCancel> page, QueryWrapper<TradeCancel> qw) {
        return tradeCancelMapper.selectCancelListPage(page,qw);
    }
}