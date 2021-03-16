package com.gs.lshly.biz.support.trade.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeMarginDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeMarginDetailQTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeMarginQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeMarginDetailVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeMarginVO;
import com.gs.lshly.rpc.api.platadmin.trade.ITradeMarginRpc;
import com.gs.lshly.biz.support.trade.service.platadmin.ITradeMarginService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
*
* @author zst
* @since 2020-12-09
*/
@DubboService
public class TradeMarginRpc implements ITradeMarginRpc{

    @Autowired
    private ITradeMarginService  TradeMarginService;

    @Override
    public PageData<TradeMarginVO.ListVO> pageData(TradeMarginQTO.marginQTO qto){
        return TradeMarginService.pageData(qto);
    }

    @Override
    public void updateByQuota(TradeMarginDTO.QuotaDTO eto){
        TradeMarginService.updateByQuota(eto);
    }

    @Override
    public void updateByCharge(TradeMarginDTO.ChargeDTO eto){
        TradeMarginService.updateByCharge(eto);
    }

    @Override
    public void updateByDeduction(TradeMarginDTO.DeductionDTO eto){
        TradeMarginService.updateByDeduction(eto);
    }

    @Override
    public TradeMarginVO.DetailVO detailTradeMargin(TradeMarginDTO.IdDTO dto){
        return  TradeMarginService.detailTradeMargin(dto);
    }


    @Override
    public void InnerCreateShopMargin(TradeMarginDTO.InnerCreateMargin dto){
        TradeMarginService.InnerCreateShopMargin(dto);
    }


}