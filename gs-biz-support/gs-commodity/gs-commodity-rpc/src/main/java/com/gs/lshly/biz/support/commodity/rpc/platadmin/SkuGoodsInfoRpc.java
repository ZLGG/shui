package com.gs.lshly.biz.support.commodity.rpc.platadmin;

import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsRelationLabelService;
import com.gs.lshly.biz.support.commodity.service.platadmin.ISkuGoodInfoService;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsRelationLabelDTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtSeckillVO;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsRelationLabelRpc;
import com.gs.lshly.rpc.api.platadmin.commodity.ISkuGoodsInfoRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Starry
 * @since 2020-10-15
 */
@DubboService
public class SkuGoodsInfoRpc implements ISkuGoodsInfoRpc {

    @Autowired
    private ISkuGoodInfoService skuGoodInfoService;

    @Override
    public MarketPtSeckillVO.SkuGoodsInfo selectOne(String skuId) {
        return skuGoodInfoService.selectOne(skuId);
    }
}