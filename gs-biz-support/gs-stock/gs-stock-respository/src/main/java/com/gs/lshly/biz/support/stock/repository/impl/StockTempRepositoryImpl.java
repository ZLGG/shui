package com.gs.lshly.biz.support.stock.repository.impl;

import com.gs.lshly.biz.support.stock.entity.StockTemp;
import com.gs.lshly.biz.support.stock.mapper.StockTempMapper;
import com.gs.lshly.biz.support.stock.repository.IStockTempRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 库存管理临时表 服务实现类
 * </p>
 *
 * @author chenyang
 * @since 2021-06-11
 */
@Service
public class StockTempRepositoryImpl extends ServiceImpl<StockTempMapper, StockTemp> implements IStockTempRepository {

}
