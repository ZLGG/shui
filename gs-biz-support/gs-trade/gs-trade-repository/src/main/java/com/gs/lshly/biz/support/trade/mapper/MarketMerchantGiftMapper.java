package com.gs.lshly.biz.support.trade.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantGift;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5MarketActivityQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5MarketActivityVO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbMarketActivityQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketActivityVO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcMarketActivityQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketActivityVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 商家满赠促销 Mapper 接口
 * </p>
 *
 * @author zdf
 * @since 2020-12-09
 */
public interface MarketMerchantGiftMapper extends BaseMapper<MarketMerchantGift> {
    @Select("select goods.goods_id goods_id,goods.gift_id id,goods.shop_id shop_id,gift.gift_name gift_name,gift.scount_rule scount_rule  from gs_market_merchant_gift_goods goods LEFT JOIN gs_market_merchant_gift gift on goods.gift_id=gift.id  where gift.state=20 AND gift.flag=0 And goods.flag=0 AND ${ew.sqlSegment}")
    IPage<PCBbbMarketActivityVO.giftVO> selectGiftListPage(IPage<PCBbbMarketActivityVO.giftVO> pager,@Param(Constants.WRAPPER) QueryWrapper<PCBbbMarketActivityQTO.QTO> qw);
    @Select("select goods.goods_id goods_id,goods.gift_id id,goods.shop_id shop_id,gift.gift_name gift_name,gift.scount_rule scount_rule  from gs_market_merchant_gift_goods goods LEFT JOIN gs_market_merchant_gift gift on goods.gift_id=gift.id  where gift.state=20 AND gift.flag=0 And goods.flag=0 AND ${ew.sqlSegment}")
    IPage<BbbH5MarketActivityVO.giftVO> selectGiftH5ListPage(IPage<BbbH5MarketActivityVO.giftVO> pager, @Param(Constants.WRAPPER)QueryWrapper<BbbH5MarketActivityQTO.QTO> qw);
    @Select("select goods.goods_id goods_id,goods.gift_id id,goods.shop_id shop_id,gift.gift_name gift_name,gift.scount_rule scount_rule  from gs_market_merchant_gift_goods goods LEFT JOIN gs_market_merchant_gift gift on goods.gift_id=gift.id  where gift.state=20 AND gift.flag=0 And goods.flag=0 AND ${ew.sqlSegment}")
    IPage<BbcMarketActivityVO.giftVO> selectGiftBbcH5ListPage(IPage<BbcMarketActivityVO.giftVO> pager,@Param(Constants.WRAPPER) QueryWrapper<BbcMarketActivityQTO.QTO> qw);
}
