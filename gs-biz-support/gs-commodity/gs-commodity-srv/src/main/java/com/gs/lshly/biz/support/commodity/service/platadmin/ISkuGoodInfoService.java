package com.gs.lshly.biz.support.commodity.service.platadmin;

import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtSeckillVO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.SkuGoodsInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.SkuGoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtSeckillVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeRightsVO;

import java.util.List;

/**
 * @Author Starry
 * @Date 16:00 2020/10/14
 */
public interface ISkuGoodInfoService {

    /**
     * 根据商品id查询sku列表
     * @param dto
     * @return
     */
    List<SkuGoodsInfoVO.DetailVO> listSku(SkuGoodsInfoDTO.GoodsIdDTO dto);

    MarketPtSeckillVO.SkuGoodsInfo selectOne(String id);

    String selectSkuImg(String skuId);

    TradeRightsVO.SkuGoodsInfo selectImageAndPrice(String skuId);
}
