package com.gs.lshly.biz.support.trade.service.merchadmin.pc;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeListVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeVO;

import java.util.List;

public interface IPCMerchTradeService {

    PageData<PCMerchTradeListVO.tradeVO> tradeListPageData(PCMerchTradeQTO.TradeList qto);

    PCMerchTradeListVO.tradeVO detail(PCMerchTradeDTO.IdDTO dto);


    List<PCMerchTradeListVO.innerGoodsIdAndName> innergoodsIdsCheck(PCMerchTradeDTO.GoodsIdsDTO dto);

    PCMerchTradeListVO.innerGoodsIdAndName innergoodsIdCheck(PCMerchTradeDTO.GoodsIdDTO dto);

    void editOrderAmount(PCMerchTradeDTO.orderAmountOrFreight dto);

    TradeVO.PayDatelistVO payDateList(TradeDTO.PayDateList dto);

    TradeVO.OperationlistVO operationList(TradeDTO.OperationList dto);

    List<PCMerchTradeListVO.tradeVOExport> export(PCMerchTradeQTO.IdListQTO qo);


}