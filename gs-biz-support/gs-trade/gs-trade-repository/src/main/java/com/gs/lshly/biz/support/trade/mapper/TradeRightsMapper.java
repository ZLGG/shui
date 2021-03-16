package com.gs.lshly.biz.support.trade.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
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
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 售后表 Mapper 接口
 * </p>
 *
 * @author oy
 * @since 2020-12-06
 */
public interface TradeRightsMapper extends BaseMapper<TradeRights> {

    @Select("SELECT * FROM `gs_trade_rights` r WHERE r.`flag`=0 AND ${ew.sqlSegment}")
    IPage<BbcTradeRightsVO.ListVO> selectTradeRightsList(IPage<BbcTradeRightsVO.ListVO> page, @Param(Constants.WRAPPER) QueryWrapper<BbcTradeRightsQTO.RightsList> qw);
    @Select("SELECT * FROM `gs_trade_rights` r WHERE r.`flag`=0 AND ${ew.sqlSegment}")
    IPage<BbbTradeRightsVO.ListVO> selectBbbTradeRightsList(IPage<BbbTradeRightsVO.ListVO> page, @Param(Constants.WRAPPER) QueryWrapper<BbbTradeRightsQTO.RightsList> qw);
    @Select("SELECT t.* \n"+
            " FROM `gs_trade_rights` t " +
            " LEFT JOIN `gs_trade` tg on t.trade_id=tg.id " +
            " WHERE t.`flag`=0 AND tg.`flag`=0 AND ${ew.sqlSegment}")
    IPage<PCMerchTradeRightsVO.ListVO>  selectMerchRightList(IPage<PCMerchTradeRightsVO.ListVO> page,@Param(Constants.WRAPPER) QueryWrapper<PCMerchTradeRightsQTO.QTO> qw);
    @Select("SELECT * FROM `gs_trade_rights` r WHERE r.`flag`=0 AND ${ew.sqlSegment}")
    IPage<BbbH5TradeRightsVO.ListVO> selectBbbH5TradeRightsList(IPage<BbbH5TradeRightsVO.ListVO> page,@Param(Constants.WRAPPER) QueryWrapper<BbbH5TradeRightsQTO.RightsList> qw);

    @Select("SELECT t.* FROM gs_trade_rights t \n" +
            " LEFT JOIN gs_trade tg on t.trade_id=tg.id  " +
            " WHERE t.flag=0 AND tg.flag=0  AND ${ew.sqlSegment}")
    IPage<H5MerchTradeRightsVO.ListVO>  selectMerchH5RightList(IPage<H5MerchTradeRightsVO.ListVO> page, @Param(Constants.WRAPPER) QueryWrapper<H5MerchTradeRightsQTO.QTO> qw);
    @Select("SELECT tr.* FROM gs_trade_rights tr LEFT JOIN gs_trade t ON tr.trade_id=t.id WHERE t.flag=0 AND tr.flag=0  AND ${ew.sqlSegment} ")
    IPage<TradeRights> selectPlatadminTradeRightsList(IPage<TradeRights> page, @Param(Constants.WRAPPER)QueryWrapper<TradeRights> qw);
}
