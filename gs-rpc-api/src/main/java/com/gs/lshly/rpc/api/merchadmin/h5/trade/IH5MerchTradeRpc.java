package com.gs.lshly.rpc.api.merchadmin.h5.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.h5.trade.dto.H5MerchTradeDTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.qto.H5MerchTradeQTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeListVO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeVO;

/**
*
* @author oy
* @since 2020-11-16
*/
public interface IH5MerchTradeRpc {


    PageData<H5MerchTradeListVO.tradeVO> tradeListPageData(H5MerchTradeQTO.TradeList qto);

    H5MerchTradeListVO.tradeVO detail(H5MerchTradeDTO.IdDTO dto);

    void editOrderAmount(H5MerchTradeDTO.orderAmountOrFreight dto);

    void editComment(H5MerchTradeDTO.editComment dto);
}