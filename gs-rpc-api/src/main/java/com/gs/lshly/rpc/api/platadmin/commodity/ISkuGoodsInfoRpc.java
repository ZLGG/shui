package com.gs.lshly.rpc.api.platadmin.commodity;

import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtSeckillVO;

/**
 * @author hanly
 */
public interface ISkuGoodsInfoRpc {
    MarketPtSeckillVO.SkuGoodsInfo selectOne(String skuId);

}
