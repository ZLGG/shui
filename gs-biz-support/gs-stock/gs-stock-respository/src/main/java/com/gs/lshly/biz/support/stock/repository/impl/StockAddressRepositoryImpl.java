package com.gs.lshly.biz.support.stock.repository.impl;

import com.gs.lshly.biz.support.stock.entity.StockAddress;
import com.gs.lshly.biz.support.stock.mapper.StockAddressMapper;
import com.gs.lshly.biz.support.stock.repository.IStockAddressRepository;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
* <p>
 *  服务实现类
 * </p>
*
* @author zzg
 * @since 2020-11-02
*/
@Service
public class StockAddressRepositoryImpl extends ServiceImpl<StockAddressMapper, StockAddress> implements IStockAddressRepository {

}