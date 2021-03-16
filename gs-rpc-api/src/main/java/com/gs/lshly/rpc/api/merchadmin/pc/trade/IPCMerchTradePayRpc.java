package com.gs.lshly.rpc.api.merchadmin.pc.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradePayDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradePayQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradePayVO;

/**
*
* @author oy
* @since 2020-11-16
*/
public interface IPCMerchTradePayRpc {

    PageData<PCMerchTradePayVO.ListVO> pageData(PCMerchTradePayQTO.QTO qto);

    void addTradePay(PCMerchTradePayDTO.ETO eto);

    void deleteTradePay(PCMerchTradePayDTO.IdDTO dto);


    void editTradePay(PCMerchTradePayDTO.ETO eto);

    PCMerchTradePayVO.DetailVO detailTradePay(PCMerchTradePayDTO.IdDTO dto);

}