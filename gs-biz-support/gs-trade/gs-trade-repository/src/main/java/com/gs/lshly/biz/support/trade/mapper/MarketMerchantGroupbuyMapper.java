package com.gs.lshly.biz.support.trade.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantGroupbuy;
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
 * 商家团购促销 Mapper 接口
 * </p>
 *
 * @author zdf
 * @since 2020-12-10
 */
public interface MarketMerchantGroupbuyMapper extends BaseMapper<MarketMerchantGroupbuy> {

    @Select("SELECT\n" +
            "\tgoods.goods_id goods_id,\n" +
            "\tgoods.groupbuy_id id,\n" +
            "\tgoods.shop_id shop_id,\n" +
            "\tgroupbuy.groupbuy_name groupbuy_name,\n" +
            "\tgoods.groupbuy_price groupbuy_price \n" +
            "FROM\n" +
            "\tgs_market_merchant_groupbuy_goods goods\n" +
            "\tLEFT JOIN gs_market_merchant_groupbuy groupbuy ON goods.groupbuy_id = groupbuy.id \n" +
            "WHERE\n" +
            "\tgroupbuy.state = 20 \n" +
            "\tAND groupbuy.flag = 0 \n" +
            "\tAND goods.flag =0 AND ${ew.sqlSegment}")
    IPage<PCBbbMarketActivityVO.groupbuyVO> selectGroupbuyListPage(IPage<PCBbbMarketActivityVO.groupbuyVO> pager, @Param(Constants.WRAPPER) QueryWrapper<PCBbbMarketActivityQTO.QTO> qw);
    @Select("SELECT\n" +
            "\tgoods.goods_id goods_id,\n" +
            "\tgoods.groupbuy_id id,\n" +
            "\tgoods.shop_id shop_id,\n" +
            "\tgroupbuy.groupbuy_name groupbuy_name,\n" +
            "\tgoods.groupbuy_price groupbuy_price \n" +
            "FROM\n" +
            "\tgs_market_merchant_groupbuy_goods goods\n" +
            "\tLEFT JOIN gs_market_merchant_groupbuy groupbuy ON goods.groupbuy_id = groupbuy.id \n" +
            "WHERE\n" +
            "\tgroupbuy.state = 20 \n" +
            "\tAND groupbuy.flag = 0 \n" +
            "\tAND goods.flag =0 AND ${ew.sqlSegment}")
    IPage<BbbH5MarketActivityVO.groupbuyVO> selectGroupbuyH5ListPage(IPage<BbbH5MarketActivityVO.groupbuyVO> pager,@Param(Constants.WRAPPER) QueryWrapper<BbbH5MarketActivityQTO.QTO> qw);
    @Select("SELECT\n" +
            "\tgoods.goods_id goods_id,\n" +
            "\tgoods.groupbuy_id id,\n" +
            "\tgoods.shop_id shop_id,\n" +
            "\tgroupbuy.groupbuy_name groupbuy_name,\n" +
            "\tgoods.groupbuy_price groupbuy_price \n" +
            "FROM\n" +
            "\tgs_market_merchant_groupbuy_goods goods\n" +
            "\tLEFT JOIN gs_market_merchant_groupbuy groupbuy ON goods.groupbuy_id = groupbuy.id \n" +
            "WHERE\n" +
            "\tgroupbuy.state = 20 \n" +
            "\tAND groupbuy.flag = 0 \n" +
            "\tAND goods.flag =0 AND ${ew.sqlSegment}")
    IPage<BbcMarketActivityVO.groupbuyVO> selectGroupbuyBbcH5ListPage(IPage<BbcMarketActivityVO.groupbuyVO> pager,@Param(Constants.WRAPPER)  QueryWrapper<BbcMarketActivityQTO.QTO> qw);
}
