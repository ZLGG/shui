package com.gs.lshly.biz.support.trade.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeRightsDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeRightsDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeRightsRefundDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeRightsQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeRightsRefundVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeRightsVO;
import com.gs.lshly.rpc.api.platadmin.trade.ITradeRightsRpc;
import com.gs.lshly.biz.support.trade.service.platadmin.ITradeRightsService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zdf
* @since 2020-12-22
*/
@DubboService
public class TradeRightsRpc implements ITradeRightsRpc{
    @Autowired
    private ITradeRightsService  TradeRightsService;

    @Override
    public PageData<TradeRightsVO.RightsListVO> pageData(TradeRightsQTO.StateDTO qto){
        return TradeRightsService.pageData(qto);
    }

    @Override
    public void addTradeRights(TradeRightsDTO.ETO eto){
        TradeRightsService.addTradeRights(eto);
    }

    @Override
    public void deleteTradeRights(TradeRightsDTO.IdDTO dto){
        TradeRightsService.deleteTradeRights(dto);
    }


    @Override
    public void editTradeRights(TradeRightsDTO.ETO eto){
        TradeRightsService.editTradeRights(eto);
    }

    @Override
    public TradeRightsVO.DetailVO detailTradeRights(TradeRightsDTO.IdDTO dto){
        return  TradeRightsService.detailTradeRights(dto);
    }

    @Override
    public TradeRightsVO.RightsListViewVO get(TradeRightsDTO.IdDTO dto) {
        return TradeRightsService.get(dto);
    }

    @Override
    public PageData<TradeRightsVO.RightsRefundListVO> getRightsRefundList(TradeRightsQTO.StateRefundDTO qto) {
        return TradeRightsService.getRightsRefundList(qto);
    }

    @Override
    public TradeRightsVO.RightsRefundViewVO getRightsRefund(TradeRightsDTO.IdDTO dto) {
        return TradeRightsService.getRightsRefund(dto);
    }

    @Override
    public void  getRightsRefundAmont(TradeRightsDTO.RefundDTO dto) {
       TradeRightsService.getRightsRefundAmont(dto);
    }

    @Override
    public PageData<TradeRightsRefundVO.DetailVO> rightsRefunList(TradeRightsQTO.NewQTO qto) {
        return TradeRightsService.rightsRefunList(qto);
    }

    @Override
    public TradeRightsRefundVO.DetailViewVO rightsRefunView(TradeRightsRefundDTO.IdDTO dto) {
        return TradeRightsService.rightsRefunView(dto);
    }

    @Override
    public void setPlatformChenkReason(TradeRightsDTO.PlatformCheckReasonDTO dto) {
        TradeRightsService.setPlatformChenkReason(dto);
    }

    @Override
    public void platformCheckReason(TradeRightsDTO.IdDTO dto) {
        TradeRightsService.platformCheckReason(dto);
    }

}