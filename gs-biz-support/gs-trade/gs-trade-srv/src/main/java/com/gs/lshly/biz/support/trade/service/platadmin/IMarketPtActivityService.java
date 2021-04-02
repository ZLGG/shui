package com.gs.lshly.biz.support.trade.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.stock.dto.MarketPtActivityGoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.MarketPtActivityDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.MarketPtActivityQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtActivityVO;

import java.util.List;

public interface IMarketPtActivityService {

    PageData<MarketPtActivityVO.ListVO> pageData(MarketPtActivityQTO.QTO qto);

    void addMarketPtActivity(MarketPtActivityDTO.ETO eto);

    void deleteMarketPtActivity(MarketPtActivityDTO.IdListDTO dto);


    void editMarketPtActivity(MarketPtActivityDTO.ETO eto);

    MarketPtActivityVO.DetailVO detailMarketPtActivity(MarketPtActivityDTO.IdDTO dto);

    List<MarketPtActivityVO.ListVO> list();

    void updateActivity(MarketPtActivityDTO.updateDTO eto);

    MarketPtActivityVO.updateDTO getActivity();


}