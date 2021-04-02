package com.gs.lshly.rpc.api.merchadmin.pc.trade;

import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeQTO;

public interface IPCMerchGoodSaleRpc {
    //导出交易数据分析
    ExportDataDTO exportPayDateList(TradeQTO.PayDateList qo) throws Exception;

    //导出商家运营概括
    ExportDataDTO exportOperationList(TradeQTO.OperationList qo) throws Exception;
}
