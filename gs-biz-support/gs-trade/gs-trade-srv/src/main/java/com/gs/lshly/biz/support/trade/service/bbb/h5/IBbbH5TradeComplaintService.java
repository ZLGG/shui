package com.gs.lshly.biz.support.trade.service.bbb.h5;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeComplaintDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5TradeComplaintQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeComplaintVO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.PCBbbTradeComplaintDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbTradeComplaintQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbTradeComplaintVO;

public interface IBbbH5TradeComplaintService {

    /**
     * 订单投诉记录
     * @param qto
     * @return
     */
    PageData<BbbH5TradeComplaintVO.DetailListVO> pageData(BbbH5TradeComplaintQTO.QTO qto);

    /**
     * 添加订单投诉
     * @param eto
     */
    void addTradeComplaint(BbbH5TradeComplaintDTO.DetailEto eto);


    /**
     * 撤销申诉
     * @param dto
     */
    void editTradeComplaint(BbbH5TradeComplaintDTO.CancelIdeaDTO dto);

    /**
     * 订单投诉详情
     * @param dto
     * @return
     */
   BbbH5TradeComplaintVO.DetailVO detailTradeComplaint(BbbH5TradeComplaintDTO.IdDTO dto);

}