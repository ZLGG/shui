package com.gs.lshly.rpc.api.bbb.h5.foundation;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5SiteFloorQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteFloorVO;
import java.util.List;

/**
*
* @author xxfc
* @since 2020-11-02
*/
public interface IBbbH5SiteFloorRpc {

    List<BbbH5SiteFloorVO.ListVO> list(BbbH5SiteFloorQTO.QTO qto);

    PageData<BbbH5SiteFloorVO.GoodsListVO> goodsList(BbbH5SiteFloorQTO.GoodsListQTO qto);

}