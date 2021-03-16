package com.gs.lshly.biz.support.trade.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeComplaintDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeComplaintQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeComplaintVO;
import com.gs.lshly.biz.support.trade.service.platadmin.ITradeComplaintService;
import com.gs.lshly.rpc.api.platadmin.trade.ITradeComplaintRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author Starry
* @since 2020-12-24
*/
@DubboService
public class TradeComplaintRpc implements ITradeComplaintRpc {
    @Autowired
    private ITradeComplaintService  TradeComplaintService;


    @Override
    public PageData<TradeComplaintVO.DetailListVO> pageData(TradeComplaintQTO.QTO qto) {
        return TradeComplaintService.pageData(qto);
    }

    @Override
    public void deleteTradeComplaint(TradeComplaintDTO.IdListDTO dto) {
        TradeComplaintService.deleteTradeComplaint(dto);
    }

    @Override
    public void editTradeComplaint(TradeComplaintDTO.ETO eto){
        TradeComplaintService.editTradeComplaint(eto);
    }

    @Override
    public TradeComplaintVO.DetailVO detailTradeComplaint(TradeComplaintDTO.IdDTO dto){
        return  TradeComplaintService.detailTradeComplaint(dto);
    }

}