package com.gs.lshly.biz.support.trade.service.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeRightsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeRightsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeRightsVO;

public interface IPCMerchTradeRightsService {

    PageData<PCMerchTradeRightsVO.RightList> pageData(PCMerchTradeRightsQTO.QTO qto);

    void addTradeRights(PCMerchTradeRightsDTO.ETO eto);

    void deleteTradeRights(PCMerchTradeRightsDTO.IdDTO dto);


    void editTradeRights(PCMerchTradeRightsDTO.ETO eto);

    PCMerchTradeRightsVO.DetailVO detailTradeRights(PCMerchTradeRightsDTO.IdDTO dto);

    void check(PCMerchTradeRightsDTO.IdCheckDTO dto);

    void receivedGet(PCMerchTradeRightsDTO.IdDTO qto);

    void send(PCMerchTradeRightsDTO.SendDTO dto);
}