package com.gs.lshly.biz.support.trade.service.bbc;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeRightsBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeRightsDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeRightsQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeRightsVO;

public interface IBbcTradeRightsService {

    PageData<BbcTradeRightsVO.ListVO> pageData(BbcTradeRightsQTO.QTO qto);

    void addTradeRights(BbcTradeRightsBuildDTO.ETO eto);

    BbcTradeRightsVO.DetailVO detailTradeRights(BbcTradeRightsDTO.IdDTO dto);

    PageData<BbcTradeRightsVO.ListVO> tradeRightsListData(BbcTradeRightsQTO.RightsList qto);

    void returnGoods(BbcTradeRightsBuildDTO.RightsReturnGoodsLogistics dto);

    void confirmReceipt(BbcTradeRightsDTO.IdDTO dto);

    void revocationTradeRights(BbcTradeRightsBuildDTO.RevocationTradeRightsETO dto);

    void deleteRecord(BbcTradeRightsDTO.IdDTO dto);
}