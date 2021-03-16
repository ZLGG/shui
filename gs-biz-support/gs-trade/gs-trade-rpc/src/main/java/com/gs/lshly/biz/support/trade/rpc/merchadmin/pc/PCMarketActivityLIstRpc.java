package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;
import com.gs.lshly.biz.support.trade.service.platadmin.IMarketPtActivityService;
import com.gs.lshly.biz.support.trade.service.platadmin.impl.MarketPtActivityServiceImpl;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtActivityVO;
import com.gs.lshly.rpc.api.platadmin.merchant.IMarketAcivityListRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class PCMarketActivityLIstRpc implements IMarketAcivityListRpc {
    @Autowired
    private IMarketPtActivityService iMarketPtActivityService;

    @Override
    public List<MarketPtActivityVO.ListVO> list() {
       return iMarketPtActivityService.list();
    }
}
