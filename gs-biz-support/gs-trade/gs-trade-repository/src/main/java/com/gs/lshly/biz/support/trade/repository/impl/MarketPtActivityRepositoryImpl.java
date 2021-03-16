package com.gs.lshly.biz.support.trade.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.trade.entity.MarketPtActivity;
import com.gs.lshly.biz.support.trade.mapper.MarketPtActivityMapper;
import com.gs.lshly.biz.support.trade.repository.IMarketPtActivityRepository;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5MarketActivityDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5MarketActivityVO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbMarketActivityDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketActivityVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcMarketActivityDTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketActivityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
* <p>
 *  服务实现类
 * </p>
*
* @author zdf
 * @since 2020-11-28
*/
@Service
public class MarketPtActivityRepositoryImpl extends ServiceImpl<MarketPtActivityMapper, MarketPtActivity> implements IMarketPtActivityRepository {

    @Autowired
    private MarketPtActivityMapper marketPtActivityMapper;
    @Override
    public IPage<PCBbbMarketActivityVO.activityGoodsVO> selectActivityPageData(IPage<PCBbbMarketActivityVO.activityGoodsVO> pager, QueryWrapper<BbbMarketActivityDTO.IdDTO> qw) {
        return marketPtActivityMapper.selectActivityPageData(pager,qw);
    }

    @Override
    public IPage<BbbH5MarketActivityVO.activityGoodsVO> selectActivityPageDataH5(IPage<BbbH5MarketActivityVO.activityGoodsVO> pager, QueryWrapper<BbbH5MarketActivityDTO.IdDTO> qw) {
        return marketPtActivityMapper.selectActivityPageDataH5(pager,qw);
    }

    @Override
    public IPage<BbcMarketActivityVO.activityGoodsVO> selectActivityPageDataBbcH5(IPage<BbcMarketActivityVO.activityGoodsVO> pager, QueryWrapper<BbcMarketActivityDTO.IdDTO> qw) {
        return marketPtActivityMapper.selectActivityPageDataBbcH5(pager,qw);
    }
}