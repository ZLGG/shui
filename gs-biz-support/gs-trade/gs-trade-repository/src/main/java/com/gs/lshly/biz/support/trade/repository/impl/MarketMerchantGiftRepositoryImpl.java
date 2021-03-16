package com.gs.lshly.biz.support.trade.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantGift;
import com.gs.lshly.biz.support.trade.mapper.MarketMerchantGiftMapper;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantGiftRepository;
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
 * @since 2020-12-09
*/
@Service
public class MarketMerchantGiftRepositoryImpl extends ServiceImpl<MarketMerchantGiftMapper, MarketMerchantGift> implements IMarketMerchantGiftRepository {

    @Autowired
    private MarketMerchantGiftMapper marketMerchantGiftMapper;

    @Override
    public IPage<PCBbbMarketActivityVO.giftVO> selectGiftListPage(IPage<PCBbbMarketActivityVO.giftVO> pager, QueryWrapper<PCBbbMarketActivityQTO.QTO> qw) {
        return marketMerchantGiftMapper.selectGiftListPage(pager,qw);
    }

    @Override
    public IPage<BbbH5MarketActivityVO.giftVO> selectGiftH5ListPage(IPage<BbbH5MarketActivityVO.giftVO> pager, QueryWrapper<BbbH5MarketActivityQTO.QTO> qw) {
        return marketMerchantGiftMapper.selectGiftH5ListPage(pager,qw);
    }

    @Override
    public IPage<BbcMarketActivityVO.giftVO> selectGiftBbcH5ListPage(IPage<BbcMarketActivityVO.giftVO> pager, QueryWrapper<BbcMarketActivityQTO.QTO> qw) {
        return marketMerchantGiftMapper.selectGiftBbcH5ListPage(pager,qw);
    }
}