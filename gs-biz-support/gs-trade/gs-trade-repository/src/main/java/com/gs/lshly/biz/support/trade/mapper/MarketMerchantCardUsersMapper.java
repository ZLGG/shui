package com.gs.lshly.biz.support.trade.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantCardUsers;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5MarketMerchantCardUsersQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5MarketMerchantCardUsersVO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeListVO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbOrderDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbMarketMerchantCardUsersQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbTradeListVO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketMerchantCardUsersVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeListVO;
import com.gs.lshly.common.struct.common.dto.CommonMarketDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 商家优惠卷会员令取记录 Mapper 接口
 * </p>
 *
 * @author zdf
 * @since 2021-01-08
 */
public interface MarketMerchantCardUsersMapper extends BaseMapper<MarketMerchantCardUsers> {
    @Select("SELECT td.*,t.`card_describe` card_describe,t.`card_prefix` card_prefix " +
            "FROM `gs_market_merchant_card_users` td " +
            "LEFT JOIN `gs_market_merchant_card` t ON t.id=td.`card_id` " +
            "WHERE td.`flag`=0 AND t.`flag`=0 AND ${ew.sqlSegment}")
    IPage<PCBbbMarketMerchantCardUsersVO.PageData> selectListPage(IPage<PCBbbMarketMerchantCardUsersVO.PageData> page, @Param(Constants.WRAPPER) QueryWrapper<PCBbbMarketMerchantCardUsersQTO.QTO> qw);
    @Select("SELECT td.*,t.`card_describe` card_describe,t.`card_prefix` card_prefix " +
            "FROM `gs_market_merchant_card_users` td " +
            "LEFT JOIN `gs_market_merchant_card` t ON t.id=td.`card_id` " +
            "WHERE td.`flag`=0 AND t.`flag`=0 AND ${ew.sqlSegment}")
    List<BbbTradeListVO.UseCard> selectUseCard(@Param(Constants.WRAPPER) QueryWrapper<BbbOrderDTO.UseCard> qw);
    @Select("SELECT td.*,t.`card_describe` card_describe,t.`card_prefix` card_prefix " +
            "FROM `gs_market_merchant_card_users` td " +
            "LEFT JOIN `gs_market_merchant_card` t ON t.id=td.`card_id` " +
            "WHERE td.`flag`=0 AND t.`flag`=0 AND ${ew.sqlSegment}")
    IPage<BbcTradeListVO.PageData> selectBbcListPage(IPage<BbcTradeListVO.PageData> pager,@Param(Constants.WRAPPER) QueryWrapper<BbcTradeQTO.UserCardQTO> qw);
    @Select("SELECT td.*,t.`card_describe` card_describe,t.`card_prefix` card_prefix " +
            "FROM `gs_market_merchant_card_users` td " +
            "LEFT JOIN `gs_market_merchant_card` t ON t.id=td.`card_id` " +
            "WHERE td.`flag`=0 AND t.`flag`=0 AND ${ew.sqlSegment}")
    List<BbcTradeListVO.UseCard> selectBbcUseCard(QueryWrapper<BbcTradeDTO.UseCard> qw);
    @Select("SELECT td.*,t.`card_describe` card_describe,t.`card_prefix` card_prefix " +
            "FROM `gs_market_merchant_card_users` td " +
            "LEFT JOIN `gs_market_merchant_card` t ON t.id=td.`card_id` " +
            "WHERE td.`flag`=0 AND t.`flag`=0 AND ${ew.sqlSegment}")
    List<BbbH5TradeListVO.UseCard> selectH5UseCard(QueryWrapper<BbbH5TradeDTO.UseCard> qw);
    @Select("SELECT td.*,t.`card_describe` card_describe,t.`card_prefix` card_prefix " +
            "FROM `gs_market_merchant_card_users` td " +
            "LEFT JOIN `gs_market_merchant_card` t ON t.id=td.`card_id` " +
            "WHERE td.`flag`=0 AND t.`flag`=0 AND ${ew.sqlSegment}")
    IPage<BbbH5MarketMerchantCardUsersVO.PageData> selectH5ListPage(IPage<BbbH5MarketMerchantCardUsersVO.PageData> page,@Param(Constants.WRAPPER)  QueryWrapper<BbbH5MarketMerchantCardUsersQTO.QTO> qw);

    @Select("select mcu.id card_id, mcp.`goods_id` spu_id,mcu.`to_price`,mcu.`cut_price`,mcu.`card_name` from gs_market_merchant_card mc \n" +
            " inner join gs_market_merchant_card_goods mcp on mc.id=mcp.`card_id` \n" +
            " inner join gs_market_merchant_card_users mcu on mc.id=mcu.`card_id`\n" +
            "where mc.flag=0 and mcp.flag=0 and mcu.flag=0 and mc.terminal like '%${terminal}%'\n" +
            " and mc.valid_start_time <= sysdate() and mc.valid_end_time >= sysdate()\n" +
            " and mc.state = 20  and mcu.state = 10\n" +
            " and mcp.goods_id in ('${spuIds}')\n" +
            " and mcu.user_id = '${userId}'")
    List<CommonMarketDTO.SkuCard> activeSkuCard(@Param("spuIds") String spuIds, @Param("terminal") Integer terminal, @Param("userId") String userId);
}
