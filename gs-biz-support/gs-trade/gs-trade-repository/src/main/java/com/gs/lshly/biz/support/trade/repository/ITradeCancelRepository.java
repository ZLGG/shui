package com.gs.lshly.biz.support.trade.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.trade.entity.TradeCancel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeDeliveryQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeDeliveryVO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeCancelQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeCancelVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 交易订单取消表 服务类
 * </p>
 *
 * @author oy
 * @since 2020-11-20
 */
public interface ITradeCancelRepository extends IService<TradeCancel> {

    IPage<TradeCancelVO.ListVO> selectListPage(IPage<TradeCancelVO.ListVO> page, @Param(Constants.WRAPPER) QueryWrapper<TradeCancelQTO.QTO> qw);
    IPage<TradeCancel> selectCancelListPage(IPage<TradeCancel> page, @Param(Constants.WRAPPER) QueryWrapper<TradeCancel> qw);

}
