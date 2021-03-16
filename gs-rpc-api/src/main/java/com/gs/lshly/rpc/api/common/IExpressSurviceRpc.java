package com.gs.lshly.rpc.api.common;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockKdniaoDTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.LogisticsInformationVO;

/**
*
 * @author zst
 * @since 2021-1-29
*/
public interface IExpressSurviceRpc {

    LogisticsInformationVO.ListVO orderLogisticsInformation(StockKdniaoDTO.TradeTailDTO dto);

    LogisticsInformationVO.ListVO queryUPS(StockKdniaoDTO.TradeTailDTO dto);

}