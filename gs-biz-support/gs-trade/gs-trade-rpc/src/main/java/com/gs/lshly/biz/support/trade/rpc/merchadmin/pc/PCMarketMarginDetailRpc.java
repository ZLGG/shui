package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;

import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMarketMarginDetailService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeMarginDetailDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeMarginDetailQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeMarginDetailVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeMarginVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMarketMarginDetailRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class PCMarketMarginDetailRpc implements IPCMarketMarginDetailRpc {
    @Autowired
    private IPCMarketMarginDetailService ipcMarketMarginDetailService;

    @Override
    public PageData<TradeMarginDetailVO.ListVO> PageData(TradeMarginDetailQTO.typeQTO qto) {
        return ipcMarketMarginDetailService.PageData(qto);
    }

    @Override
    public TradeMarginDetailVO.ListVO detailTradeMargin(TradeMarginDetailDTO.IdDTO dto){
        return  ipcMarketMarginDetailService.detailTradeMargin(dto);
    }

    @Override
    public TradeMarginVO.ListVO view(TradeMarginDetailQTO.viewQTO qto){
        return  ipcMarketMarginDetailService.view(qto);
    }

}
