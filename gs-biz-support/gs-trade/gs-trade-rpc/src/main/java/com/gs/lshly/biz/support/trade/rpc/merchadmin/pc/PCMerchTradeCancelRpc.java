package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;

import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchTradeCancelService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeCancelDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeCancelQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeCancelVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchTradeCancelRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author oy
* @since 2020-11-20
*/
@DubboService
public class PCMerchTradeCancelRpc implements IPCMerchTradeCancelRpc{
    @Autowired
    private IPCMerchTradeCancelService  pCMerchTradeCancelService;

    @Override
    public PageData<PCMerchTradeCancelVO.ListVO> pageData(PCMerchTradeCancelQTO.QTO qto){
        return pCMerchTradeCancelService.pageData(qto);
    }

    @Override
    public ResponseData<Void> examineTradeCancel(PCMerchTradeCancelDTO.ExamineDTO eto) {
        return pCMerchTradeCancelService.examineTradeCancel(eto);
    }

    @Override
    public PCMerchTradeCancelVO.DetailVO detailTradeCancel(PCMerchTradeCancelDTO.IdDTO dto){
        return  pCMerchTradeCancelService.detailTradeCancel(dto);
    }

}