package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;


import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMarketActivityEnlistService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.qto.MarketPtActivityQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtActivityVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMarketActivityEnlistRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class PCMarketActivityEnlistRpc implements IPCMarketActivityEnlistRpc {
    @Autowired
    private IPCMarketActivityEnlistService ipcMarketActivityEnlistService;
    @Override
    public PageData<MarketPtActivityVO.MerchantActivity> activityPageData(MarketPtActivityQTO.QTO qto) {

        return ipcMarketActivityEnlistService.activityPageData(qto);
    }
}
