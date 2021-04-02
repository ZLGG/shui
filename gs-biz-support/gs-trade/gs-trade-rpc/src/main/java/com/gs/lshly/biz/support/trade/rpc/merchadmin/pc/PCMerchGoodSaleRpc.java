package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;

import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchGoodSaleService;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchGoodSaleRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class PCMerchGoodSaleRpc implements IPCMerchGoodSaleRpc {


    @Autowired
    private IPCMerchGoodSaleService ipcMerchGoodSaleService;
    //导出交易数据分析
    @Override
    public ExportDataDTO exportPayDateList(TradeQTO.PayDateList qo) throws Exception {
        return ExcelUtil.treatmentBean(ipcMerchGoodSaleService.exportPayDateList(qo), TradeVO.PayDatelistVO.class);
    }

    //导出商家运营概括
    @Override
    public ExportDataDTO exportOperationList(TradeQTO.OperationList qo) throws Exception {
        return ExcelUtil.treatmentBean(ipcMerchGoodSaleService.exportOperationList(qo), TradeVO.OperationlistVO.class);
    }
}
