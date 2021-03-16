package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantGiftDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantGiftQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantGiftVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchMarketMerchantGiftRpc;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketMerchantGiftService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zdf
* @since 2020-12-09
*/
@DubboService
public class PCMerchMarketMerchantGiftRpc implements IPCMerchMarketMerchantGiftRpc{
    @Autowired
    private IPCMerchMarketMerchantGiftService  pCMerchMarketMerchantGiftService;

    @Override
    public PageData<PCMerchMarketMerchantGiftVO.ViewVO> pageData(PCMerchMarketMerchantGiftQTO.QTO qto){
        return pCMerchMarketMerchantGiftService.pageData(qto);
    }

    @Override
    public void addMarketMerchantGift(PCMerchMarketMerchantGiftDTO.AddDTO eto){
        pCMerchMarketMerchantGiftService.addMarketMerchantGift(eto);
    }

    @Override
    public void deleteMarketMerchantGift(PCMerchMarketMerchantGiftDTO.IdDTO dto){
        pCMerchMarketMerchantGiftService.deleteMarketMerchantGift(dto);
    }


    @Override
    public void editMarketMerchantGift(PCMerchMarketMerchantGiftDTO.AddDTO eto){
        pCMerchMarketMerchantGiftService.editMarketMerchantGift(eto);
    }

    @Override
    public PCMerchMarketMerchantGiftVO.View detailMarketMerchantGift(PCMerchMarketMerchantGiftDTO.IdDTO dto){
        return  pCMerchMarketMerchantGiftService.detailMarketMerchantGift(dto);
    }

    @Override
    public void commitMarketMerchantGift(PCMerchMarketMerchantGiftDTO.IdDTO eto) {
        pCMerchMarketMerchantGiftService.commitMarketMerchantGift(eto);
    }

    @Override
    public void cancelMarketMerchantGift(PCMerchMarketMerchantGiftDTO.IdDTO eto) {
        pCMerchMarketMerchantGiftService.cancelMarketMerchantGift(eto);
    }

}