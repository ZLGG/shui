package com.gs.lshly.biz.support.trade.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeDeliveryDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeDeliveryQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeDeliveryVO;

import java.util.List;

public interface ITradeDeliveryService {

    PageData<TradeDeliveryVO.ListVO> pageData(TradeDeliveryQTO.QTO qto);

    void addTradeDelivery(TradeDeliveryDTO.ETO eto);

    void deleteTradeDelivery(TradeDeliveryDTO.IdDTO dto);


    void editTradeDelivery(TradeDeliveryDTO.ETO eto);

    TradeDeliveryVO.DetailVO detailTradeDelivery(TradeDeliveryDTO.IdDTO dto);

    List<TradeDeliveryVO.ListExportVO> deliveryExport(TradeDeliveryQTO.IdListQTO qo);
}