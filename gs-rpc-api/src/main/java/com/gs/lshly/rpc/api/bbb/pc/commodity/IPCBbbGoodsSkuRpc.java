package com.gs.lshly.rpc.api.bbb.pc.commodity;

import com.gs.lshly.common.struct.bbb.pc.stock.dto.BbbStockDeliveryDTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserShoppingCarVO;
import com.gs.lshly.common.struct.bbc.stock.dto.BbcStockDeliveryDTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserShoppingCarVO;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateVO;

/**
*
* @author lxus
* @since 2020-10-23
*/
public interface IPCBbbGoodsSkuRpc {

    /**
     * 获取sku对应的物流模板id
     * @param sku
     * @return
     */
    CommonStockTemplateVO.TemplateIdAndSkuPriceVO getGoodsStockTemplateIdAndSkuPrice(BbbStockDeliveryDTO.DeliverySKUDTO sku);

    CommonStockTemplateVO.SkuAmountAndPriceVO getGoodsSkuPrice(BbbStockDeliveryDTO.DeliverySKUDTO skudto);

    BbbUserShoppingCarVO.InnerSkuInfoVO getSkuInfo(String skuId);
}