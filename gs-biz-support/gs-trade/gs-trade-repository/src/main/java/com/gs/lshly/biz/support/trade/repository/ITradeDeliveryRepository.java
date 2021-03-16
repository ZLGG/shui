package com.gs.lshly.biz.support.trade.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.lshly.biz.support.trade.entity.TradeDelivery;
import com.gs.lshly.common.struct.merchadmin.h5.trade.qto.H5MerchTradeDeliveryQTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeDeliveryVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeDeliveryQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeDeliveryVO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeDeliveryQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeDeliveryVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 订单发货表 服务类
 * </p>
 *
 * @author oy
 * @since 2020-11-16
 */
public interface ITradeDeliveryRepository extends IService<TradeDelivery> {

    IPage<PCMerchTradeDeliveryVO.ListVO> selectListPage(IPage<PCMerchTradeDeliveryVO.ListVO> page, @Param(Constants.WRAPPER) QueryWrapper<PCMerchTradeDeliveryQTO.QTO> qw);
    IPage<H5MerchTradeDeliveryVO.ListVO> selectH5ListPage(IPage<H5MerchTradeDeliveryVO.ListVO> page, @Param(Constants.WRAPPER) QueryWrapper<H5MerchTradeDeliveryQTO.QTO> qw);

    IPage<TradeDeliveryVO.ListVO> selectListPageForPlatform(IPage<TradeDeliveryVO.ListVO> page, @Param(Constants.WRAPPER) QueryWrapper<TradeDeliveryQTO.QTO> qw);

}
