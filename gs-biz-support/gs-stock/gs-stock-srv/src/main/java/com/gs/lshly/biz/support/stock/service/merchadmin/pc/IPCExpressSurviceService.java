package com.gs.lshly.biz.support.stock.service.merchadmin.pc;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.stock.vo.PCMerchStockVO;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockKdniaoDTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.LogisticsInformationVO;

import java.util.List;

public interface IPCExpressSurviceService {

    LogisticsInformationVO.ListVO orderLogisticsInformation(StockKdniaoDTO.TradeTailDTO dto);

    LogisticsInformationVO.ListVO queryUPS(StockKdniaoDTO.TradeTailDTO dto);

}