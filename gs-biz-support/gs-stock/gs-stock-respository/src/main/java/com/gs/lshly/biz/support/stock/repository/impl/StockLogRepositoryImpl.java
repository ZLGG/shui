package com.gs.lshly.biz.support.stock.repository.impl;

import com.gs.lshly.biz.support.stock.entity.StockLog;
import com.gs.lshly.biz.support.stock.mapper.StockLogMapper;
import com.gs.lshly.biz.support.stock.repository.IStockLogRepository;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
* <p>
 *  服务实现类
 * </p>
*
* @author xxfc
 * @since 2020-11-02
*/
@Service
public class StockLogRepositoryImpl extends ServiceImpl<StockLogMapper, StockLog> implements IStockLogRepository {

}