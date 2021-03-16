package com.gs.lshly.biz.support.trade.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.trade.entity.TradeDelivery;
import com.gs.lshly.common.struct.merchadmin.h5.trade.qto.H5MerchTradeDeliveryQTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeDeliveryVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeDeliveryQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeDeliveryVO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeDeliveryQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeDeliveryVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 订单发货表 Mapper 接口
 * </p>
 *
 * @author oy
 * @since 2020-11-16
 */
@Repository
public interface TradeDeliveryMapper extends BaseMapper<TradeDelivery> {

    @Select("SELECT td.*,t.`trade_code`,t.`trade_state` " +
            "FROM `gs_trade_delivery` td " +
            "LEFT JOIN `gs_trade` t ON t.id=td.`trade_id` " +
            "WHERE td.`flag`=0 AND t.`flag`=0 AND ${ew.sqlSegment}")
    IPage<PCMerchTradeDeliveryVO.ListVO> selectListPage(IPage<PCMerchTradeDeliveryVO.ListVO> page, @Param(Constants.WRAPPER) QueryWrapper<PCMerchTradeDeliveryQTO.QTO> qw);

    @Select("SELECT td.*,t.`trade_code`,t.`trade_state` " +
            "FROM `gs_trade_delivery` td " +
            "LEFT JOIN `gs_trade` t ON t.id=td.`trade_id` " +
            "WHERE td.`flag`=0 AND t.`flag`=0 AND ${ew.sqlSegment}")
    IPage<TradeDeliveryVO.ListVO> selectListPageForPlatform(IPage<TradeDeliveryVO.ListVO> page, @Param(Constants.WRAPPER) QueryWrapper<TradeDeliveryQTO.QTO> qw);
    @Select("SELECT td.*,t.`trade_code`,t.`trade_state` " +
            "FROM `gs_trade_delivery` td " +
            "LEFT JOIN `gs_trade` t ON t.id=td.`trade_id` " +
            "WHERE td.`flag`=0 AND t.`flag`=0 AND ${ew.sqlSegment}")
    IPage<H5MerchTradeDeliveryVO.ListVO> selectH5ListPage(IPage<H5MerchTradeDeliveryVO.ListVO> page, @Param(Constants.WRAPPER)QueryWrapper<H5MerchTradeDeliveryQTO.QTO> qw);
}
