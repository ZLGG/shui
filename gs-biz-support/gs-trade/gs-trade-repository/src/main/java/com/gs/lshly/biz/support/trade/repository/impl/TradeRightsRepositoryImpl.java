package com.gs.lshly.biz.support.trade.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.trade.entity.TradeRights;
import com.gs.lshly.biz.support.trade.mapper.TradeRightsMapper;
import com.gs.lshly.biz.support.trade.repository.ITradeRightsRepository;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5TradeRightsQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeRightsVO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.BbbTradeRightsQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbTradeRightsVO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeRightsQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeRightsVO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.qto.H5MerchTradeRightsQTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeRightsVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeRightsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeRightsVO;
import org.springframework.stereotype.Service;

/**
* <p>
 *  服务实现类
 * </p>
*
* @author oy
 * @since 2020-12-06
*/
@Service
public class TradeRightsRepositoryImpl extends ServiceImpl<TradeRightsMapper, TradeRights> implements ITradeRightsRepository {

    private final TradeRightsMapper tradeRightsMapper;

    public TradeRightsRepositoryImpl(TradeRightsMapper tradeRightsMapper) {
        this.tradeRightsMapper = tradeRightsMapper;
    }

    @Override
    public IPage<BbcTradeRightsVO.ListVO> selectTradeRightsList(IPage<BbcTradeRightsVO.ListVO> page, QueryWrapper<BbcTradeRightsQTO.RightsList> qw) {

        return tradeRightsMapper.selectTradeRightsList(page,qw);
    }

    @Override
    public IPage<BbbH5TradeRightsVO.ListVO> selectBbbH5TradeRightsList(IPage<BbbH5TradeRightsVO.ListVO> page, QueryWrapper<BbbH5TradeRightsQTO.RightsList> qw) {
        return tradeRightsMapper.selectBbbH5TradeRightsList(page,qw);
    }

    @Override
    public IPage<BbbTradeRightsVO.ListVO> selectBbbTradeRightsList(IPage<BbbTradeRightsVO.ListVO> page, QueryWrapper<BbbTradeRightsQTO.RightsList> qw) {
        return tradeRightsMapper.selectBbbTradeRightsList(page,qw);
    }

    @Override
    public IPage<PCMerchTradeRightsVO.ListVO>  selectMerchRightList(IPage<PCMerchTradeRightsVO.ListVO> page, QueryWrapper<PCMerchTradeRightsQTO.QTO> qw) {
        return tradeRightsMapper.selectMerchRightList(page,qw);
    }

    @Override
    public IPage<TradeRights> selectPlatadminTradeRightsList(IPage<TradeRights> page, QueryWrapper<TradeRights> qw) {
        return tradeRightsMapper.selectPlatadminTradeRightsList(page,qw);
    }

    @Override
    public IPage<H5MerchTradeRightsVO.ListVO>  selectMerchH5RightList(IPage<H5MerchTradeRightsVO.ListVO> page, QueryWrapper<H5MerchTradeRightsQTO.QTO> qw) {
        return tradeRightsMapper.selectMerchH5RightList(page,qw);
    }
}