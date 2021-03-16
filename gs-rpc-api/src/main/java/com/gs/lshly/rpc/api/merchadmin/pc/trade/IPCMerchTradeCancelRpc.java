package com.gs.lshly.rpc.api.merchadmin.pc.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeCancelDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeCancelQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeCancelVO;

/**
*
* @author oy
* @since 2020-11-20
*/
public interface IPCMerchTradeCancelRpc {

    PageData<PCMerchTradeCancelVO.ListVO> pageData(PCMerchTradeCancelQTO.QTO qto);

    ResponseData<Void> examineTradeCancel(PCMerchTradeCancelDTO.ExamineDTO eto);

    PCMerchTradeCancelVO.DetailVO detailTradeCancel(PCMerchTradeCancelDTO.IdDTO dto);

}