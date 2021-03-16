package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;

import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMarketSettlementService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeSettlementDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeSettlementQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementDetailVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMarketSettlementRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class PCMarketSettlementRpc implements IPCMarketSettlementRpc {
    @Autowired
    private IPCMarketSettlementService ipcMarketSettlementService;

    @Override
    public PageData<TradeSettlementVO.ListVO> settlementPageData(TradeSettlementQTO.PcQTO qto) {
        return ipcMarketSettlementService.settlementPageData(qto);
    }

    @Override
    public TradeSettlementVO.DetailVO detailTradeSettlement(TradeSettlementDTO.IdOfDTO dto){
        return  ipcMarketSettlementService.detailTradeSettlement(dto);
    }

    @Override
    public ExportDataDTO export(TradeSettlementQTO.PcQTO qo) throws Exception {
        PageData<TradeSettlementVO.ListVO> pageData = settlementPageData(qo);
        return ExcelUtil.treatmentBean(pageData.getContent(), TradeSettlementVO.ListVO.class);
    }

    @Override
    public ExportDataDTO DownloadExport(TradeSettlementQTO.DownloadExportQTO qo) throws Exception {
        return ExcelUtil.treatmentBean(ipcMarketSettlementService.DownloadExport(qo), TradeSettlementDetailVO.ListVO.class);
    }
}
