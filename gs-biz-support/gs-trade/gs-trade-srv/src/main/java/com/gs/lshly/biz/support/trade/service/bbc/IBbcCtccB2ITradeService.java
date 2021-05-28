package com.gs.lshly.biz.support.trade.service.bbc;

import com.gs.lshly.common.struct.bbc.trade.dto.BbcCtccB2ITradeDTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcCtccB2ITradeVO;

/**
 * 电信B2I业务
 *
 * 
 * @author yingjun
 * @date 2021年5月28日 下午2:05:49
 */
public interface IBbcCtccB2ITradeService {

	/**
	 * 
	 * @param dto
	 * @return
	 */
	BbcCtccB2ITradeVO.ResultVO createSimpleBusinessAccept(BbcCtccB2ITradeDTO.CreateSimpleBusinessAcceptDTO dto);
}