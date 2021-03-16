package com.gs.lshly.biz.support.trade.service.bbb.pc.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantCard;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantCardRepository;
import com.gs.lshly.biz.support.trade.service.bbb.pc.IBbbPcTradeCardListService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbMarketMerchantCardDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbMarketMerchantCardVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BbbPcTradeCardListServiceImpl implements IBbbPcTradeCardListService {
    @Autowired
    private IMarketMerchantCardRepository repository;
    @Override
    public List<BbbMarketMerchantCardVO.ListVO> innerCardList(BbbMarketMerchantCardDTO.IdsDTO dto) {
        List<MarketMerchantCard> marketMerchantCards = repository.listByIds(dto.getIds());
        if(ObjectUtils.isEmpty(marketMerchantCards)){
            throw new BusinessException("没有数据");
        }
        List<BbbMarketMerchantCardVO.ListVO> ListVO= new ArrayList<>();
        for (MarketMerchantCard card:marketMerchantCards){
            BbbMarketMerchantCardVO.ListVO listVO = new BbbMarketMerchantCardVO.ListVO();
            BeanUtils.copyProperties(card,listVO);
            ListVO.add(listVO);
        }
        return ListVO;
    }
}
