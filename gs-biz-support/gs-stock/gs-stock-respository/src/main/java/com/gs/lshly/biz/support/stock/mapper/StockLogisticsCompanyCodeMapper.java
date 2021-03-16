package com.gs.lshly.biz.support.stock.mapper;

import com.gs.lshly.biz.support.stock.entity.StockLogisticsCompanyCode;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzg
 * @since 2020-10-30
 */
public interface StockLogisticsCompanyCodeMapper extends BaseMapper<StockLogisticsCompanyCode> {

    @Select("select a.id, a.`code`,a.logistics_company_ame from  gs_stock_logistics_company_code  a ")
    List<StockLogisticsCompanyCode>  provideLogisticsCode();
}
