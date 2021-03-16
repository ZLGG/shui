package com.gs.lshly.biz.support.commodity.rpc.bbc;

import com.gs.lshly.biz.support.commodity.service.bbc.IBbcGoodsSkuService;
import com.gs.lshly.common.struct.bbc.stock.dto.BbcStockDeliveryDTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserShoppingCarVO;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateVO;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsSkuRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lxus
 * @since 2020-11-18
 */
@DubboService
public class BbcGoodsSkuRpc implements IBbcGoodsSkuRpc {

    @Autowired
    private IBbcGoodsSkuService skuService;

    @Override
    public CommonStockTemplateVO.TemplateIdAndSkuPriceVO getGoodsStockTemplateIdAndSkuPrice(BbcStockDeliveryDTO.DeliverySKUDTO sku) {

        String templateId = skuService.getGoodsStockTemplateId(sku.getSkuId());

        CommonStockTemplateVO.SkuAmountAndPriceVO amountAndPriceVO = skuService.getSalePriceAndWeight(sku);

        CommonStockTemplateVO.TemplateIdAndSkuPriceVO vo = new CommonStockTemplateVO.TemplateIdAndSkuPriceVO().setTemplateId(templateId);
        vo.getSkus().add(amountAndPriceVO);

        return vo;
    }

    @Override
    public CommonStockTemplateVO.SkuAmountAndPriceVO getGoodsSkuPrice(BbcStockDeliveryDTO.DeliverySKUDTO sku) {
        return skuService.getSalePriceAndWeight(sku);
    }

    @Override
    public BbcUserShoppingCarVO.InnerSkuInfoVO getSkuInfo(String skuId) {
        return skuService.getSkuInfo(skuId);
    }
}
