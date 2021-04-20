package com.gs.lshly.rpc.api.bbc.commodity;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcCtccCategoryGoodsDTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcCtccCategoryGoodsVO;

/**
 * 电信类产品
 *
 * 
 * @author yingjun
 * @date 2021年4月20日 上午10:36:41
 */
public interface IBbcCtccCategoryGoodsRpc {

	/**
     * 电信国际首页
     * @param qto
     * @return
     */
    BbcCtccCategoryGoodsVO.CtccInternationalHomeVO ctccInternationalHomeVO(BbcCtccCategoryGoodsDTO.DTO dto);
    
}