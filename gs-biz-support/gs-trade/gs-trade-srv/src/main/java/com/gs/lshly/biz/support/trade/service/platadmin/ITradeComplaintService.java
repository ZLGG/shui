package com.gs.lshly.biz.support.trade.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeComplaintDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeComplaintQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeComplaintVO;

public interface ITradeComplaintService {

    /**
     * 查询订单投诉分页列表
     * @param qto
     * @return
     */
    PageData<TradeComplaintVO.DetailListVO> pageData(TradeComplaintQTO.QTO qto);


    /**
     * 批量删除
     * @param dto
     */
    void deleteTradeComplaint(TradeComplaintDTO.IdListDTO dto);


    /**
     * 处理投诉
     * @param eto
     */
    void editTradeComplaint(TradeComplaintDTO.ETO eto);


    /**
     * 查询投诉详情
     * @param dto
     * @return
     */
    TradeComplaintVO.DetailVO detailTradeComplaint(TradeComplaintDTO.IdDTO dto);

}