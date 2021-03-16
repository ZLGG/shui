package com.gs.lshly.biz.support.trade.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.trade.entity.TradeGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.MerchantHomeDashboardDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.MerchantHomeDashboardVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeListVO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeDeliveryQTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author oy
 * @since 2020-10-30
 */
public interface ITradeGoodsRepository extends IService<TradeGoods> {

    List<PCMerchTradeListVO.innerGoodsIdAndName> selectTradeIng(@Param(Constants.WRAPPER) QueryWrapper<TradeGoods> qw);

    List<MerchantHomeDashboardVO.GoodsInfo> selectGoodsInfo(@Param(Constants.WRAPPER) QueryWrapper<MerchantHomeDashboardDTO.ETO> query1);
}
