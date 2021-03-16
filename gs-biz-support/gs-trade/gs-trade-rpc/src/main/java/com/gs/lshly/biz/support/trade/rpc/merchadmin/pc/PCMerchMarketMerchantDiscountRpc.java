package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantDiscountDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantDiscountQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantDiscountVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchMarketMerchantDiscountRpc;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketMerchantDiscountService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zdf
* @since 2020-12-09
*/
@DubboService
public class PCMerchMarketMerchantDiscountRpc implements IPCMerchMarketMerchantDiscountRpc{
    @Autowired
    private IPCMerchMarketMerchantDiscountService  pCMerchMarketMerchantDiscountService;

    @Override
    public PageData<PCMerchMarketMerchantDiscountVO.ViewVO> pageData(PCMerchMarketMerchantDiscountQTO.QTO qto){
        return pCMerchMarketMerchantDiscountService.pageData(qto);
    }

    @Override
    public void addMarketMerchantDiscount(PCMerchMarketMerchantDiscountDTO.AddDTO eto){
        pCMerchMarketMerchantDiscountService.addMarketMerchantDiscount(eto);
    }

    @Override
    public void deleteMarketMerchantDiscount(PCMerchMarketMerchantDiscountDTO.IdDTO dto){
        pCMerchMarketMerchantDiscountService.deleteMarketMerchantDiscount(dto);
    }


    @Override
    public void editMarketMerchantDiscount(PCMerchMarketMerchantDiscountDTO.AddDTO eto){
        pCMerchMarketMerchantDiscountService.editMarketMerchantDiscount(eto);
    }

    @Override
    public PCMerchMarketMerchantDiscountVO.View detailMarketMerchantDiscount(PCMerchMarketMerchantDiscountDTO.IdDTO dto){
        return  pCMerchMarketMerchantDiscountService.detailMarketMerchantDiscount(dto);
    }

    @Override
    public void commitMarketMerchantDiscount(PCMerchMarketMerchantDiscountDTO.IdDTO eto) {
        pCMerchMarketMerchantDiscountService.commitMarketMerchantDiscount(eto);
    }

    @Override
    public void cancelMarketMerchantDiscount(PCMerchMarketMerchantDiscountDTO.IdDTO eto) {
        pCMerchMarketMerchantDiscountService.cancelMarketMerchantDiscount(eto);
    }

}