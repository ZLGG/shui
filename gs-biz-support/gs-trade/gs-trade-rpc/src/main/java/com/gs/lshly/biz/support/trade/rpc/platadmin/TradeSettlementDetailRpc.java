package com.gs.lshly.biz.support.trade.rpc.platadmin;
import com.gs.lshly.biz.support.trade.service.platadmin.ITradeSettlementDetailService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeSettlementDetailDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeSettlementDetailQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementDetailVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.rpc.api.platadmin.trade.ITradeSettlementDetailRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zst
* @since 2020-12-01
*/
@DubboService
public class TradeSettlementDetailRpc implements ITradeSettlementDetailRpc {
    @Autowired
    private ITradeSettlementDetailService TradeSettlementDetailService;

    @Override
    public PageData<TradeSettlementDetailVO.ListVO> pageData(TradeSettlementDetailQTO.ListQTO qto){
        return TradeSettlementDetailService.pageData(qto);
    }

    @Override
    public void addTradeSettlementDetail(TradeSettlementDetailDTO.ETO eto){
        TradeSettlementDetailService.addTradeSettlementDetail(eto);
    }

    @Override
    public TradeSettlementDetailVO.DetailVO detailTradeSettlementDetail(TradeSettlementDetailDTO.IdDTO dto){
        return  TradeSettlementDetailService.detailTradeSettlementDetail(dto);
    }

    @Override
    public ExportDataDTO export(TradeSettlementDetailQTO.ListQTO qo) throws Exception {
        PageData<TradeSettlementDetailVO.ListVO> pageData = pageData(qo);
        return ExcelUtil.treatmentBean(pageData.getContent(), TradeSettlementDetailVO.ListVO.class);
    }

}