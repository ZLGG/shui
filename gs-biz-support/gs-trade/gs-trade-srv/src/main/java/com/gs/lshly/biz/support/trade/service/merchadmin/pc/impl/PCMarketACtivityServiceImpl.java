package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gs.lshly.biz.support.trade.entity.MarketPtActivity;
import com.gs.lshly.biz.support.trade.repository.IMarketPtActivityRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMarketACtivityService;
import com.gs.lshly.common.struct.platadmin.trade.qto.MarketPtActivityQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtActivityVO;

@Component
public class PCMarketACtivityServiceImpl implements IPCMarketACtivityService {
    @Autowired
    private IMarketPtActivityRepository iMarketPtActivityRepository;
    @Override
    public List<MarketPtActivityVO.ListVO> list(MarketPtActivityQTO.QTO qot) {
        List<MarketPtActivity> list = iMarketPtActivityRepository.list();
        List<MarketPtActivityVO.ListVO> marketPtActivityVOS = new ArrayList<>();
        for (MarketPtActivity marketPtActivity:list){
            MarketPtActivityVO.ListVO marketPtActivityVO = new MarketPtActivityVO.ListVO();
            BeanUtils.copyProperties(marketPtActivity,marketPtActivityVO);
            marketPtActivityVOS.add(marketPtActivityVO);
        }
        return marketPtActivityVOS;
    }


}
