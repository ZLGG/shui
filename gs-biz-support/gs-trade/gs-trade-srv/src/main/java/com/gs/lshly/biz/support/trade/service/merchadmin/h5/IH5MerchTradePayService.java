package com.gs.lshly.biz.support.trade.service.merchadmin.h5;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.h5.trade.dto.H5MerchTradePayDTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.qto.H5MerchTradePayQTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradePayVO;

public interface IH5MerchTradePayService {

    PageData<H5MerchTradePayVO.ListVO> pageData(H5MerchTradePayQTO.QTO qto);

    void addTradePay(H5MerchTradePayDTO.ETO eto);

    void deleteTradePay(H5MerchTradePayDTO.IdDTO dto);


    void editTradePay(H5MerchTradePayDTO.ETO eto);

    H5MerchTradePayVO.DetailVO detailTradePay(H5MerchTradePayDTO.IdDTO dto);

}