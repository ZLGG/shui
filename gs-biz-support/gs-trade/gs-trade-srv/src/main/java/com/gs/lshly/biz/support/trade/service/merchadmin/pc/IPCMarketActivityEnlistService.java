package com.gs.lshly.biz.support.trade.service.merchadmin.pc;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.qto.MarketPtActivityQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtActivityVO;

public interface IPCMarketActivityEnlistService {
    PageData<MarketPtActivityVO.MerchantActivity> activityPageData(MarketPtActivityQTO.QTO qto);
}
