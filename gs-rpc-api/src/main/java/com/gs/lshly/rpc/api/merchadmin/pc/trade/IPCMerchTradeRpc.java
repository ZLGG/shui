package com.gs.lshly.rpc.api.merchadmin.pc.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeListVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeGoodsDTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeGoodsVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeVO;

import java.util.List;

/**
*
* @author oy
* @since 2020-11-16
*/
public interface IPCMerchTradeRpc {

    PageData<PCMerchTradeListVO.tradeVO> tradeListPageData(PCMerchTradeQTO.TradeList qto);


    PCMerchTradeListVO.tradeVO detail(PCMerchTradeDTO.IdDTO dto);

    List<PCMerchTradeListVO.innerGoodsIdAndName> innergoodsIdsCheck(PCMerchTradeDTO.GoodsIdsDTO dto);

    PCMerchTradeListVO.innerGoodsIdAndName innergoodsIdCheck(PCMerchTradeDTO.GoodsIdDTO dto);


    void editOrderAmount(PCMerchTradeDTO.orderAmountOrFreight dto);

    TradeVO.PayDatelistVO payDateList(TradeDTO.PayDateList dto);

    TradeVO.OperationlistVO operationList(TradeDTO.OperationList dto);
}