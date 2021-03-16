package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCardDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantCardQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantCardVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchMarketMerchantCardRpc;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketMerchantCardService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zdf
* @since 2020-12-04
*/
@DubboService
public class PCMerchMarketMerchantCardRpc implements IPCMerchMarketMerchantCardRpc{
    @Autowired
    private IPCMerchMarketMerchantCardService  pCMerchMarketMerchantCardService;

    @Override
    public PageData<PCMerchMarketMerchantCardVO.ListVO> pageData(PCMerchMarketMerchantCardQTO.QTO qto){
        return pCMerchMarketMerchantCardService.pageData(qto);
    }

    @Override
    public void addMarketMerchantCard(PCMerchMarketMerchantCardDTO.ETO eto){
        pCMerchMarketMerchantCardService.addMarketMerchantCard(eto);
    }

    @Override
    public void deleteMarketMerchantCard(PCMerchMarketMerchantCardDTO.IdDTO dto){
        pCMerchMarketMerchantCardService.deleteMarketMerchantCard(dto);
    }


    @Override
    public void editMarketMerchantCard(PCMerchMarketMerchantCardDTO.ETO eto){
        pCMerchMarketMerchantCardService.editMarketMerchantCard(eto);
    }

    @Override
    public PCMerchMarketMerchantCardVO.DetailVO detailMarketMerchantCard(PCMerchMarketMerchantCardDTO.IdDTO dto){
        return  pCMerchMarketMerchantCardService.detailMarketMerchantCard(dto);
    }

    @Override
    public void cancelMarketMerchantCard(PCMerchMarketMerchantCardDTO.IdDTO eto) {
       pCMerchMarketMerchantCardService.commitMarketMerchantCard(eto);
    }

    @Override
    public void commitMarketMerchantCard(PCMerchMarketMerchantCardDTO.IdDTO eto) {
        pCMerchMarketMerchantCardService.commitMarketMerchantCard(eto);
    }

    @Override
    public PCMerchMarketMerchantCardVO.View viewMarketMerchantCard(PCMerchMarketMerchantCardDTO.IdDTO eto) {
        return pCMerchMarketMerchantCardService.viewMarketMerchantCard(eto);
    }

}