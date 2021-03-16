package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantGroupbuyDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantGroupbuyQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantGroupbuyVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchMarketMerchantGroupbuyRpc;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketMerchantGroupbuyService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zdf
* @since 2020-12-10
*/
@DubboService
public class PCMerchMarketMerchantGroupbuyRpc implements IPCMerchMarketMerchantGroupbuyRpc{
    @Autowired
    private IPCMerchMarketMerchantGroupbuyService  pCMerchMarketMerchantGroupbuyService;

    @Override
    public PageData<PCMerchMarketMerchantGroupbuyVO.ViewVO> pageData(PCMerchMarketMerchantGroupbuyQTO.QTO qto){
        return pCMerchMarketMerchantGroupbuyService.pageData(qto);
    }

    @Override
    public void addMarketMerchantGroupbuy(PCMerchMarketMerchantGroupbuyDTO.AddDTO dto){
        pCMerchMarketMerchantGroupbuyService.addMarketMerchantGroupbuy(dto);
    }

    @Override
    public void deleteMarketMerchantGroupbuy(PCMerchMarketMerchantGroupbuyDTO.IdDTO dto){
        pCMerchMarketMerchantGroupbuyService.deleteMarketMerchantGroupbuy(dto);
    }


    @Override
    public void editMarketMerchantGroupbuy(PCMerchMarketMerchantGroupbuyDTO.AddDTO  eto){
        pCMerchMarketMerchantGroupbuyService.editMarketMerchantGroupbuy(eto);
    }

    @Override
    public PCMerchMarketMerchantGroupbuyVO.View detailMarketMerchantGroupbuy(PCMerchMarketMerchantGroupbuyDTO.IdDTO dto){
        return  pCMerchMarketMerchantGroupbuyService.detailMarketMerchantGroupbuy(dto);
    }

    @Override
    public void commitMarketMerchantGroupbuy(PCMerchMarketMerchantGroupbuyDTO.AddDTO eto) {
        pCMerchMarketMerchantGroupbuyService.commitMarketMerchantGroupbuy(eto);
    }

    @Override
    public void cancelMarketMerchantGroupbuy(PCMerchMarketMerchantGroupbuyDTO.AddDTO eto) {
        pCMerchMarketMerchantGroupbuyService.cancelMarketMerchantGroupbuy(eto);
    }

}