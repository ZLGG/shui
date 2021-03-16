package com.gs.lshly.biz.support.trade.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeCancelDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeCancelQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeCancelVO;

import java.util.List;

public interface ITradeCancelService {

    PageData<TradeCancelVO.ListVO> pageData(TradeCancelQTO.QTO qto);

    TradeCancelVO.DetailVO detailTradeCancel(TradeCancelDTO.IdDTO dto);

    List<TradeCancelVO.ListVOExport> export(TradeCancelQTO.IdListQTO qo);
}