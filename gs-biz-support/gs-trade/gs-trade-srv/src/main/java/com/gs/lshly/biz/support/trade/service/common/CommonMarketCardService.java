package com.gs.lshly.biz.support.trade.service.common;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantCard;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantCardUsers;
import com.gs.lshly.biz.support.trade.enums.MarketPtCardStatusEnum;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantCardRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantCardUsersRepository;
import com.gs.lshly.biz.support.trade.service.common.Impl.ICommonMarketCardServiceImpl;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommonMarketCardService implements ICommonMarketCardServiceImpl {

    @Autowired
    private IMarketMerchantCardRepository iMarketMerchantCardRepository;
    @Autowired
    private IMarketMerchantCardUsersRepository iMarketMerchantCardUsersRepository;


    @Override
    public void useCard(String cardId,String userId) {
        if (ObjectUtils.isNotEmpty(cardId)){
            MarketMerchantCardUsers marketMerchantCardUsers = iMarketMerchantCardUsersRepository.getById(cardId);
            if (ObjectUtils.isNotEmpty(marketMerchantCardUsers)){
                marketMerchantCardUsers.setState(MarketPtCardStatusEnum.已使用.getCode());
                MarketMerchantCard marketMerchantCard = iMarketMerchantCardRepository.getById(marketMerchantCardUsers.getCardId());
                if (ObjectUtils.isNotEmpty(marketMerchantCard)){
                    marketMerchantCard.setUsedTotal(marketMerchantCard.getUsedTotal()+1);
                    iMarketMerchantCardRepository.updateById(marketMerchantCard);
                }
                iMarketMerchantCardUsersRepository.updateById(marketMerchantCardUsers);
            }
        }
    }
}
