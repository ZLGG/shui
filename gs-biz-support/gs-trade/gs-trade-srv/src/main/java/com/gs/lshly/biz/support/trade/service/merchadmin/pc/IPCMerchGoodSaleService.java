package com.gs.lshly.biz.support.trade.service.merchadmin.pc;

import com.gs.lshly.common.struct.platadmin.trade.qto.TradeQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeVO;

import java.util.List;

public interface IPCMerchGoodSaleService {
    List<TradeVO.PayDatelistVO> exportPayDateList(TradeQTO.PayDateList qo)  throws Exception;

    List<TradeVO.OperationlistVO> exportOperationList(TradeQTO.OperationList qo)  throws Exception;
}
