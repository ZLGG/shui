package com.gs.lshly.rpc.api.merchadmin.h5.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.h5.trade.dto.H5MerchTradeCancelDTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.qto.H5MerchTradeCancelQTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeCancelVO;

/**
*
* @author oy
* @since 2020-11-20
*/
public interface
IH5MerchTradeCancelRpc {

    PageData<H5MerchTradeCancelVO.ListVO> pageData(H5MerchTradeCancelQTO.QTO qto);

    void addTradeCancel(H5MerchTradeCancelDTO.ETO eto);

    void deleteTradeCancel(H5MerchTradeCancelDTO.IdDTO dto);


    void editTradeCancel(H5MerchTradeCancelDTO.ETO eto);

    H5MerchTradeCancelVO.DetailVO detailTradeCancel(H5MerchTradeCancelDTO.IdDTO dto);

}