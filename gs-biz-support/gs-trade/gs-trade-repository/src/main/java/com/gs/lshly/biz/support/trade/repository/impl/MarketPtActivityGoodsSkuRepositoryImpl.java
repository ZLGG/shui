package com.gs.lshly.biz.support.trade.repository.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.trade.entity.MarketPtActivityGoodsSku;
import com.gs.lshly.biz.support.trade.mapper.MarketPtActivityGoodsSkuMapper;
import com.gs.lshly.biz.support.trade.repository.IMarketPtActivityGoodsSkuRepository;
import org.springframework.stereotype.Service;

/**
* <p>
 *  服务实现类
 * </p>
*
* @author zdf
 * @since 2020-12-02
*/
@Service
public class MarketPtActivityGoodsSkuRepositoryImpl extends ServiceImpl<MarketPtActivityGoodsSkuMapper, MarketPtActivityGoodsSku> implements IMarketPtActivityGoodsSkuRepository {

    @Override
    public MarketPtActivityGoodsSkuMapper baseMapper() {
        return getBaseMapper();
    }
}