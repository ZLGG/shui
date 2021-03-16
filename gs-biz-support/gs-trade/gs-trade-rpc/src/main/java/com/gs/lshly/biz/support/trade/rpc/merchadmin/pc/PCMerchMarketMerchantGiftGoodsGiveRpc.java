package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantGiftGoodsGiveDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantGiftGoodsGiveQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantGiftGoodsGiveVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchMarketMerchantGiftGoodsGiveRpc;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketMerchantGiftGoodsGiveService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zdf
* @since 2020-12-09
*/
@DubboService
public class PCMerchMarketMerchantGiftGoodsGiveRpc implements IPCMerchMarketMerchantGiftGoodsGiveRpc{
    @Autowired
    private IPCMerchMarketMerchantGiftGoodsGiveService  pCMerchMarketMerchantGiftGoodsGiveService;

    @Override
    public PageData<PCMerchMarketMerchantGiftGoodsGiveVO.ListVO> pageData(PCMerchMarketMerchantGiftGoodsGiveQTO.QTO qto){
        return pCMerchMarketMerchantGiftGoodsGiveService.pageData(qto);
    }

    @Override
    public void addMarketMerchantGiftGoodsGive(PCMerchMarketMerchantGiftGoodsGiveDTO.ETO eto){
        pCMerchMarketMerchantGiftGoodsGiveService.addMarketMerchantGiftGoodsGive(eto);
    }

    @Override
    public void deleteMarketMerchantGiftGoodsGive(PCMerchMarketMerchantGiftGoodsGiveDTO.IdDTO dto){
        pCMerchMarketMerchantGiftGoodsGiveService.deleteMarketMerchantGiftGoodsGive(dto);
    }


    @Override
    public void editMarketMerchantGiftGoodsGive(PCMerchMarketMerchantGiftGoodsGiveDTO.ETO eto){
        pCMerchMarketMerchantGiftGoodsGiveService.editMarketMerchantGiftGoodsGive(eto);
    }

    @Override
    public PCMerchMarketMerchantGiftGoodsGiveVO.DetailVO detailMarketMerchantGiftGoodsGive(PCMerchMarketMerchantGiftGoodsGiveDTO.IdDTO dto){
        return  pCMerchMarketMerchantGiftGoodsGiveService.detailMarketMerchantGiftGoodsGive(dto);
    }

}