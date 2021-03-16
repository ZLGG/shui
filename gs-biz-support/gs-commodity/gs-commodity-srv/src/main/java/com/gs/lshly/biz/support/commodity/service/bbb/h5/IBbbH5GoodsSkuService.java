package com.gs.lshly.biz.support.commodity.service.bbb.h5;

import com.gs.lshly.common.struct.bbb.h5.commodity.dto.BbbH5GoodsInfoDTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5SkuGoodInfoVO;
import com.gs.lshly.common.struct.bbb.h5.stock.dto.BbbH5StockDeliveryDTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserShoppingCarVO;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateVO;

import java.util.List;

public interface IBbbH5GoodsSkuService {

    String getGoodsStockTemplateId(String skuId);

    CommonStockTemplateVO.SkuAmountAndPriceVO getSalePriceAndWeight(BbbH5StockDeliveryDTO.DeliverySKUDTO sku);

    BbbH5UserShoppingCarVO.InnerSkuInfoVO getSkuInfo(String skuId);

}
