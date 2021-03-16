package com.gs.lshly.biz.support.foundation.service.bbc;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteFloorQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteFloorVO;

import java.util.List;

public interface IBbcSiteFloorService {

    List<BbcSiteFloorVO.ListVO> list(BbcSiteFloorQTO.QTO qto);

    List<BbcSiteFloorVO.List2VO> list2(BbcSiteFloorQTO.QTO qto);

    List<BbcGoodsInfoVO.HomeAndShopInnerServiceVO> ListGoodsMax(BbcSiteFloorQTO.GoodsMaxQTO qto);

    PageData<BbcGoodsInfoVO.HomeAndShopInnerServiceVO> goodsMore(BbcSiteFloorQTO.GoodsMoreQTO qto);

}