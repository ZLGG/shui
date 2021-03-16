package com.gs.lshly.biz.support.foundation.service.platadmin;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteFloorGoodsDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteFloorGoodsQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteFloorGoodsVO;

import java.util.List;

public interface ISiteFloorGoodsService {

    PageData<SiteFloorGoodsVO.ListVO> pageData(SiteFloorGoodsQTO.QTO qto);

    void addSiteFloorGoods(SiteFloorGoodsDTO.ADTO eto);

    void deleteSiteFloorGoods(SiteFloorGoodsDTO.IdDTO dto);

}