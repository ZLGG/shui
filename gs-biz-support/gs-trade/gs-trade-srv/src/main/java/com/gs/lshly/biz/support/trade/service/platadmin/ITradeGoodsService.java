package com.gs.lshly.biz.support.trade.service.platadmin;
import com.gs.lshly.biz.support.trade.entity.TradeGoods;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsInfoDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeGoodsDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeGoodsQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeGoodsVO;

import java.util.List;

public interface ITradeGoodsService {

    PageData<TradeGoodsVO.ListVO> pageData(TradeGoodsQTO.QTO qto);

    void addTradeGoods(TradeGoodsDTO.ETO eto);

    void deleteTradeGoods(TradeGoodsDTO.IdDTO dto);


    void editTradeGoods(TradeGoodsDTO.ETO eto);

    TradeGoodsVO.DetailVO detailTradeGoods(TradeGoodsDTO.IdDTO dto);

    List<TradeGoodsVO.ListVO> findListByGoodsId(GoodsInfoDTO.IdListDTO dto);
}