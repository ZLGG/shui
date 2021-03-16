package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeRightsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeRightsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeRightsVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchTradeRightsRpc;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchTradeRightsService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zdf
* @since 2020-12-17
*/
@DubboService
public class PCMerchTradeRightsRpc implements IPCMerchTradeRightsRpc{
    @Autowired
    private IPCMerchTradeRightsService  pCMerchTradeRightsService;

    @Override
    public PageData<PCMerchTradeRightsVO.RightList> pageData(PCMerchTradeRightsQTO.QTO qto){
        return pCMerchTradeRightsService.pageData(qto);
    }

    @Override
    public void addTradeRights(PCMerchTradeRightsDTO.ETO eto){
        pCMerchTradeRightsService.addTradeRights(eto);
    }

    @Override
    public void deleteTradeRights(PCMerchTradeRightsDTO.IdDTO dto){
        pCMerchTradeRightsService.deleteTradeRights(dto);
    }


    @Override
    public void editTradeRights(PCMerchTradeRightsDTO.ETO eto){
        pCMerchTradeRightsService.editTradeRights(eto);
    }

    @Override
    public PCMerchTradeRightsVO.DetailVO detailTradeRights(PCMerchTradeRightsDTO.IdDTO dto){
        return  pCMerchTradeRightsService.detailTradeRights(dto);
    }

    @Override
    public void check(PCMerchTradeRightsDTO.IdCheckDTO dto) {
        pCMerchTradeRightsService.check(dto);
    }

    @Override
    public void receivedGet(PCMerchTradeRightsDTO.IdDTO qto) {
        pCMerchTradeRightsService.receivedGet(qto);
    }

    @Override
    public void send(PCMerchTradeRightsDTO.SendDTO dto) {
        pCMerchTradeRightsService.send(dto);
    }

}