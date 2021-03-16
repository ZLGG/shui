package com.gs.lshly.biz.support.trade.service.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradePayDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradePayQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradePayVO;

public interface IPCMerchTradePayService {

    PageData<PCMerchTradePayVO.ListVO> pageData(PCMerchTradePayQTO.QTO qto);

    void addTradePay(PCMerchTradePayDTO.ETO eto);

    void deleteTradePay(PCMerchTradePayDTO.IdDTO dto);


    void editTradePay(PCMerchTradePayDTO.ETO eto);

    PCMerchTradePayVO.DetailVO detailTradePay(PCMerchTradePayDTO.IdDTO dto);

}