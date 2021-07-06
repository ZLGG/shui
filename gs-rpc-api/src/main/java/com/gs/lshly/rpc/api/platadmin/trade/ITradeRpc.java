package com.gs.lshly.rpc.api.platadmin.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeGoodsVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeListVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeVO;

/**
 * @author oy
 * @since 2020-11-16
 */
public interface ITradeRpc {

    PageData<TradeListVO.tradeVO> tradeListPageData(TradeQTO.TradeList qto);

    TradeListVO.tradeVO detail(TradeDTO.IdDTO dto);

    ExportDataDTO export(TradeQTO.IdListQTO qo) throws Exception;

    TradeVO.TradeInfoVO findById(TradeGoodsVO.ListVO listVO);

    Boolean platformCancel(TradeDTO.PlatformCancelDTO dto);
}