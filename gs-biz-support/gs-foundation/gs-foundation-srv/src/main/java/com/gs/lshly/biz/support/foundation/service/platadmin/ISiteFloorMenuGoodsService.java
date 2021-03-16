package com.gs.lshly.biz.support.foundation.service.platadmin;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteFloorMenuGoodsDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteFloorMenuGoodsQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteFloorMenuGoodsVO;

import java.util.List;

public interface ISiteFloorMenuGoodsService {

    PageData<SiteFloorMenuGoodsVO.ListVO> pageData(SiteFloorMenuGoodsQTO.QTO qto);

    void addSiteFloorMenuGoods(SiteFloorMenuGoodsDTO.ADTO eto);

    void deleteSiteFloorMenuGoods(SiteFloorMenuGoodsDTO.IdDTO dto);

    void editSiteFloorMenuGoods(SiteFloorMenuGoodsDTO.ADTO eto);

    List<SiteFloorMenuGoodsVO.GoodsIdVO> selectByFloorMenuId(SiteFloorMenuGoodsQTO.FloorMenuIdQTO qto);
}