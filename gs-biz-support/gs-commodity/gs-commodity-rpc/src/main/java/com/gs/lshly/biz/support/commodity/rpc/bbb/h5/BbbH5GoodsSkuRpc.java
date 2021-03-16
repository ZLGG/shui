package com.gs.lshly.biz.support.commodity.rpc.bbb.h5;

import com.gs.lshly.biz.support.commodity.service.bbb.h5.IBbbH5GoodsSkuService;
import com.gs.lshly.common.struct.bbb.h5.stock.dto.BbbH5StockDeliveryDTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserShoppingCarVO;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateVO;
import com.gs.lshly.rpc.api.bbb.h5.commodity.IBbbH5GoodsSkuRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lxus
 * @since 2020-11-18
 */
@DubboService
public class BbbH5GoodsSkuRpc implements IBbbH5GoodsSkuRpc {

    @Autowired
    private IBbbH5GoodsSkuService skuService;

    @Override
    public CommonStockTemplateVO.TemplateIdAndSkuPriceVO getGoodsStockTemplateIdAndSkuPrice(BbbH5StockDeliveryDTO.DeliverySKUDTO sku) {

        String templateId = skuService.getGoodsStockTemplateId(sku.getSkuId());

        CommonStockTemplateVO.SkuAmountAndPriceVO amountAndPriceVO = skuService.getSalePriceAndWeight(sku);

        CommonStockTemplateVO.TemplateIdAndSkuPriceVO vo = new CommonStockTemplateVO.TemplateIdAndSkuPriceVO().setTemplateId(templateId);
        vo.getSkus().add(amountAndPriceVO);

        return vo;
    }

    @Override
    public CommonStockTemplateVO.SkuAmountAndPriceVO getGoodsSkuPrice(BbbH5StockDeliveryDTO.DeliverySKUDTO sku) {
        return skuService.getSalePriceAndWeight(sku);
    }

    @Override
    public BbbH5UserShoppingCarVO.InnerSkuInfoVO getSkuInfo(String skuId) {
        return skuService.getSkuInfo(skuId);
    }
}
