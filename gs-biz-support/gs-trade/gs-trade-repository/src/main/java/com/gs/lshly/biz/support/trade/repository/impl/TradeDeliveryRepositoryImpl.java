package com.gs.lshly.biz.support.trade.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.trade.entity.TradeDelivery;
import com.gs.lshly.biz.support.trade.mapper.TradeDeliveryMapper;
import com.gs.lshly.biz.support.trade.repository.ITradeDeliveryRepository;
import com.gs.lshly.common.struct.merchadmin.h5.trade.qto.H5MerchTradeDeliveryQTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeDeliveryVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeDeliveryQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeDeliveryVO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeDeliveryQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeDeliveryVO;
import org.springframework.stereotype.Service;

/**
* <p>
 *  服务实现类
 * </p>
*
* @author oy
 * @since 2020-11-16
*/
@Service
public class TradeDeliveryRepositoryImpl extends ServiceImpl<TradeDeliveryMapper, TradeDelivery> implements ITradeDeliveryRepository {

    private final TradeDeliveryMapper tradeDeliveryMapper;

    public TradeDeliveryRepositoryImpl(TradeDeliveryMapper tradeDeliveryMapper) {
        this.tradeDeliveryMapper = tradeDeliveryMapper;
    }

    @Override
    public IPage<PCMerchTradeDeliveryVO.ListVO> selectListPage(IPage<PCMerchTradeDeliveryVO.ListVO> page, QueryWrapper<PCMerchTradeDeliveryQTO.QTO> qw) {
        return tradeDeliveryMapper.selectListPage(page,qw);
    }

    @Override
    public IPage<H5MerchTradeDeliveryVO.ListVO> selectH5ListPage(IPage<H5MerchTradeDeliveryVO.ListVO> page, QueryWrapper<H5MerchTradeDeliveryQTO.QTO> qw) {
        return tradeDeliveryMapper.selectH5ListPage(page,qw);
    }

    @Override
    public IPage<TradeDeliveryVO.ListVO> selectListPageForPlatform(IPage<TradeDeliveryVO.ListVO> page, QueryWrapper<TradeDeliveryQTO.QTO> qw) {
        return tradeDeliveryMapper.selectListPageForPlatform(page,qw);
    }
}