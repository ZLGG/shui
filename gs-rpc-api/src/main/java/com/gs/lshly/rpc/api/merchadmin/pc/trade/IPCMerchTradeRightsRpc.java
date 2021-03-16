package com.gs.lshly.rpc.api.merchadmin.pc.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeRightsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeRightsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeRightsVO;

/**
*
* @author zdf
* @since 2020-12-17
*/
public interface IPCMerchTradeRightsRpc {

    PageData<PCMerchTradeRightsVO.RightList> pageData(PCMerchTradeRightsQTO.QTO qto);

    void addTradeRights(PCMerchTradeRightsDTO.ETO eto);

    void deleteTradeRights(PCMerchTradeRightsDTO.IdDTO dto);


    void editTradeRights(PCMerchTradeRightsDTO.ETO eto);

    PCMerchTradeRightsVO.DetailVO detailTradeRights(PCMerchTradeRightsDTO.IdDTO qto);

    void check(PCMerchTradeRightsDTO.IdCheckDTO dto);

    void receivedGet(PCMerchTradeRightsDTO.IdDTO qto);

    void send(PCMerchTradeRightsDTO.SendDTO qto);

}