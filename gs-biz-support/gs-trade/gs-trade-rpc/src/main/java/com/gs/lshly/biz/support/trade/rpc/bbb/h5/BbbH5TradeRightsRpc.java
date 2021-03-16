package com.gs.lshly.biz.support.trade.rpc.bbb.h5;

import com.gs.lshly.biz.support.trade.service.bbb.h5.IBbbH5TradeRightsService;
import com.gs.lshly.biz.support.trade.service.bbb.pc.IBbbTradeRightsService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeRightsBuildDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeRightsDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5TradeRightsQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeRightsVO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeRightsBuildDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeRightsDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.BbbTradeRightsQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbTradeRightsVO;
import com.gs.lshly.rpc.api.bbb.h5.trade.IBbbH5TradeRightsRpc;
import com.gs.lshly.rpc.api.bbb.pc.trade.IBbbTradeRightsRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author oy
* @since 2020-12-06
*/
@DubboService
public class BbbH5TradeRightsRpc implements IBbbH5TradeRightsRpc {
    @Autowired
    private IBbbH5TradeRightsService iBbbH5TradeRightsService;


    @Override
    public PageData<BbbH5TradeRightsVO.ListVO> tradeRightsListData(BbbH5TradeRightsQTO.RightsList qto) {
        return iBbbH5TradeRightsService.tradeRightsListData(qto);
    }

    @Override
    public BbbH5TradeRightsVO.DetailVO detailTradeRights(BbbH5TradeRightsDTO.IdDTO idDTO) {
        return iBbbH5TradeRightsService.detailTradeRights(idDTO);
    }

    @Override
    public void addTradeRights(BbbH5TradeRightsBuildDTO.ETO dto) {
        iBbbH5TradeRightsService.addTradeRights(dto);
    }

    @Override
    public void addAddress(BbbH5TradeRightsBuildDTO.AddAddressDTO dto) {
        iBbbH5TradeRightsService.addAddress(dto);
    }

    @Override
    public void confirmReceipt(BbbH5TradeRightsDTO.IdDTO dto) {
        iBbbH5TradeRightsService.confirmReceipt(dto);
    }

    @Override
    public void applyAgain(BbbH5TradeRightsBuildDTO.ApplyAgain dto) {
        iBbbH5TradeRightsService.applyAgain(dto);
    }
}