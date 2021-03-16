package com.gs.lshly.biz.support.commodity.rpc.bbb.pc;

import com.gs.lshly.biz.support.commodity.service.bbb.pc.IPCBbbGoodsSkuService;
import com.gs.lshly.biz.support.commodity.service.bbc.IBbcGoodsSkuService;
import com.gs.lshly.common.struct.bbb.pc.stock.dto.BbbStockDeliveryDTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserShoppingCarVO;
import com.gs.lshly.common.struct.bbc.stock.dto.BbcStockDeliveryDTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserShoppingCarVO;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateVO;
import com.gs.lshly.rpc.api.bbb.pc.commodity.IPCBbbGoodsSkuRpc;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsSkuRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lxus
 * @since 2020-11-18
 */
@DubboService
public class PCBbbGoodsSkuRpc implements IPCBbbGoodsSkuRpc {

    @Autowired
    private IPCBbbGoodsSkuService skuService;

    @Override
    public CommonStockTemplateVO.TemplateIdAndSkuPriceVO getGoodsStockTemplateIdAndSkuPrice(BbbStockDeliveryDTO.DeliverySKUDTO sku) {

        String templateId = skuService.getGoodsStockTemplateId(sku.getSkuId());

        CommonStockTemplateVO.SkuAmountAndPriceVO amountAndPriceVO = skuService.getSalePriceAndWeight(sku);

        CommonStockTemplateVO.TemplateIdAndSkuPriceVO vo = new CommonStockTemplateVO.TemplateIdAndSkuPriceVO().setTemplateId(templateId);
        vo.getSkus().add(amountAndPriceVO);

        return vo;
    }

    @Override
    public CommonStockTemplateVO.SkuAmountAndPriceVO getGoodsSkuPrice(BbbStockDeliveryDTO.DeliverySKUDTO sku) {
        return skuService.getSalePriceAndWeight(sku);
    }

    @Override
    public BbbUserShoppingCarVO.InnerSkuInfoVO getSkuInfo(String skuId) {
        return skuService.getSkuInfo(skuId);
    }
}
