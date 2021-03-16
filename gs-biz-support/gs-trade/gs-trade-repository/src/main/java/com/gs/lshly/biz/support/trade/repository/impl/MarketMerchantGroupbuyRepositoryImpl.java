package com.gs.lshly.biz.support.trade.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantGroupbuy;
import com.gs.lshly.biz.support.trade.mapper.MarketMerchantGroupbuyMapper;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantGroupbuyRepository;
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
 * @since 2020-12-10
*/
@Service
public class MarketMerchantGroupbuyRepositoryImpl extends ServiceImpl<MarketMerchantGroupbuyMapper, MarketMerchantGroupbuy> implements IMarketMerchantGroupbuyRepository {

    @Autowired
    private MarketMerchantGroupbuyMapper marketMerchantGroupbuyMapper;
    @Override
    public IPage<PCBbbMarketActivityVO.groupbuyVO> selectGroupbuyListPage(IPage<PCBbbMarketActivityVO.groupbuyVO> pager, QueryWrapper<PCBbbMarketActivityQTO.QTO> qw) {
        return marketMerchantGroupbuyMapper.selectGroupbuyListPage(pager,qw);
    }

    @Override
    public IPage<BbbH5MarketActivityVO.groupbuyVO> selectGroupbuyH5ListPage(IPage<BbbH5MarketActivityVO.groupbuyVO> pager, QueryWrapper<BbbH5MarketActivityQTO.QTO> qw) {
        return marketMerchantGroupbuyMapper.selectGroupbuyH5ListPage(pager,qw);
    }

    @Override
    public IPage<BbcMarketActivityVO.groupbuyVO> selectGroupbuyBbcH5ListPage(IPage<BbcMarketActivityVO.groupbuyVO> pager, QueryWrapper<BbcMarketActivityQTO.QTO> qw) {
        return marketMerchantGroupbuyMapper.selectGroupbuyBbcH5ListPage(pager,qw);
    }
}