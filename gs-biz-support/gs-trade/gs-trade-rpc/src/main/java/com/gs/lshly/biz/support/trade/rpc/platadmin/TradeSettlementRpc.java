package com.gs.lshly.biz.support.trade.rpc.platadmin;
import com.gs.lshly.biz.support.trade.service.platadmin.ITradeSettlementService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeSettlementDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeSettlementQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.rpc.api.platadmin.trade.ITradeSettlementRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;


/**
*
* @author zst
* @since 2020-11-30
*/
@DubboService
public class TradeSettlementRpc implements ITradeSettlementRpc {

    @Autowired
    private ITradeSettlementService iTradeSettlementService;

    @Override
    public PageData<TradeSettlementVO.ListVO> pageData(TradeSettlementQTO.settlementList qto){
        return iTradeSettlementService.pageData(qto);
    }

    @Override
    public void addTradeSettlement(TradeSettlementDTO.ETO eto){
        iTradeSettlementService.addTradeSettlement(eto);
    }

    @Override
    public TradeSettlementVO.DetailVO detailTradeSettlement(TradeSettlementDTO.IdOfDTO dto){
        return  iTradeSettlementService.detailTradeSettlement(dto);
    }

    @Override
    public ExportDataDTO export(TradeSettlementQTO.settlementList qo) throws Exception {
        PageData<TradeSettlementVO.ListVO> pageData = pageData(qo);
        return ExcelUtil.treatmentBean(pageData.getContent(), TradeSettlementVO.ListVO.class);
    }

    @Override
    public boolean summaryOrderData() {
        return iTradeSettlementService.summaryOrderData();
    }

    @Override
    public String settleConfirmation(String tradeCode) {
        return iTradeSettlementService.settleConfirmation(tradeCode);
    }


}