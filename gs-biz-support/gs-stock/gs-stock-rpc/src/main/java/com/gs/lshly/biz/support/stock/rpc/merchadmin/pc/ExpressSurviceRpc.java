package com.gs.lshly.biz.support.stock.rpc.merchadmin.pc;

import com.gs.lshly.biz.support.stock.service.merchadmin.pc.IPCExpressSurviceService;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockKdniaoDTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.LogisticsInformationVO;
import com.gs.lshly.rpc.api.common.IExpressSurviceRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
 * @author zst
 * @since 2021-1-29
*/
@DubboService
public class ExpressSurviceRpc implements IExpressSurviceRpc {
    @Autowired
    private IPCExpressSurviceService iPCExpressSurviceService;

    @Override
    public LogisticsInformationVO.ListVO orderLogisticsInformation(StockKdniaoDTO.TradeTailDTO dto) {
        return iPCExpressSurviceService.orderLogisticsInformation(dto);
    }

    @Override
    public LogisticsInformationVO.ListVO onLineMonitoring(StockKdniaoDTO.TradeTailDTO dto) {
        return iPCExpressSurviceService.onLineMonitoring(dto);
    }
}