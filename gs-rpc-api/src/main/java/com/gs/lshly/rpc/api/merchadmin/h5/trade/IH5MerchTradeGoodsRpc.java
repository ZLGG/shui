package com.gs.lshly.rpc.api.merchadmin.h5.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.h5.trade.dto.H5MerchTradeGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.qto.H5MerchTradeGoodsQTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeGoodsVO;

/**
*
* @author oy
* @since 2020-11-16
*/
public interface IH5MerchTradeGoodsRpc {

    PageData<H5MerchTradeGoodsVO.ListVO> pageData(H5MerchTradeGoodsQTO.QTO qto);

    void addTradeGoods(H5MerchTradeGoodsDTO.ETO eto);

    void deleteTradeGoods(H5MerchTradeGoodsDTO.IdDTO dto);


    void editTradeGoods(H5MerchTradeGoodsDTO.ETO eto);

    H5MerchTradeGoodsVO.DetailVO detailTradeGoods(H5MerchTradeGoodsDTO.IdDTO dto);

}