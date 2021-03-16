package com.gs.lshly.biz.support.trade.service.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeRightsRefundDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeRightsRefundQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeRightsRefundVO;

public interface IPCMerchTradeRightsRefundService {

    PageData<PCMerchTradeRightsRefundVO.ListVO> pageData(PCMerchTradeRightsRefundQTO.QTO qto);

    void addTradeRightsRefund(PCMerchTradeRightsRefundDTO.ETO eto);

    void deleteTradeRightsRefund(PCMerchTradeRightsRefundDTO.IdDTO dto);


    void editTradeRightsRefund(PCMerchTradeRightsRefundDTO.ETO eto);

    PCMerchTradeRightsRefundVO.DetailVO detailTradeRightsRefund(PCMerchTradeRightsRefundDTO.IdDTO dto);

}