package com.gs.lshly.rpc.api.bbc.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeRightsBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeRightsDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeRightsLogQTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeRightsQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeRightsLogVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeRightsVO;

import java.util.List;

/**
 * @author oy
 * @since 2020-12-06
 */
public interface IBbcTradeRightsRpc {

    PageData<BbcTradeRightsVO.ListVO> pageData(BbcTradeRightsQTO.QTO qto);

    List<String> addTradeRights(BbcTradeRightsBuildDTO.ETO eto);

    BbcTradeRightsVO.DetailVO detailTradeRights(BbcTradeRightsDTO.IdDTO dto);

    PageData<BbcTradeRightsVO.ListVO> tradeRightsListData(BbcTradeRightsQTO.RightsList qto);

    void returnGoods(BbcTradeRightsBuildDTO.RightsReturnGoodsLogistics dto);

    void confirmReceipt(BbcTradeRightsDTO.IdDTO dto);

    void revocationTradeRights(BbcTradeRightsBuildDTO.RevocationTradeRightsETO dto);

    void deleteRecord(BbcTradeRightsDTO.IdDTO dto);

    void updateTradeRights(BbcTradeRightsBuildDTO.UpdateETO dto);

    BbcTradeRightsVO.GoodsTotalVO goodsTotal(BbcTradeRightsBuildDTO.GoodsTotalDTO dto);

    void twoCheck(BbcTradeRightsDTO.IdDTO dto);
}