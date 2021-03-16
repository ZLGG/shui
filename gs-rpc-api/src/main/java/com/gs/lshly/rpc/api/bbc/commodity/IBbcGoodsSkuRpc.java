package com.gs.lshly.rpc.api.bbc.commodity;

import com.gs.lshly.common.struct.bbc.stock.dto.BbcStockDeliveryDTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserShoppingCarVO;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateVO;

/**
*
* @author lxus
* @since 2020-10-23
*/
public interface IBbcGoodsSkuRpc {

    /**
     * 获取sku对应的物流模板id
     * @param sku
     * @return
     */
    CommonStockTemplateVO.TemplateIdAndSkuPriceVO getGoodsStockTemplateIdAndSkuPrice(BbcStockDeliveryDTO.DeliverySKUDTO sku);

    CommonStockTemplateVO.SkuAmountAndPriceVO getGoodsSkuPrice(BbcStockDeliveryDTO.DeliverySKUDTO skudto);

    BbcUserShoppingCarVO.InnerSkuInfoVO getSkuInfo(String skuId);
}