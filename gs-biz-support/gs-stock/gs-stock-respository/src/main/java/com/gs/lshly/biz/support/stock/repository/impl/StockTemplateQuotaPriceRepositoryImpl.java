package com.gs.lshly.biz.support.stock.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.stock.entity.StockTemplateQuota;
import com.gs.lshly.biz.support.stock.entity.StockTemplateQuotaPrice;
import com.gs.lshly.biz.support.stock.mapper.StockTemplateQuotaMapper;
import com.gs.lshly.biz.support.stock.mapper.StockTemplateQuotaPriceMapper;
import com.gs.lshly.biz.support.stock.repository.IStockTemplateQuotaPriceRepository;
import com.gs.lshly.biz.support.stock.repository.IStockTemplateQuotaRepository;
import org.springframework.stereotype.Service;

/**
* <p>
 *  服务实现类
 * </p>
*
* @author lxus
 * @since 2020-10-26
*/
@Service
public class StockTemplateQuotaPriceRepositoryImpl extends ServiceImpl<StockTemplateQuotaPriceMapper, StockTemplateQuotaPrice> implements IStockTemplateQuotaPriceRepository {

}