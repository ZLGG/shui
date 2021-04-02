package com.gs.lshly.biz.support.trade.service.platadmin;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradePayOfflineDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradePayOfflineQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradePayOfflineVO;

import java.util.List;

public interface ITradePayOfflineService {

    PageData<TradePayOfflineVO.ListVO> pageData(TradePayOfflineQTO.QTO qto);

    TradePayOfflineVO.DetailVO showDetail(String id);

    Boolean verify(TradePayOfflineDTO.DTO dto);

    void updateByRefuse(TradePayOfflineDTO.RefuseDTO eto);

    /**
     * 导出线下审核数据
     * @param qto
     * @return
     */
    List<TradePayOfflineVO.ListVOExport> exportPayOfficeData(TradePayOfflineQTO.IdListQTO qto);
}