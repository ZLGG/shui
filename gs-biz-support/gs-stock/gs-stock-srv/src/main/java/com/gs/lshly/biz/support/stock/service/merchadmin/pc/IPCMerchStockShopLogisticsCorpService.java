package com.gs.lshly.biz.support.stock.service.merchadmin.pc;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.stock.dto.PCMerchStockShopLogisticsCorpDTO;
import com.gs.lshly.common.struct.merchadmin.pc.stock.qto.PCMerchStockShopLogisticsCorpQTO;
import com.gs.lshly.common.struct.merchadmin.pc.stock.vo.PCMerchStockShopLogisticsCorpVO;

import java.util.List;

public interface IPCMerchStockShopLogisticsCorpService {

    List<PCMerchStockShopLogisticsCorpVO.ListVO> pageData(PCMerchStockShopLogisticsCorpQTO.QTO qto);

    List<PCMerchStockShopLogisticsCorpVO.ListVO> enableList(BaseDTO dto);

    void enable(PCMerchStockShopLogisticsCorpDTO.IdDTO idDTO);

    void disable(PCMerchStockShopLogisticsCorpDTO.IdDTO idDTO);
}