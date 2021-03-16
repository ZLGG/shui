package com.gs.lshly.rpc.api.platadmin.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeComplaintDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeComplaintQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeComplaintVO;

/**
*
* @author Starry
* @since 2020-12-24
*/
public interface ITradeComplaintRpc {
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


    void editTradeComplaint(TradeComplaintDTO.ETO eto);

    TradeComplaintVO.DetailVO detailTradeComplaint(TradeComplaintDTO.IdDTO dto);

}