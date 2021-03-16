package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantDiscountGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantDiscountGoodsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantDiscountGoodsVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchMarketMerchantDiscountGoodsRpc;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketMerchantDiscountGoodsService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zdf
* @since 2020-12-09
*/
@DubboService
public class PCMerchMarketMerchantDiscountGoodsRpc implements IPCMerchMarketMerchantDiscountGoodsRpc{
    @Autowired
    private IPCMerchMarketMerchantDiscountGoodsService  pCMerchMarketMerchantDiscountGoodsService;

    @Override
    public PageData<PCMerchMarketMerchantDiscountGoodsVO.ListVO> pageData(PCMerchMarketMerchantDiscountGoodsQTO.QTO qto){
        return pCMerchMarketMerchantDiscountGoodsService.pageData(qto);
    }

    @Override
    public void addMarketMerchantDiscountGoods(PCMerchMarketMerchantDiscountGoodsDTO.ETO eto){
        pCMerchMarketMerchantDiscountGoodsService.addMarketMerchantDiscountGoods(eto);
    }

    @Override
    public void deleteMarketMerchantDiscountGoods(PCMerchMarketMerchantDiscountGoodsDTO.IdDTO dto){
        pCMerchMarketMerchantDiscountGoodsService.deleteMarketMerchantDiscountGoods(dto);
    }


    @Override
    public void editMarketMerchantDiscountGoods(PCMerchMarketMerchantDiscountGoodsDTO.ETO eto){
        pCMerchMarketMerchantDiscountGoodsService.editMarketMerchantDiscountGoods(eto);
    }

    @Override
    public PCMerchMarketMerchantDiscountGoodsVO.DetailVO detailMarketMerchantDiscountGoods(PCMerchMarketMerchantDiscountGoodsDTO.IdDTO dto){
        return  pCMerchMarketMerchantDiscountGoodsService.detailMarketMerchantDiscountGoods(dto);
    }

}