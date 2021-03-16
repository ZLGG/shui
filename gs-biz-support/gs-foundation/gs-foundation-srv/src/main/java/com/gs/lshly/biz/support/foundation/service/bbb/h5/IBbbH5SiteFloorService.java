package com.gs.lshly.biz.support.foundation.service.bbb.h5;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5SiteFloorQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteFloorVO;

import java.util.List;

public interface IBbbH5SiteFloorService {

    List<BbbH5SiteFloorVO.ListVO> list(BbbH5SiteFloorQTO.QTO qto);

    PageData<BbbH5SiteFloorVO.GoodsListVO> goodsList(BbbH5SiteFloorQTO.GoodsListQTO qto);

}