package com.gs.lshly.biz.support.commodity.service.bbc;

import com.gs.lshly.common.struct.bbc.stock.dto.BbcStockDeliveryDTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserShoppingCarVO;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateVO;

public interface IBbcGoodsSkuService {

    String getGoodsStockTemplateId(String skuId);

    CommonStockTemplateVO.SkuAmountAndPriceVO getSalePriceAndWeight(BbcStockDeliveryDTO.DeliverySKUDTO sku);

    BbcUserShoppingCarVO.InnerSkuInfoVO getSkuInfo(String skuId);
}
