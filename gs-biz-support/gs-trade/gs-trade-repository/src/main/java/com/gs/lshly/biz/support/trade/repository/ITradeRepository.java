package com.gs.lshly.biz.support.trade.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.lshly.biz.support.trade.entity.Trade;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5TradeQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeListVO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.BbbOrderQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbTradeListVO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeListVO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.qto.H5MerchTradeQTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeListVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.MerchantHomeDashboardDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeListVO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author oy
 * @since 2020-10-28
 */
public interface ITradeRepository extends IService<Trade> {

    List<BbcTradeListVO.tradeVO> selectTradeListPage(@Param(Constants.WRAPPER) QueryWrapper<BbcTradeQTO.TradeList> qw);

    List<BbbH5TradeListVO.tradeVO>  BbbH5selectTradeListPage( @Param(Constants.WRAPPER) QueryWrapper<BbbH5TradeQTO.TradeList> qw);

    List<BbcTradeListVO.stateCountVO> selectTradeStateCount(@Param(Constants.WRAPPER) QueryWrapper<BbcTradeQTO> qw);

    List<BbbH5TradeListVO.stateCountVO> BbbH5selectTradeStateCount(@Param(Constants.WRAPPER) QueryWrapper<BbbH5TradeQTO> qw);

    IPage<PCMerchTradeListVO.tradeVO> selectPCMerchTradePage(IPage<PCMerchTradeListVO.tradeVO> page, @Param(Constants.WRAPPER) QueryWrapper<PCMerchTradeQTO.TradeList> qw);

    IPage<H5MerchTradeListVO.tradeVO> selectH5MerchTradePage(IPage<H5MerchTradeListVO.tradeVO> page, @Param(Constants.WRAPPER) QueryWrapper<H5MerchTradeQTO.TradeList> qw);

    IPage<TradeListVO.tradeVO> selectTradePage(IPage<TradeListVO.tradeVO> page, @Param(Constants.WRAPPER) QueryWrapper<TradeQTO.TradeList> qw);

    IPage<BbbTradeListVO.tradeVO> selectBbbTradeListPage(IPage<BbbTradeListVO.tradeVO> page, @Param(Constants.WRAPPER) QueryWrapper<BbbOrderQTO.TradeList> qw);

    List<Trade> yesterdayTrade(@Param(Constants.WRAPPER) QueryWrapper<MerchantHomeDashboardDTO.ETO> queryWrapper);

    BbbTradeListVO.InnerGoodsScore selectShopScore( QueryWrapper<Object> queryWrapper);

    BbbTradeListVO.InnerGoodsScore selectGoodScore( QueryWrapper<Object> queryWrapper);
}
