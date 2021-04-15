package com.gs.lshly.rpc.api.bbc.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcMarketSeckillDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcMarketSeckillQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketActivityVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketSeckillVO;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年4月15日 上午11:32:58
 */
public interface IBbcMarketSeckillRpc {
    
    /**
     * 秒杀首页
     * @param qto
     * @return
     */
    BbcMarketActivityVO.SeckillHome seckillHome(BbcMarketSeckillDTO.DTO dto);
    
    /**
     * 分页查询秒杀商品列表
     * @param qto
     * @return
     */
    PageData<BbcMarketSeckillVO.SeckillGoodsVO> pageSeckillGoods(BbcMarketSeckillQTO.QTO qto);
}
