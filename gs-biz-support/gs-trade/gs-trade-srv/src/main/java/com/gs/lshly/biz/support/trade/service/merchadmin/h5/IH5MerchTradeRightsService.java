package com.gs.lshly.biz.support.trade.service.merchadmin.h5;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.h5.trade.dto.H5MerchTradeRightsDTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.qto.H5MerchTradeRightsQTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeRightsVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeRightsDTO;

public interface IH5MerchTradeRightsService {

    PageData<H5MerchTradeRightsVO.RightList> pageData(H5MerchTradeRightsQTO.QTO qto);

    void addTradeRights(PCMerchTradeRightsDTO.ETO eto);

    void deleteTradeRights(PCMerchTradeRightsDTO.IdDTO dto);


    void editTradeRights(PCMerchTradeRightsDTO.ETO eto);

    H5MerchTradeRightsVO.DetailVO detailTradeRights(H5MerchTradeRightsDTO.IdDTO dto);

    void check(H5MerchTradeRightsDTO.IdCheckDTO dto);

    void receivedGet(H5MerchTradeRightsDTO.IdDTO qto);

    void send(H5MerchTradeRightsDTO.SendDTO dto);

}