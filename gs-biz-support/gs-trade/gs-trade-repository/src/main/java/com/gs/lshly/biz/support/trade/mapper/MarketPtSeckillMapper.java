package com.gs.lshly.biz.support.trade.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.trade.entity.MarketPtSeckill;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcMarketSeckillQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketSeckillVO;

/**
 * <p>
 * 平台秒杀活动 Mapper 接口
 * </p>
 *
 * @author yingjun
 * @since 2021-04-14
 */
public interface MarketPtSeckillMapper extends BaseMapper<MarketPtSeckill> {

	@Select("SELECT\n" +
            "\tgoods.seckill_sale_price seckill_price,\n" +
            "\tgoods.seckill_point_price seckill_point_price,\n" +
            "\tgoods.seckill_in_member_point_price seckill_in_member_point_price,\n" +
            "\tgoods.goods_id goods_id\n" +
            "FROM\n" +
            "\tgs_market_pt_seckill_goods_spu goods\n" +
            "\tLEFT JOIN gs_market_pt_seckill_merchant merchant ON merchant.seckill_id = goods.seckill_id \n" +
            "WHERE\n" +
            "\tgoods.flag = 0 \n" +
            "\tAND merchant.flag = 0 \n" +
            "\tAND merchant.state = 10 AND ${ew.sqlSegment}")
    IPage<BbcMarketSeckillVO.SeckillGoodsVO> pageSeckillGoods(IPage<BbcMarketSeckillVO.SeckillGoodsVO> pager,@Param(Constants.WRAPPER) QueryWrapper<BbcMarketSeckillQTO.QTO> qw);
    
}
