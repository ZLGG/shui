package com.gs.lshly.rpc.api.merchadmin.pc.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.qto.MarketPtActivityQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtActivityVO;

public interface IPCMarketActivityEnlistRpc{
    PageData<MarketPtActivityVO.MerchantActivity>  activityPageData(MarketPtActivityQTO.QTO qto);
}
