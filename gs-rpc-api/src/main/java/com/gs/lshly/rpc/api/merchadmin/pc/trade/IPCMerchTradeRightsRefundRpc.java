package com.gs.lshly.rpc.api.merchadmin.pc.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeRightsRefundDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeRightsRefundQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeRightsRefundVO;

/**
*
* @author zdf
* @since 2020-12-17
*/
public interface IPCMerchTradeRightsRefundRpc {

    PageData<PCMerchTradeRightsRefundVO.ListVO> pageData(PCMerchTradeRightsRefundQTO.QTO qto);

    void addTradeRightsRefund(PCMerchTradeRightsRefundDTO.ETO eto);

    void deleteTradeRightsRefund(PCMerchTradeRightsRefundDTO.IdDTO dto);


    void editTradeRightsRefund(PCMerchTradeRightsRefundDTO.ETO eto);

    PCMerchTradeRightsRefundVO.DetailVO detailTradeRightsRefund(PCMerchTradeRightsRefundDTO.IdDTO dto);

}