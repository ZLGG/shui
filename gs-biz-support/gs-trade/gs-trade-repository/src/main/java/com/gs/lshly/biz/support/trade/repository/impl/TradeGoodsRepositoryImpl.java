package com.gs.lshly.biz.support.trade.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.trade.entity.TradeGoods;
import com.gs.lshly.biz.support.trade.mapper.TradeGoodsMapper;
import com.gs.lshly.biz.support.trade.repository.ITradeGoodsRepository;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.MerchantHomeDashboardDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.MerchantHomeDashboardVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
* <p>
 *  服务实现类
 * </p>
*
* @author oy
 * @since 2020-10-30
*/
@Service
public class TradeGoodsRepositoryImpl extends ServiceImpl<TradeGoodsMapper, TradeGoods> implements ITradeGoodsRepository {

    @Autowired
    private TradeGoodsMapper tradeGoodsMapper;
    @Override
    public List<PCMerchTradeListVO.innerGoodsIdAndName> selectTradeIng(QueryWrapper<TradeGoods> qw) {
        return tradeGoodsMapper.selectTradeIng(qw);
    }

    @Override
    public List<MerchantHomeDashboardVO.GoodsInfo> selectGoodsInfo(QueryWrapper<MerchantHomeDashboardDTO.ETO> qw) {
        return tradeGoodsMapper.selectGoodsInfo(qw);
    }
}