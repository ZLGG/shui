package com.gs.lshly.biz.support.stock.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gs.lshly.biz.support.stock.entity.StockShopLogisticsCorp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.stock.mapper.view.StockRoliceView;
import com.gs.lshly.biz.support.stock.mapper.view.StockShopLogisticsCorpView;
import com.gs.lshly.common.struct.merchadmin.pc.stock.vo.PCMerchStockShopLogisticsCorpVO;
import com.gs.lshly.common.struct.platadmin.stock.vo.StockLogisticsCorpVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 * 店铺支持物流 Mapper 接口
 * </p>
 *
 * @author R
 * @since 2020-10-24
 */
@Repository
public interface StockShopLogisticsCorpMapper extends BaseMapper<StockShopLogisticsCorp> {

    @Select("select  a.id,b.name,a.state  from  gs_stock_shop_logistics_corp a inner join gs_stock_logistics_corp b on a.logistics_corp_id  = b.id")
    IPage<PCMerchStockShopLogisticsCorpVO.ListVO> queryAll(Page<?> page);

    @Select("select lc.*,sc.shop_id,sc.merchant_id " +
            "from gs_stock_shop_logistics_corp sc " +
            "left join gs_stock_logistics_corp lc on sc.logistics_corp_id = lc.id " +
            "WHERE lc.flag = 0 and sc.flag = 0 and ${ew.sqlSegment}")
    List<StockShopLogisticsCorpView> mapperListShopLogisticsCorp(@Param(value = "ew") QueryWrapper<StockShopLogisticsCorpView> we);

}
