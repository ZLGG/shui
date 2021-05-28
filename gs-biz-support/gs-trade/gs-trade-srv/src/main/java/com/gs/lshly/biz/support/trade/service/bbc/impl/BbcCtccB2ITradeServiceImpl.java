package com.gs.lshly.biz.support.trade.service.bbc.impl;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.gs.lshly.biz.support.trade.service.bbc.IBbcCtccB2ITradeService;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcCtccB2ITradeDTO.CreateSimpleBusinessAcceptDTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcCtccB2ITradeVO.ResultVO;

/**
 * 电信B2I业务
 *
 * 
 * @author yingjun
 * @date 2021年5月28日 下午2:05:49
 */
@Component
public class BbcCtccB2ITradeServiceImpl implements IBbcCtccB2ITradeService{

	@Override
	public ResultVO createSimpleBusinessAccept(CreateSimpleBusinessAcceptDTO dto) {
		
		
		return null;
	}
	
	private Map<String,Object> setHeader(){
		
		return null;
	}

	
}