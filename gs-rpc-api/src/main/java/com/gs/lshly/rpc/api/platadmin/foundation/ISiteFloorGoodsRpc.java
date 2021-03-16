package com.gs.lshly.rpc.api.platadmin.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteFloorGoodsDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteFloorGoodsQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteFloorGoodsVO;

import java.util.List;

/**
*
* @author 大飞船
* @since 2020-09-29
*/
public interface ISiteFloorGoodsRpc {

    PageData<SiteFloorGoodsVO.ListVO> pageData(SiteFloorGoodsQTO.QTO qto);

    void addSiteFloorGoods(SiteFloorGoodsDTO.ADTO eto);

    void deleteSiteFloorGoods(SiteFloorGoodsDTO.IdDTO dto);

}