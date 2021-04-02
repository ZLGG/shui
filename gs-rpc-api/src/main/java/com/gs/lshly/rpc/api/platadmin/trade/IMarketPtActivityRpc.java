package com.gs.lshly.rpc.api.platadmin.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.stock.dto.MarketPtActivityGoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.MarketPtActivityDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.MarketPtActivityQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtActivityVO;

import java.util.List;

/**
*
* @author zdf
* @since 2020-11-28
*/
public interface IMarketPtActivityRpc {

    PageData<MarketPtActivityVO.ListVO> pageData(MarketPtActivityQTO.QTO qto);

    void addMarketPtActivity(MarketPtActivityDTO.ETO eto);

    void deleteMarketPtActivity(MarketPtActivityDTO.IdListDTO dto);


    void editMarketPtActivity(MarketPtActivityDTO.ETO eto);

    MarketPtActivityVO.DetailVO detailMarketPtActivity(MarketPtActivityDTO.IdDTO dto);

    List<MarketPtActivityVO.ListVO> list();

    void updateActivity(MarketPtActivityDTO.updateDTO eto);

    MarketPtActivityVO.updateDTO getActivity();
}