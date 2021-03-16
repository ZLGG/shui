package com.gs.lshly.biz.support.trade.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantCut;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5MarketActivityQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5MarketActivityVO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbMarketActivityQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketActivityVO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcMarketActivityQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketActivityVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商家满减促销 服务类
 * </p>
 *
 * @author zdf
 * @since 2020-12-08
 */
public interface IMarketMerchantCutRepository extends IService<MarketMerchantCut> {
    IPage<PCBbbMarketActivityVO.cutVO> selectCutListPage(IPage<PCBbbMarketActivityVO.cutVO> pager, @Param(Constants.WRAPPER)QueryWrapper<PCBbbMarketActivityQTO.QTO> qw);

    IPage<BbbH5MarketActivityVO.cutVO> selectCutH5ListPage(IPage<BbbH5MarketActivityVO.cutVO> pager, @Param(Constants.WRAPPER)QueryWrapper<BbbH5MarketActivityQTO.QTO> qw);

    IPage<BbcMarketActivityVO.cutVO> selectCutBbcH5ListPage(IPage<BbcMarketActivityVO.cutVO> pager, @Param(Constants.WRAPPER)QueryWrapper<BbcMarketActivityQTO.QTO> qw);

}
