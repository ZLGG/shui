package com.gs.lshly.rpc.api.platadmin.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradePayOfflineDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradePayOfflineQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradePayOfflineVO;


/**
 *
 * @author tangxu
 * @version 1.0
 * @date 2020/12/10 17:55
 */
public interface ITradePayOfflineRpc {

    PageData<TradePayOfflineVO.ListVO> pageData(TradePayOfflineQTO.QTO qto);

    TradePayOfflineVO.DetailVO showDetail(String id);

    Boolean verify(TradePayOfflineDTO.DTO dto);

    void updateByRefuse(TradePayOfflineDTO.RefuseDTO dto);

    ExportDataDTO export(TradePayOfflineQTO.IdListQTO qo) throws Exception;
}
