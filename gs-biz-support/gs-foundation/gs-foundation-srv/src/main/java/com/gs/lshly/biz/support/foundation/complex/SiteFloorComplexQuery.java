package com.gs.lshly.biz.support.foundation.complex;

import com.gs.lshly.biz.support.foundation.entity.SiteFloorGoods;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteFloorVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopFloorVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteFloorVO;
import lombok.Data;

import java.util.List;


public abstract class SiteFloorComplexQuery {


    @Data
    public static class ComplexPlatformH5{

        private SiteFloorVO.H5ListVO floorVO;

        private List<SiteFloorGoods> floorGoodsList;

    }

}
