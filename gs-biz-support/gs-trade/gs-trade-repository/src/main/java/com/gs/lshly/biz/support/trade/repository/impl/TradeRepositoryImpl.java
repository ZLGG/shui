package com.gs.lshly.biz.support.trade.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.trade.entity.Trade;
import com.gs.lshly.biz.support.trade.mapper.TradeMapper;
import com.gs.lshly.biz.support.trade.repository.ITradeRepository;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5TradeQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeListVO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.BbbOrderQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbTradeListVO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeListVO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.qto.H5MerchTradeQTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeListVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.MerchantHomeDashboardDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeListVO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeListVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* <p>
 *  服务实现类
 * </p>
*
* @author oy
 * @since 2020-10-28
*/
@Service
public class TradeRepositoryImpl extends ServiceImpl<TradeMapper, Trade> implements ITradeRepository {

    private final TradeMapper tradeMapper;

    public TradeRepositoryImpl(TradeMapper tradeMapper) {
        this.tradeMapper = tradeMapper;
    }

    @Override
    public IPage<BbcTradeListVO.tradeVO> selectTradeListPage(IPage<BbcTradeListVO.tradeVO> page, QueryWrapper<BbcTradeQTO.TradeList> qw) {

        return tradeMapper.selectTradeListPage(page,qw);
    }

    @Override
    public IPage<BbbH5TradeListVO.tradeVO> BbbH5selectTradeListPage(IPage<BbbH5TradeListVO.tradeVO> page, QueryWrapper<BbbH5TradeQTO.TradeList> qw) {
        return tradeMapper.BbbH5selectTradeListPage(page,qw);
    }

    @Override
    public List<BbcTradeListVO.stateCountVO> selectTradeStateCount(QueryWrapper<BbcTradeQTO> qw) {
        return tradeMapper.selectTradeStateCount(qw);
    }

    @Override
    public List<BbbH5TradeListVO.stateCountVO> BbbH5selectTradeStateCount(QueryWrapper<BbbH5TradeQTO> qw) {
        return tradeMapper.BbbH5selectTradeStateCount(qw);
    }

    @Override
    public IPage<PCMerchTradeListVO.tradeVO> selectPCMerchTradePage(IPage<PCMerchTradeListVO.tradeVO> page, QueryWrapper<PCMerchTradeQTO.TradeList> qw) {
        return tradeMapper.selectPCMerchTradePage(page,qw);
    }

    @Override
    public IPage<H5MerchTradeListVO.tradeVO> selectH5MerchTradePage(IPage<H5MerchTradeListVO.tradeVO> page, QueryWrapper<H5MerchTradeQTO.TradeList> qw) {
        return tradeMapper.selectH5MerchTradePage(page,qw);
    }

    @Override
    public IPage<TradeListVO.tradeVO> selectTradePage(IPage<TradeListVO.tradeVO> page, QueryWrapper<TradeQTO.TradeList> qw) {
        return tradeMapper.selectTradePage(page,qw);
    }

    @Override
    public IPage<BbbTradeListVO.tradeVO> selectBbbTradeListPage(IPage<BbbTradeListVO.tradeVO> page, QueryWrapper<BbbOrderQTO.TradeList> qw) {
        return tradeMapper.selectBbbTradeListPage(page,qw);
    }

    @Override
    public List<Trade> yesterdayTrade(QueryWrapper<MerchantHomeDashboardDTO.ETO> queryWrapper) {
        return tradeMapper.yesterdayTrade(queryWrapper);
    }
}