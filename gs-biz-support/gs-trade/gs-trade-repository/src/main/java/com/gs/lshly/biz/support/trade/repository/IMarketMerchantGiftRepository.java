package com.gs.lshly.biz.support.trade.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantGift;
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
 * 商家满赠促销 服务类
 * </p>
 *
 * @author zdf
 * @since 2020-12-09
 */
public interface IMarketMerchantGiftRepository extends IService<MarketMerchantGift> {

    IPage<PCBbbMarketActivityVO.giftVO> selectGiftListPage(IPage<PCBbbMarketActivityVO.giftVO> pager, @Param(Constants.WRAPPER) QueryWrapper<PCBbbMarketActivityQTO.QTO> qw);
    IPage<BbbH5MarketActivityVO.giftVO> selectGiftH5ListPage(IPage<BbbH5MarketActivityVO.giftVO> pager, @Param(Constants.WRAPPER) QueryWrapper<BbbH5MarketActivityQTO.QTO> qw);
    IPage<BbcMarketActivityVO.giftVO> selectGiftBbcH5ListPage(IPage<BbcMarketActivityVO.giftVO> pager, @Param(Constants.WRAPPER) QueryWrapper<BbcMarketActivityQTO.QTO> qw);

}
