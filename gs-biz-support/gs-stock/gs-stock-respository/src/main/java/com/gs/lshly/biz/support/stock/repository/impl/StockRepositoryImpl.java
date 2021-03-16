package com.gs.lshly.biz.support.stock.repository.impl;

import com.gs.lshly.biz.support.stock.entity.Stock;
import com.gs.lshly.biz.support.stock.mapper.StockMapper;
import com.gs.lshly.biz.support.stock.repository.IStockRepository;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
* <p>
 *  服务实现类
 * </p>
*
* @author zhaozhigang
 * @since 2020-10-22
*/
@Service
public class StockRepositoryImpl extends ServiceImpl<StockMapper, Stock> implements IStockRepository {

}