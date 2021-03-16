package com.gs.lshly.biz.support.trade.service.merchadmin.h5;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.h5.trade.dto.H5MerchTradeDTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.qto.H5MerchTradeQTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeListVO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeVO;

public interface IH5MerchTradeService {


    void editOrderAmount(H5MerchTradeDTO.orderAmountOrFreight dto);

    void editComment(H5MerchTradeDTO.editComment dto);

    H5MerchTradeListVO.tradeVO detail(H5MerchTradeDTO.IdDTO dto);

    PageData<H5MerchTradeListVO.tradeVO> tradeListPageData(H5MerchTradeQTO.TradeList qto);
}