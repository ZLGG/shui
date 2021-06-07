package com.gs.lshly.biz.support.trade.rpc.bbc;

import com.gs.lshly.biz.support.trade.service.bbc.IBbcTradeRightsService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeRightsBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeRightsDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeRightsQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeRightsVO;
import com.gs.lshly.rpc.api.bbc.trade.IBbcTradeRightsRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author oy
 * @since 2020-12-06
 */
@DubboService
public class BbcTradeRightsRpc implements IBbcTradeRightsRpc {
    @Autowired
    private IBbcTradeRightsService bbcTradeRightsService;

    @Override
    public PageData<BbcTradeRightsVO.ListVO> pageData(BbcTradeRightsQTO.QTO qto) {
        return bbcTradeRightsService.pageData(qto);
    }

    @Override
    public List<String> addTradeRights(BbcTradeRightsBuildDTO.ETO eto) {
        return bbcTradeRightsService.addTradeRights(eto);
    }

    @Override
    public BbcTradeRightsVO.DetailVO detailTradeRights(BbcTradeRightsDTO.IdDTO dto) {
        return bbcTradeRightsService.detailTradeRights(dto);
    }

    @Override
    public PageData<BbcTradeRightsVO.ListVO> tradeRightsListData(BbcTradeRightsQTO.RightsList qto) {
        return bbcTradeRightsService.tradeRightsListData(qto);
    }

    @Override
    public void returnGoods(BbcTradeRightsBuildDTO.RightsReturnGoodsLogistics dto) {
        bbcTradeRightsService.returnGoods(dto);
    }

    @Override
    public void confirmReceipt(BbcTradeRightsDTO.IdDTO dto) {
        bbcTradeRightsService.confirmReceipt(dto);
    }

    @Override
    public void revocationTradeRights(BbcTradeRightsBuildDTO.RevocationTradeRightsETO dto) {
        bbcTradeRightsService.revocationTradeRights(dto);
    }

    @Override
    public void deleteRecord(BbcTradeRightsDTO.IdDTO dto) {
        bbcTradeRightsService.deleteRecord(dto);
    }

    @Override
    public void updateTradeRights(BbcTradeRightsBuildDTO.UpdateETO dto) {
        bbcTradeRightsService.updateTradeRights(dto);
    }

    @Override
    public BbcTradeRightsVO.GoodsTotalVO goodsTotal(BbcTradeRightsBuildDTO.GoodsTotalDTO dto) {
        return bbcTradeRightsService.goodsTotal(dto);
    }

    @Override
    public void twoCheck(BbcTradeRightsDTO.IdDTO dto) {
        bbcTradeRightsService.twoCheck(dto);
    }

}