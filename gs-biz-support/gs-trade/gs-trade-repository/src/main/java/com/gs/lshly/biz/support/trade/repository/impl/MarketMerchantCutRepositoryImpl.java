package com.gs.lshly.biz.support.trade.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantCut;
import com.gs.lshly.biz.support.trade.mapper.MarketMerchantCutMapper;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantCutRepository;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5MarketActivityQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5MarketActivityVO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbMarketActivityQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketActivityVO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcMarketActivityQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketActivityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
* <p>
 *  服务实现类
 * </p>
*
* @author zdf
 * @since 2020-12-08
*/
@Service
public class MarketMerchantCutRepositoryImpl extends ServiceImpl<MarketMerchantCutMapper, MarketMerchantCut> implements IMarketMerchantCutRepository {
    @Autowired
    private  MarketMerchantCutMapper marketMerchantCutMapper;

    @Override
    public IPage<PCBbbMarketActivityVO.cutVO> selectCutListPage(IPage<PCBbbMarketActivityVO.cutVO> pager, QueryWrapper<PCBbbMarketActivityQTO.QTO> qw) {
        return marketMerchantCutMapper.selectCutListPage(pager,qw);
    }

    @Override
    public IPage<BbbH5MarketActivityVO.cutVO> selectCutH5ListPage(IPage<BbbH5MarketActivityVO.cutVO> pager, QueryWrapper<BbbH5MarketActivityQTO.QTO> qw) {
        return marketMerchantCutMapper.selectCutH5ListPage(pager,qw);
    }

    @Override
    public IPage<BbcMarketActivityVO.cutVO> selectCutBbcH5ListPage(IPage<BbcMarketActivityVO.cutVO> pager, QueryWrapper<BbcMarketActivityQTO.QTO> qw) {
        return marketMerchantCutMapper.selectCutBbcH5ListPage(pager,qw);
    }
}
