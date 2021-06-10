package com.gs.lshly.biz.support.trade.service.bbc;

import java.util.List;

import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;

/**
 * @Author yangxi
 * @create 2021/5/8 9:54
 */
public interface IBbcCtccPtActivityGoodsService {
    
	List<BbcGoodsInfoVO.DetailVO> listCtccPtActivityGoods(Integer limit);
    
    
}
