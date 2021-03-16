package com.gs.lshly.biz.support.trade.service.bbb.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.PCBbbTradeComplaintDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbTradeComplaintQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbTradeComplaintVO;

public interface IPCBbbTradeComplaintService {

    /**
     * 订单投诉记录
     * @param qto
     * @return
     */
    PageData<PCBbbTradeComplaintVO.DetailListVO> pageData(PCBbbTradeComplaintQTO.QTO qto);

    /**
     * 添加订单投诉
     * @param eto
     */
    void addTradeComplaint(PCBbbTradeComplaintDTO.DetailEto eto);


    /**
     * 撤销申诉
     * @param dto
     */
    void editTradeComplaint(PCBbbTradeComplaintDTO.CancelIdeaDTO dto);

    /**
     * 订单投诉详情
     * @param dto
     * @return
     */
    PCBbbTradeComplaintVO.DetailVO detailTradeComplaint(PCBbbTradeComplaintDTO.IdDTO dto);

}