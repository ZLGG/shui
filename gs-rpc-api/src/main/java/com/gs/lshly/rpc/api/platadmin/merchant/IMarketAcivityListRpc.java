package com.gs.lshly.rpc.api.platadmin.merchant;

import com.gs.lshly.common.struct.platadmin.trade.dto.MarketPtActivityDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.MarketPtActivityQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtActivityVO;

import java.util.List;

public interface IMarketAcivityListRpc {
    List<MarketPtActivityVO.ListVO> list();
}
