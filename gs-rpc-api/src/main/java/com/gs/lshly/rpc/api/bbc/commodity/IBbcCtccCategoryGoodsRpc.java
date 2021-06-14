package com.gs.lshly.rpc.api.bbc.commodity;
import java.util.List;

import com.gs.lshly.common.struct.bbc.commodity.dto.BbcCtccCategoryGoodsDTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcCtccCategoryGoodsVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;

/**
 * 电信类产品
 *
 * 
 * @author yingjun
 * @date 2021年4月20日 上午10:36:41
 */
public interface IBbcCtccCategoryGoodsRpc {

    /**
     * 获取电信国际列表
     * @return
     */
    List<BbcGoodsInfoVO.DetailVO> listGoodsInfo();
    
}