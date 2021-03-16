package com.gs.lshly.rpc.api.bbb.h5.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeRightsBuildDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeRightsDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5TradeRightsQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeRightsVO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeRightsBuildDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeRightsDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.BbbTradeRightsQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbTradeRightsVO;

/**
*
* @author oy
* @since 2021-01-04
*/
public interface IBbbH5TradeRightsRpc {


    PageData<BbbH5TradeRightsVO.ListVO> tradeRightsListData(BbbH5TradeRightsQTO.RightsList qto);

    BbbH5TradeRightsVO.DetailVO detailTradeRights(BbbH5TradeRightsDTO.IdDTO idDTO);

    void addTradeRights(BbbH5TradeRightsBuildDTO.ETO dto);

    void addAddress(BbbH5TradeRightsBuildDTO.AddAddressDTO dto);

    void confirmReceipt(BbbH5TradeRightsDTO.IdDTO dto);

    void applyAgain(BbbH5TradeRightsBuildDTO.ApplyAgain dto);
}