package com.gs.lshly.biz.support.trade.rpc.bbc;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.gs.lshly.biz.support.trade.service.bbc.IBbcCtccPtActivityGoodsService;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcCtccCategoryGoodsDTO.DTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcCtccCategoryGoodsVO.CtccInternationalHomeVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.rpc.api.bbc.trade.IBbcCtccPtActivityGoodsRpc;

/**
 * 电信国际
 *
 * 
 * @author yingjun
 * @date 2021年6月10日 下午3:12:31
 */
@DubboService
public class BbcCtccPtActivityGoodsRpc implements IBbcCtccPtActivityGoodsRpc {
    @Autowired
    private IBbcCtccPtActivityGoodsService bbcCtccPtActivityGoodsService;

	@Override
	public List<BbcGoodsInfoVO.DetailVO> listCtccPtActivityGoods(Integer limit) {
		return bbcCtccPtActivityGoodsService.listCtccPtActivityGoods(limit);
	}

	@Override
	public CtccInternationalHomeVO ctccInternationalHomeVO(DTO dto) {
		// TODO Auto-generated method stub
		return bbcCtccPtActivityGoodsService.ctccInternationalHomeVO(dto);
	}
}
