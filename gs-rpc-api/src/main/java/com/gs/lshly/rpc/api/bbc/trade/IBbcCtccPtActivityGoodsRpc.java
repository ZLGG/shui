package com.gs.lshly.rpc.api.bbc.trade;

import java.util.List;

import com.gs.lshly.common.struct.bbc.commodity.dto.BbcCtccCategoryGoodsDTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcCtccCategoryGoodsVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;

/**
 * 电信国际
 *
 * 
 * @author yingjun
 * @date 2021年6月10日 下午3:13:19
 */
public interface IBbcCtccPtActivityGoodsRpc {

	/**
	 * 查询电信国际列表
	 * @param limit
	 * @return
	 */
	List<BbcGoodsInfoVO.DetailVO> listCtccPtActivityGoods(Integer limit);
	
	
	/**
     * 电信国际首页
     * @param qto
     * @return
     */
    BbcCtccCategoryGoodsVO.CtccInternationalHomeVO ctccInternationalHomeVO(BbcCtccCategoryGoodsDTO.DTO dto);
	
}
