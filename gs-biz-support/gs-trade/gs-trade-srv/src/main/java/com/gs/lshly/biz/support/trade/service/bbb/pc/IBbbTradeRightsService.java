package com.gs.lshly.biz.support.trade.service.bbb.pc;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeRightsBuildDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeRightsDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.BbbTradeRightsQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbTradeRightsVO;

public interface IBbbTradeRightsService {


    PageData<BbbTradeRightsVO.ListVO> tradeRightsListData(BbbTradeRightsQTO.RightsList qto);

    BbbTradeRightsVO.DetailVO detailTradeRights(BbbTradeRightsDTO.IdDTO idDTO);

    void addTradeRights(BbbTradeRightsBuildDTO.ETO dto);

    void addAddress(BbbTradeRightsBuildDTO.AddAddressDTO dto);

    void confirmReceipt(BbbTradeRightsDTO.IdDTO dto);

    void applyAgain(BbbTradeRightsBuildDTO.ApplyAgain dto);
}