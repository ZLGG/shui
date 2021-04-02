package com.gs.lshly.rpc.api.platadmin.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeDeliveryDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeDeliveryQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeDeliveryVO;

/**
*
* @author oy
* @since 2020-11-16
*/
public interface ITradeDeliveryRpc {

    PageData<TradeDeliveryVO.ListVO> pageData(TradeDeliveryQTO.QTO qto);

    void addTradeDelivery(TradeDeliveryDTO.ETO eto);

    void deleteTradeDelivery(TradeDeliveryDTO.IdDTO dto);


    void editTradeDelivery(TradeDeliveryDTO.ETO eto);

    TradeDeliveryVO.DetailVO detailTradeDelivery(TradeDeliveryDTO.IdDTO dto);

    ExportDataDTO deliveryExport(TradeDeliveryQTO.IdListQTO qo) throws Exception;
}