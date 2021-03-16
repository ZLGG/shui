package com.gs.lshly.biz.support.trade.rpc.bbb.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.PCBbbTradeComplaintDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbTradeComplaintQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbTradeComplaintVO;
import com.gs.lshly.rpc.api.bbb.pc.trade.IPCBbbTradeComplaintRpc;
import com.gs.lshly.biz.support.trade.service.bbb.pc.IPCBbbTradeComplaintService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author Starry
* @since 2020-12-23
*/
@DubboService
public class PCBbbTradeComplaintRpc implements IPCBbbTradeComplaintRpc{
    @Autowired
    private IPCBbbTradeComplaintService  pCBbbTradeComplaintService;


    @Override
    public PageData<PCBbbTradeComplaintVO.DetailListVO> pageData(PCBbbTradeComplaintQTO.QTO qto) {
        return pCBbbTradeComplaintService.pageData(qto);
    }

    @Override
    public void addTradeComplaint(PCBbbTradeComplaintDTO.DetailEto eto) {
        pCBbbTradeComplaintService.addTradeComplaint(eto);
    }


    @Override
    public void editTradeComplaint(PCBbbTradeComplaintDTO.CancelIdeaDTO dto){
        pCBbbTradeComplaintService.editTradeComplaint(dto);
    }

    @Override
    public PCBbbTradeComplaintVO.DetailVO detailTradeComplaint(PCBbbTradeComplaintDTO.IdDTO dto){
        return  pCBbbTradeComplaintService.detailTradeComplaint(dto);
    }

}