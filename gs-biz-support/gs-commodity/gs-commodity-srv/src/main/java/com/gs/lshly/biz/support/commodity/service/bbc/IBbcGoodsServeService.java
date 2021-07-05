package com.gs.lshly.biz.support.commodity.service.bbc;

import java.util.List;

import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsServeQTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsServeQTO.GoodsInfoQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsServeVO;

/**
 * @author hanly
 */
public interface IBbcGoodsServeService {

    List<BbcGoodsServeVO.ListVO> getGoodsServeDetail(BbcGoodsServeQTO.GoodsInfoQTO qto);

    List<String> getServeIdByGoodsId(GoodsInfoQTO dto);
}
