package com.gs.lshly.biz.support.trade.service.bbc;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcMarketActivityQTO.SeckillHomeQTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcMarketSeckillDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcMarketSeckillQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketActivityVO.SeckillHome;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketSeckillVO;

/**
 * 秒杀首页
 *
 * 
 * @author yingjun
 * @date 2021年4月15日 上午10:54:31
 */
public interface IBbcMarketSeckillService {


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
}