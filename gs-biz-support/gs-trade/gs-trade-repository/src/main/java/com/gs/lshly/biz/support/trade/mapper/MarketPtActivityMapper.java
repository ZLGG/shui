package com.gs.lshly.biz.support.trade.mapper;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.trade.entity.MarketPtActivity;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5MarketActivityDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5MarketActivityVO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbMarketActivityDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketActivityVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcMarketActivityDTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketActivityVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 平台活动 Mapper 接口
 * </p>
 *
 * @author zdf
 * @since 2020-11-28
 */
public interface MarketPtActivityMapper extends BaseMapper<MarketPtActivity> {

    @Select("SELECT\n" +
            "\tgoods.activity_sale_price activity_price,\n" +
            "\tgoods.goods_id goods_id\n" +
            "FROM\n" +
            "\tgs_market_pt_activity_goods_spu goods\n" +
            "\tLEFT JOIN gs_market_pt_activity_merchant merchant ON merchant.activity_id = goods.activity_id \n" +
            "WHERE\n" +
            "\tgoods.flag = 0 \n" +
            "\tAND merchant.flag = 0 \n" +
            "\tAND merchant.state = 10 AND ${ew.sqlSegment}")
    IPage<PCBbbMarketActivityVO.activityGoodsVO> selectActivityPageData(IPage<PCBbbMarketActivityVO.activityGoodsVO> pager,  @Param(Constants.WRAPPER)QueryWrapper<BbbMarketActivityDTO.IdDTO> qw);
    @Select("SELECT\n" +
            "\tgoods.activity_sale_price activity_price,\n" +
            "\tgoods.goods_id goods_id\n" +
            "FROM\n" +
            "\tgs_market_pt_activity_goods_spu goods\n" +
            "\tLEFT JOIN gs_market_pt_activity_merchant merchant ON merchant.activity_id = goods.activity_id \n" +
            "WHERE\n" +
            "\tgoods.flag = 0 \n" +
            "\tAND merchant.flag = 0 \n" +
            "\tAND merchant.state = 10 AND ${ew.sqlSegment}")
    IPage<BbbH5MarketActivityVO.activityGoodsVO> selectActivityPageDataH5(IPage<BbbH5MarketActivityVO.activityGoodsVO> pager,@Param(Constants.WRAPPER) QueryWrapper<BbbH5MarketActivityDTO.IdDTO> qw);
    @Select("SELECT\n" +
            "\tgoods.activity_sale_price activity_price,\n" +
            "\tgoods.goods_id goods_id\n" +
            "FROM\n" +
            "\tgs_market_pt_activity_goods_spu goods\n" +
            "\tLEFT JOIN gs_market_pt_activity_merchant merchant ON merchant.activity_id = goods.activity_id \n" +
            "WHERE\n" +
            "\tgoods.flag = 0 \n" +
            "\tAND merchant.flag = 0 \n" +
            "\tAND merchant.state = 10 AND ${ew.sqlSegment}")
    IPage<BbcMarketActivityVO.activityGoodsVO> selectActivityPageDataBbcH5(IPage<BbcMarketActivityVO.activityGoodsVO> pager, @Param(Constants.WRAPPER)QueryWrapper<BbcMarketActivityDTO.IdDTO> qw);
}
