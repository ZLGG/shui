package com.gs.lshly.biz.support.trade.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantDiscount;
import com.gs.lshly.biz.support.trade.mapper.MarketMerchantDiscountMapper;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantDiscountRepository;
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
public class MarketMerchantDiscountRepositoryImpl extends ServiceImpl<MarketMerchantDiscountMapper, MarketMerchantDiscount> implements IMarketMerchantDiscountRepository {
    @Autowired
    private MarketMerchantDiscountMapper marketMerchantDiscountMapper;
    @Override
    public IPage<PCBbbMarketActivityVO.discountVO> selectDiscountListPage(IPage<PCBbbMarketActivityVO.discountVO> pager, QueryWrapper<PCBbbMarketActivityQTO.QTO> qw) {
        return marketMerchantDiscountMapper.selectDiscountListPage(pager,qw);
    }

    @Override
    public IPage<BbbH5MarketActivityVO.discountVO> selectDiscountH5ListPage(IPage<BbbH5MarketActivityVO.discountVO> pager, QueryWrapper<BbbH5MarketActivityQTO.QTO> qw) {
        return marketMerchantDiscountMapper.selectDiscountH5ListPage(pager,qw);
    }

    @Override
    public IPage<BbcMarketActivityVO.discountVO> selectDiscountBbcH5ListPage(IPage<BbcMarketActivityVO.discountVO> pager, QueryWrapper<BbcMarketActivityQTO.QTO> qw) {
        return marketMerchantDiscountMapper.selectDiscountBbcH5ListPage(pager,qw);
    }
}