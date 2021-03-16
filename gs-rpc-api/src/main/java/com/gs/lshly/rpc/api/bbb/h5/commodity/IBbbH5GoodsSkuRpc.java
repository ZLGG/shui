package com.gs.lshly.rpc.api.bbb.h5.commodity;

import com.gs.lshly.common.struct.bbb.h5.stock.dto.BbbH5StockDeliveryDTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserShoppingCarVO;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateVO;
/**
*
* @author lxus
* @since 2020-10-23
*/
public interface IBbbH5GoodsSkuRpc {

    /**
     * 获取sku对应的物流模板id
     * @param sku
     * @return
     */
    CommonStockTemplateVO.TemplateIdAndSkuPriceVO getGoodsStockTemplateIdAndSkuPrice(BbbH5StockDeliveryDTO.DeliverySKUDTO sku);

    CommonStockTemplateVO.SkuAmountAndPriceVO getGoodsSkuPrice(BbbH5StockDeliveryDTO.DeliverySKUDTO skudto);

    BbbH5UserShoppingCarVO.InnerSkuInfoVO getSkuInfo(String skuId);
}