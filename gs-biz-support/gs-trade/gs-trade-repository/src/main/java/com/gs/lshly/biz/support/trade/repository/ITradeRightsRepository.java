package com.gs.lshly.biz.support.trade.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.lshly.biz.support.trade.entity.TradeRights;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5TradeRightsQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeRightsVO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.BbbTradeRightsQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbTradeRightsVO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeRightsQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeRightsVO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.qto.H5MerchTradeRightsQTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeRightsVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeRightsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeRightsVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 售后表 服务类
 * </p>
 *
 * @author oy
 * @since 2020-12-06
 */
public interface ITradeRightsRepository extends IService<TradeRights> {

    IPage<BbcTradeRightsVO.ListVO> selectTradeRightsList(IPage<BbcTradeRightsVO.ListVO> page, @Param(Constants.WRAPPER) QueryWrapper<BbcTradeRightsQTO.RightsList> qw);
    IPage<BbbH5TradeRightsVO.ListVO> selectBbbH5TradeRightsList(IPage<BbbH5TradeRightsVO.ListVO> page, @Param(Constants.WRAPPER) QueryWrapper<BbbH5TradeRightsQTO.RightsList> qw);
    IPage<BbbTradeRightsVO.ListVO> selectBbbTradeRightsList(IPage<BbbTradeRightsVO.ListVO> page, @Param(Constants.WRAPPER) QueryWrapper<BbbTradeRightsQTO.RightsList> qw);
    IPage<PCMerchTradeRightsVO.ListVO> selectMerchRightList(IPage<PCMerchTradeRightsVO.ListVO> page, @Param(Constants.WRAPPER)  QueryWrapper<PCMerchTradeRightsQTO.QTO>  qw);
    IPage<TradeRights> selectPlatadminTradeRightsList(IPage<TradeRights> page, @Param(Constants.WRAPPER) QueryWrapper<TradeRights> qw);
    IPage<H5MerchTradeRightsVO.ListVO> selectMerchH5RightList(IPage<H5MerchTradeRightsVO.ListVO> page, @Param(Constants.WRAPPER)  QueryWrapper<H5MerchTradeRightsQTO.QTO>  qw);
}
