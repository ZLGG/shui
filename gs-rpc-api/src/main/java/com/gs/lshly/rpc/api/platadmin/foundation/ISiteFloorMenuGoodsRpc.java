package com.gs.lshly.rpc.api.platadmin.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteFloorMenuGoodsDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteFloorMenuGoodsQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteFloorMenuGoodsVO;

import java.util.List;

/**
*
* @author 大飞船
* @since 2020-09-29
*/
public interface ISiteFloorMenuGoodsRpc {

    PageData<SiteFloorMenuGoodsVO.ListVO> pageData(SiteFloorMenuGoodsQTO.QTO qto);

    void addSiteFloorMenuGoods(SiteFloorMenuGoodsDTO.ADTO eto);

    void deleteSiteFloorMenuGoods(SiteFloorMenuGoodsDTO.IdDTO dto);

    void editSiteFloorMenuGoods(SiteFloorMenuGoodsDTO.ADTO eto);

    List<SiteFloorMenuGoodsVO.GoodsIdVO> selectByFloorMenuId(SiteFloorMenuGoodsQTO.FloorMenuIdQTO qto);
}