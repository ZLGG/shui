package com.gs.lshly.biz.support.trade.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeRightsDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeRightsDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeRightsRefundDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeRightsQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeRightsRefundVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeRightsVO;

public interface ITradeRightsService {

    PageData<TradeRightsVO.RightsListVO> pageData(TradeRightsQTO.StateDTO qto);

    void addTradeRights(TradeRightsDTO.ETO eto);

    void deleteTradeRights(TradeRightsDTO.IdDTO dto);


    void editTradeRights(TradeRightsDTO.ETO eto);

    TradeRightsVO.DetailVO detailTradeRights(TradeRightsDTO.IdDTO dto);

    TradeRightsVO.RightsListViewVO get(TradeRightsDTO.IdDTO dto);

    PageData<TradeRightsVO.RightsRefundListVO> getRightsRefundList(TradeRightsQTO.StateRefundDTO qto);

    TradeRightsVO.RightsRefundViewVO getRightsRefund(TradeRightsDTO.IdDTO dto);

    void getRightsRefundAmont(TradeRightsDTO.RefundDTO dto);

    PageData<TradeRightsRefundVO.DetailVO> rightsRefunList(TradeRightsQTO.NewQTO qto);

    TradeRightsRefundVO.DetailViewVO rightsRefunView(TradeRightsRefundDTO.IdDTO dto);

    void setPlatformChenkReason(TradeRightsDTO.PlatformCheckReasonDTO dto);

    void platformCheckReason(TradeRightsDTO.IdDTO dto);
}