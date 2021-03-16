package com.gs.lshly.biz.support.trade.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantDiscount;
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
 * 商家满折促销 Mapper 接口
 * </p>
 *
 * @author zdf
 * @since 2020-12-09
 */
public interface MarketMerchantDiscountMapper extends BaseMapper<MarketMerchantDiscount> {

    @Select("SELECT goods.goods_id goods_id,goods.discount_id id,goods.shop_id shop_id,discount.scount_rule scount_rule,discount.scount_name scount_name FROM gs_market_merchant_discount_goods goods LEFT JOIN gs_market_merchant_discount discount ON goods.discount_id = discount.id WHERE discount.state = 20 AND discount.flag = 0 AND goods.flag = 0 AND ${ew.sqlSegment}")
    IPage<PCBbbMarketActivityVO.discountVO> selectDiscountListPage(IPage<PCBbbMarketActivityVO.discountVO> pager, @Param(Constants.WRAPPER) QueryWrapper<PCBbbMarketActivityQTO.QTO> qw);
    @Select("SELECT goods.goods_id goods_id,goods.discount_id id,goods.shop_id shop_id,discount.scount_rule scount_rule,discount.scount_name scount_name FROM gs_market_merchant_discount_goods goods LEFT JOIN gs_market_merchant_discount discount ON goods.discount_id = discount.id WHERE discount.state = 20 AND discount.flag = 0 AND goods.flag = 0 AND ${ew.sqlSegment}")
    IPage<BbbH5MarketActivityVO.discountVO> selectDiscountH5ListPage(IPage<BbbH5MarketActivityVO.discountVO> pager, @Param(Constants.WRAPPER) QueryWrapper<BbbH5MarketActivityQTO.QTO> qw);
    @Select("SELECT goods.goods_id goods_id,goods.discount_id id,goods.shop_id shop_id,discount.scount_rule scount_rule,discount.scount_name scount_name FROM gs_market_merchant_discount_goods goods LEFT JOIN gs_market_merchant_discount discount ON goods.discount_id = discount.id WHERE discount.state = 20 AND discount.flag = 0 AND goods.flag = 0 AND ${ew.sqlSegment}")
    IPage<BbcMarketActivityVO.discountVO> selectDiscountBbcH5ListPage(IPage<BbcMarketActivityVO.discountVO> pager, @Param(Constants.WRAPPER) QueryWrapper<BbcMarketActivityQTO.QTO> qw);
}
