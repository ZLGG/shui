package com.gs.lshly.biz.support.trade.service.bbb.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.enums.MarketPtCardStatusEnum;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantCardUsersRepository;
import com.gs.lshly.biz.support.trade.service.bbb.h5.IBbbH5MarketMerchantCardUsersService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5MarketMerchantCardUsersQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5MarketMerchantCardUsersVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


/**
* <p>
*  服务实现类
* </p>
* @author zdf
* @since 2021-01-08
*/
@Component
public class BbbH5MarketMerchantCardUsersServiceImpl implements IBbbH5MarketMerchantCardUsersService {

    @Autowired
    private IMarketMerchantCardUsersRepository repository;

    @Override
    public PageData<BbbH5MarketMerchantCardUsersVO.PageData> pageData(BbbH5MarketMerchantCardUsersQTO.QTO qto) {
        QueryWrapper<BbbH5MarketMerchantCardUsersQTO.QTO> query = MybatisPlusUtil.query();
        IPage<BbbH5MarketMerchantCardUsersVO.PageData> pager = MybatisPlusUtil.pager(qto);
        query.and(i->i.eq("td.`user_id`",qto.getJwtUserId()));
        if (ObjectUtils.isNotEmpty(qto.getState())){
            switch (qto.getState()){
                case 10:query.and(i->i.eq("td.`state`",MarketPtCardStatusEnum.未使用.getCode()));break;
                case 20:query.and(i->i.eq("td.`state`",MarketPtCardStatusEnum.已使用.getCode()));break;
                case 30:query.and(i->i.lt("td.`valid_end_time`",LocalDateTime.now()));break;
            }
        }
        if (ObjectUtils.isNotEmpty(qto.getExpirationTime())){
            switch (qto.getExpirationTime()){
                case 10:query.orderByAsc("td.`valid_end_time`");
                case 20:query.orderByDesc("td.`valid_end_time`");
            }
        }else if (ObjectUtils.isNotEmpty(qto.getPreferentialAmount())){
            switch (qto.getPreferentialAmount()){
                case 10:query.orderByAsc("td.`cut_price`");
                case 20:query.orderByDesc("td.`cut_price`");
            }
        }
        repository.selectH5ListPage(pager,query);
        if (ObjectUtils.isNotEmpty(query) || ObjectUtils.isNotEmpty(pager)){
            return MybatisPlusUtil.toPageData(qto, BbbH5MarketMerchantCardUsersVO.PageData.class, pager);
        }

        return new PageData<>();
    }



}
