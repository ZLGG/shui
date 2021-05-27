package com.gs.lshly.biz.support.stock.service.bbc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.stock.entity.Stock;
import com.gs.lshly.biz.support.stock.repository.IStockRepository;
import com.gs.lshly.biz.support.stock.service.bbc.IBbcStockService;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

import lombok.extern.slf4j.Slf4j;

/**
* <p>
*  门店物流服务实现类
* </p>
* @author lxus
* @since 2020-11-02
*/
@Component
@Slf4j
public class BbcStockServiceImpl implements IBbcStockService {

    @Autowired
    private IStockRepository stockRepository;

	@Override
	public Integer getQuantity(String skuId) {
		QueryWrapper<Stock> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("sku_id",skuId);
        Stock stock = stockRepository.getOne(queryWrapper);
        if(stock==null)
        	return 0;
        return stock.getQuantity();
	}

    

}
