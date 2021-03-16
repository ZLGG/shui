package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeRightsRefundDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeRightsRefundQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeRightsRefundVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchTradeRightsRefundRpc;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchTradeRightsRefundService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zdf
* @since 2020-12-17
*/
@DubboService
public class PCMerchTradeRightsRefundRpc implements IPCMerchTradeRightsRefundRpc{
    @Autowired
    private IPCMerchTradeRightsRefundService  pCMerchTradeRightsRefundService;

    @Override
    public PageData<PCMerchTradeRightsRefundVO.ListVO> pageData(PCMerchTradeRightsRefundQTO.QTO qto){
        return pCMerchTradeRightsRefundService.pageData(qto);
    }

    @Override
    public void addTradeRightsRefund(PCMerchTradeRightsRefundDTO.ETO eto){
        pCMerchTradeRightsRefundService.addTradeRightsRefund(eto);
    }

    @Override
    public void deleteTradeRightsRefund(PCMerchTradeRightsRefundDTO.IdDTO dto){
        pCMerchTradeRightsRefundService.deleteTradeRightsRefund(dto);
    }


    @Override
    public void editTradeRightsRefund(PCMerchTradeRightsRefundDTO.ETO eto){
        pCMerchTradeRightsRefundService.editTradeRightsRefund(eto);
    }

    @Override
    public PCMerchTradeRightsRefundVO.DetailVO detailTradeRightsRefund(PCMerchTradeRightsRefundDTO.IdDTO dto){
        return  pCMerchTradeRightsRefundService.detailTradeRightsRefund(dto);
    }

}