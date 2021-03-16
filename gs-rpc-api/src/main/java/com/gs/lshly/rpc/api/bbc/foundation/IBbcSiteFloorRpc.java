package com.gs.lshly.rpc.api.bbc.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.foundation.dto.BbcSiteFloorDTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteFloorQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteFloorVO;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-11-02
*/
public interface IBbcSiteFloorRpc {

    List<BbcSiteFloorVO.ListVO> list(BbcSiteFloorQTO.QTO qto);

    PageData<BbcGoodsInfoVO.HomeAndShopInnerServiceVO> goodsMore(BbcSiteFloorQTO.GoodsMoreQTO qto);

    List<BbcSiteFloorVO.List2VO> list2(BbcSiteFloorQTO.QTO qto);

    List<BbcGoodsInfoVO.HomeAndShopInnerServiceVO> ListGoodsMax(BbcSiteFloorQTO.GoodsMaxQTO qto);

}