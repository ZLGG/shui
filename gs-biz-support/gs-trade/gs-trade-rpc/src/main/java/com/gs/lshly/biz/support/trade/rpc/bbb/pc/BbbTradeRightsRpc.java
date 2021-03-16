package com.gs.lshly.biz.support.trade.rpc.bbb.pc;

import com.gs.lshly.biz.support.trade.service.bbb.pc.IBbbTradeRightsService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeRightsBuildDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeRightsDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.BbbTradeRightsQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbTradeRightsVO;
import com.gs.lshly.rpc.api.bbb.pc.trade.IBbbTradeRightsRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author oy
* @since 2020-12-06
*/
@DubboService
public class BbbTradeRightsRpc implements IBbbTradeRightsRpc {
    @Autowired
    private IBbbTradeRightsService iBbbTradeRightsService;


    @Override
    public PageData<BbbTradeRightsVO.ListVO> tradeRightsListData(BbbTradeRightsQTO.RightsList qto) {
        return iBbbTradeRightsService.tradeRightsListData(qto);
    }

    @Override
    public BbbTradeRightsVO.DetailVO detailTradeRights(BbbTradeRightsDTO.IdDTO idDTO) {
        return iBbbTradeRightsService.detailTradeRights(idDTO);
    }

    @Override
    public void addTradeRights(BbbTradeRightsBuildDTO.ETO dto) {
        iBbbTradeRightsService.addTradeRights(dto);
    }

    @Override
    public void addAddress(BbbTradeRightsBuildDTO.AddAddressDTO dto) {
        iBbbTradeRightsService.addAddress(dto);
    }

    @Override
    public void confirmReceipt(BbbTradeRightsDTO.IdDTO dto) {
        iBbbTradeRightsService.confirmReceipt(dto);
    }

    @Override
    public void applyAgain(BbbTradeRightsBuildDTO.ApplyAgain dto) {
        iBbbTradeRightsService.applyAgain(dto);
    }
}