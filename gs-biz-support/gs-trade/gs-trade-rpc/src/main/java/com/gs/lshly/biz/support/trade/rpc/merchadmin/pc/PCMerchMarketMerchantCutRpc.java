package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCutDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantCutQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantCutVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchMarketMerchantCutRpc;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketMerchantCutService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zdf
* @since 2020-12-08
*/
@DubboService
public class PCMerchMarketMerchantCutRpc implements IPCMerchMarketMerchantCutRpc{
    @Autowired
    private IPCMerchMarketMerchantCutService  pCMerchMarketMerchantCutService;

    @Override
    public PageData<PCMerchMarketMerchantCutVO.ViewVO> pageData(PCMerchMarketMerchantCutQTO.QTO qto){
        return pCMerchMarketMerchantCutService.pageData(qto);
    }

    @Override
    public void addMarketMerchantCut(PCMerchMarketMerchantCutDTO.AddDTO eto){
        pCMerchMarketMerchantCutService.addMarketMerchantCut(eto);
    }

    @Override
    public void deleteMarketMerchantCut(PCMerchMarketMerchantCutDTO.IdDTO dto){
        pCMerchMarketMerchantCutService.deleteMarketMerchantCut(dto);
    }


    @Override
    public void editMarketMerchantCut(PCMerchMarketMerchantCutDTO.AddDTO eto){
        pCMerchMarketMerchantCutService.editMarketMerchantCut(eto);
    }

    @Override
    public PCMerchMarketMerchantCutVO.View detailMarketMerchantCut(PCMerchMarketMerchantCutDTO.IdDTO dto){
        return  pCMerchMarketMerchantCutService.detailMarketMerchantCut(dto);
    }

    @Override
    public void commitMarketMerchantCut(PCMerchMarketMerchantCutDTO.IdDTO eto) {
    pCMerchMarketMerchantCutService.commitMarketMerchantCut(eto);
    }

    @Override
    public void cancelMarketMerchantCut(PCMerchMarketMerchantCutDTO.IdDTO eto) {
        pCMerchMarketMerchantCutService.cancelMarketMerchantCut(eto);
    }

}