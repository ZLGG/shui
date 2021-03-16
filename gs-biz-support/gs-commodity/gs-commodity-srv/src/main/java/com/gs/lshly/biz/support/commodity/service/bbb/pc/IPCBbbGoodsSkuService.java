package com.gs.lshly.biz.support.commodity.service.bbb.pc;

import com.gs.lshly.common.struct.bbb.pc.stock.dto.BbbStockDeliveryDTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserShoppingCarVO;

import com.gs.lshly.common.struct.common.stock.CommonStockTemplateVO;

public interface IPCBbbGoodsSkuService {

    String getGoodsStockTemplateId(String skuId);

    CommonStockTemplateVO.SkuAmountAndPriceVO getSalePriceAndWeight(BbbStockDeliveryDTO.DeliverySKUDTO sku);

    BbbUserShoppingCarVO.InnerSkuInfoVO getSkuInfo(String skuId);
}
