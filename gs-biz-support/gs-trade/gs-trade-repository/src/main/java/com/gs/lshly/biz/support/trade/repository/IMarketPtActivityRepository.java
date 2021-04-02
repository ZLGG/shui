package com.gs.lshly.biz.support.trade.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.lshly.biz.support.trade.entity.MarketPtActivity;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5MarketActivityDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5MarketActivityQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5MarketActivityVO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbMarketActivityDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbMarketActivityQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketActivityVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcMarketActivityDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcMarketActivityQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketActivityVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 平台活动 服务类
 * </p>
 *
 * @author zdf
 * @since 2020-11-28
 */
public interface IMarketPtActivityRepository extends IService<MarketPtActivity> {

    IPage<PCBbbMarketActivityVO.activityGoodsVO> selectActivityPageData(IPage<PCBbbMarketActivityVO.activityGoodsVO> pager,@Param(Constants.WRAPPER)  QueryWrapper<BbbMarketActivityDTO.IdDTO> qw);
    IPage<BbbH5MarketActivityVO.activityGoodsVO> selectActivityPageDataH5(IPage<BbbH5MarketActivityVO.activityGoodsVO> pager, @Param(Constants.WRAPPER)  QueryWrapper<BbbH5MarketActivityDTO.IdDTO> qw);
    IPage<BbcMarketActivityVO.activityGoodsVO> selectActivityPageDataBbcH5(IPage<BbcMarketActivityVO.activityGoodsVO> pager, @Param(Constants.WRAPPER)  QueryWrapper<BbcMarketActivityDTO.IdDTO> qw);

    IPage<PCBbbMarketActivityVO.activityListPageVO> activityListPage(IPage<PCBbbMarketActivityVO.activityListPageVO> pager,@Param(Constants.WRAPPER) QueryWrapper<PCBbbMarketActivityQTO.QTO> qw);

    IPage<BbbH5MarketActivityVO.activityListPageVO> activityListBbbh5Page(IPage<BbbH5MarketActivityVO.activityListPageVO> pager,@Param(Constants.WRAPPER) QueryWrapper<BbbH5MarketActivityQTO.QTO> qw);

    IPage<BbcMarketActivityVO.activityListPageVO> activityListBbcPage(IPage<BbcMarketActivityVO.activityListPageVO> pager, QueryWrapper<BbcMarketActivityQTO.QTO> query);
}
