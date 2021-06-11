package com.gs.lshly.rpc.api.platadmin.commodity;

import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtSeckillVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtSeckillVO;

import java.util.List;

/**
 * @author hanly
 */
public interface ISkuGoodsInfoRpc {
    MarketPtSeckillVO.SkuGoodsInfo selectOne(String skuId);

}
