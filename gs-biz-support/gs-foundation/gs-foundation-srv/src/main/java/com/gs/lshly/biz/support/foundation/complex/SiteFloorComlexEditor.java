package com.gs.lshly.biz.support.foundation.complex;

import com.gs.lshly.biz.support.foundation.entity.SiteFloor;
import com.gs.lshly.biz.support.foundation.entity.SiteFloorGoods;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteFloorVO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteFloorDTO;
import lombok.Data;

@Data
public class SiteFloorComlexEditor {

    private SiteFloorDTO.H5SiteFloorItem floorH5Item;

    private SiteFloor siteFloor;

    //private SiteFloorDTO.PCCustomGoodsItem pcCustomGoodsItem;

}
