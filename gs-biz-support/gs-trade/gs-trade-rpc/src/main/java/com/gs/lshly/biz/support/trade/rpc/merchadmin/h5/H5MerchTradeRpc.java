package com.gs.lshly.biz.support.trade.rpc.merchadmin.h5;

import com.gs.lshly.biz.support.trade.service.merchadmin.h5.IH5MerchTradeService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.h5.trade.dto.H5MerchTradeDTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.qto.H5MerchTradeQTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeListVO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeVO;
import com.gs.lshly.rpc.api.merchadmin.h5.trade.IH5MerchTradeRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author oy
* @since 2020-11-16
*/
@DubboService
public class H5MerchTradeRpc implements IH5MerchTradeRpc{
    @Autowired
    private IH5MerchTradeService  h5MerchTradeService;


    @Override
    public PageData<H5MerchTradeListVO.tradeVO> tradeListPageData(H5MerchTradeQTO.TradeList qto) {
        return h5MerchTradeService.tradeListPageData(qto);
    }

    @Override
    public H5MerchTradeListVO.tradeVO detail(H5MerchTradeDTO.IdDTO dto) {
        return h5MerchTradeService.detail(dto);
    }

    @Override
    public void editOrderAmount(H5MerchTradeDTO.orderAmountOrFreight dto) {
        h5MerchTradeService.editOrderAmount(dto);
    }

    @Override
    public void editComment(H5MerchTradeDTO.editComment dto) {
        h5MerchTradeService.editComment(dto);
    }

}