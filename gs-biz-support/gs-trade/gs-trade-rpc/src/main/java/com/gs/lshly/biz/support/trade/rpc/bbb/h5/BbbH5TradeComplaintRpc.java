package com.gs.lshly.biz.support.trade.rpc.bbb.h5;

import com.gs.lshly.biz.support.trade.service.bbb.h5.IBbbH5TradeComplaintService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeComplaintDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5TradeComplaintQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeComplaintVO;
import com.gs.lshly.rpc.api.bbb.h5.trade.IBbbH5TradeComplaintRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class BbbH5TradeComplaintRpc implements IBbbH5TradeComplaintRpc {
    @Autowired
    private IBbbH5TradeComplaintService iBbbH5TradeComplaintService;
    @Override
    public PageData<BbbH5TradeComplaintVO.DetailListVO> pageData(BbbH5TradeComplaintQTO.QTO qto) {
        return iBbbH5TradeComplaintService.pageData(qto);
    }

    @Override
    public void addTradeComplaint(BbbH5TradeComplaintDTO.DetailEto eto) {
        iBbbH5TradeComplaintService.addTradeComplaint(eto);
    }

    @Override
    public void editTradeComplaint(BbbH5TradeComplaintDTO.CancelIdeaDTO dto) {
        iBbbH5TradeComplaintService.editTradeComplaint(dto);
    }

    @Override
    public BbbH5TradeComplaintVO.DetailVO detailTradeComplaint(BbbH5TradeComplaintDTO.IdDTO dto) {
        return iBbbH5TradeComplaintService.detailTradeComplaint(dto);
    }
}
