package com.gs.lshly.biz.support.trade.rpc.bbc;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.gs.lshly.biz.support.trade.service.bbc.IBbcMarketSeckillService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsInfoDTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO.SeckillDetailVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcMarketSeckillDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcMarketSeckillQTO.QTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketActivityVO.SeckillHome;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketSeckillVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketSeckillVO.SeckillPointHome;
import com.gs.lshly.rpc.api.bbc.trade.IBbcMarketSeckillRpc;

/**
 * 秒杀活动
 *
 * 
 * @author yingjun
 * @date 2021年4月15日 上午11:32:03
 */
@DubboService
public class BbcMarketSeckillRpc implements IBbcMarketSeckillRpc {
    
	@Autowired
    private IBbcMarketSeckillService bbcMarketSeckillService;

	@Override
	public SeckillHome seckillHome(BbcMarketSeckillDTO.DTO dto) {
		return bbcMarketSeckillService.seckillHome(dto);
	}

	@Override
	public PageData<BbcMarketSeckillVO.SeckillGoodsVO> pageSeckillGoods(QTO qto) {
		return bbcMarketSeckillService.pageSeckillGoods(qto);
	}

	@Override
	public SeckillDetailVO detailGoodsInfo(BbcGoodsInfoDTO.IdDTO dto) {
		return bbcMarketSeckillService.detailGoodsInfo(dto);
	}

}