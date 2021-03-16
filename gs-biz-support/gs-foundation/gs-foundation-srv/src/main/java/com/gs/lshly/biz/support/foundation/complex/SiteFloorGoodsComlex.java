package com.gs.lshly.biz.support.foundation.complex;

import com.gs.lshly.biz.support.foundation.entity.SiteFloorGoods;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteFloorVO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SiteFloorGoodsComlex {


    /**
     * 楼层
     */
    private BbcSiteFloorVO.ListVO listVO;

    /**
     * 楼层的商品ID列表
     */
    private List<String> goodsIdList = new ArrayList<>();

    /**
     * 楼层的商品ID列表
     */
    private List<SiteFloorGoods> floorGoodsList = new ArrayList<>();

}
