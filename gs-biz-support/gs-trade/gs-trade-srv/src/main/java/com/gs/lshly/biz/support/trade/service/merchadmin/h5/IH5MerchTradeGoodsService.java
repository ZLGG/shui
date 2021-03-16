package com.gs.lshly.biz.support.trade.service.merchadmin.h5;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.h5.trade.dto.H5MerchTradeGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.qto.H5MerchTradeGoodsQTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeGoodsVO;

public interface IH5MerchTradeGoodsService {

    PageData<H5MerchTradeGoodsVO.ListVO> pageData(H5MerchTradeGoodsQTO.QTO qto);

    void addTradeGoods(H5MerchTradeGoodsDTO.ETO eto);

    void deleteTradeGoods(H5MerchTradeGoodsDTO.IdDTO dto);


    void editTradeGoods(H5MerchTradeGoodsDTO.ETO eto);

    H5MerchTradeGoodsVO.DetailVO detailTradeGoods(H5MerchTradeGoodsDTO.IdDTO dto);

}