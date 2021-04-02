package com.gs.lshly.biz.support.trade.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantCut;
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
 * 商家满减促销 Mapper 接口
 * </p>
 *
 * @author zdf
 * @since 2020-12-08
 */
public interface MarketMerchantCutMapper extends BaseMapper<MarketMerchantCut> {
    @Select("SELECT goods.goods_id goods_id,goods.cut_id cut_id,goods.shop_id shop_id,cut.cut_rule cut_rule,cut.cut_name cut_name,cut.is_not_max is_not_max  FROM `gs_market_merchant_cut_goods` goods LEFT JOIN gs_market_merchant_cut cut  on goods.cut_id=cut.id LEFT JOIN gs_goods_info gg on goods.goods_id=gg.id where gg.goods_state=20 AND cut.state=20 AND cut.flag=0 And goods.flag=0 AND ${ew.sqlSegment}")
    IPage<PCBbbMarketActivityVO.cutVO> selectCutListPage(IPage<PCBbbMarketActivityVO.cutVO> pager, @Param(Constants.WRAPPER)QueryWrapper<PCBbbMarketActivityQTO.QTO> qw);
    @Select("SELECT goods.goods_id goods_id,goods.cut_id cut_id,goods.shop_id shop_id,cut.cut_rule cut_rule,cut.cut_name cut_name,cut.is_not_max is_not_max  FROM `gs_market_merchant_cut_goods` goods LEFT JOIN gs_market_merchant_cut cut  on goods.cut_id=cut.id LEFT JOIN gs_goods_info gg on goods.goods_id=gg.id where gg.goods_state=20 AND cut.state=20 AND cut.flag=0 And goods.flag=0 AND ${ew.sqlSegment}")
    IPage<BbbH5MarketActivityVO.cutVO> selectCutH5ListPage(IPage<BbbH5MarketActivityVO.cutVO> pager,@Param(Constants.WRAPPER) QueryWrapper<BbbH5MarketActivityQTO.QTO> qw);
    @Select("SELECT goods.goods_id goods_id,goods.cut_id cut_id,goods.shop_id shop_id,cut.cut_rule cut_rule,cut.cut_name cut_name,cut.is_not_max is_not_max  FROM `gs_market_merchant_cut_goods` goods LEFT JOIN gs_market_merchant_cut cut  on goods.cut_id=cut.id LEFT JOIN gs_goods_info gg on goods.goods_id=gg.id where gg.goods_state=20 AND cut.state=20 AND cut.flag=0 And goods.flag=0 AND ${ew.sqlSegment}")
    IPage<BbcMarketActivityVO.cutVO> selectCutBbcH5ListPage(IPage<BbcMarketActivityVO.cutVO> pager,@Param(Constants.WRAPPER) QueryWrapper<BbcMarketActivityQTO.QTO> qw);
}
