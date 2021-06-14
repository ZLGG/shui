package com.gs.lshly.biz.support.trade.service.bbc;

import java.util.List;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcMarketSeckillDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcMarketSeckillDTO.DTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcMarketSeckillQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketActivityVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketActivityVO.SeckillHome;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketSeckillVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketSeckillVO.SeckillPointHome;

/**
 * 秒杀首页
 *
 * 
 * @author yingjun
 * @date 2021年4月15日 上午10:54:31
 */
public interface IBbcMarketSeckillService {


//	/**
//     * 
//     * @param qto
//     * @return
//     */
//	SeckillPointHome seckillPointHome(BbcMarketSeckillDTO.DTO dto);
    
    /**
     * 
     * @param qto
     * @return
     */
    SeckillHome seckillHome(BbcMarketSeckillDTO.DTO dto);
    
    /**
     * 分页查询秒杀活动分页数据
     * @param qto
     * @return
     */
    PageData<BbcMarketSeckillVO.SeckillGoodsVO> pageSeckillGoods(BbcMarketSeckillQTO.QTO qto);
    
    /**
     * 查询秒杀商品首页
     * @param id
     * @return
     */
    BbcGoodsInfoVO.SeckillDetailVO detailGoodsInfo(BbcMarketSeckillQTO.DetailQTO qto);
    
    /**
     * 首页秒杀
     * @param dto
     * @return
     */
    List<BbcMarketSeckillVO.HomePageSeckill> homePageSeckill(BaseDTO dto);
    
    /**
     * 
     * @param dto
     * @return
     */
    BbcMarketSeckillVO.SeckillIngVO seckillIng() ;
    
    
    SeckillHome seckillHomeNew(BbcMarketSeckillDTO.DTO dto);
    
    /**
     * 分页查询秒杀活动分页数据
     * @param qto
     * @return
     */
    PageData<BbcMarketSeckillVO.SeckillGoodsVO> pageSeckillGoodsNew(BbcMarketSeckillQTO.QTO qto);
}