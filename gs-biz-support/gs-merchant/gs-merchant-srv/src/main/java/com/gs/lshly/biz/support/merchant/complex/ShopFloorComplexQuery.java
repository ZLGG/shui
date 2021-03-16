package com.gs.lshly.biz.support.merchant.complex;

import com.gs.lshly.biz.support.merchant.entity.ShopFloorGoods;
import com.gs.lshly.common.struct.bbb.h5.merchant.vo.BbbH5ShopFloorVO;
import com.gs.lshly.common.struct.bbb.pc.pages.vo.PCBbbShopHomeVO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopFloorVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopFloorVO;
import lombok.Data;

import java.util.List;

@Data
public abstract class ShopFloorComplexQuery {

    @Data
    public static class ComplexBbcH5{

       private BbcShopFloorVO.ListVO floorVO;

        private List<ShopFloorGoods> floorGoodsItemList;

    }

    @Data
    public static class ComplexBbbH5{

        private BbbH5ShopFloorVO.ListVO floorVO;

        private List<ShopFloorGoods> floorGoodsItemList;

    }

    @Data
    public static class ComplexMerchadminH5{

        private PCMerchShopFloorVO.H5ListVO floorVO;

        private List<ShopFloorGoods> floorGoodsList;

    }

    @Data
    public static class ComplexMerchadminPc{

        private PCMerchShopFloorVO.PCListVO floorVO;

        private List<ShopFloorGoods> floorGoodsList;

    }

    @Data
    public static class ComplexB2bPc{

        private PCBbbShopHomeVO.FloorVO floorVO;

        private List<ShopFloorGoods> floorGoodsList;

    }
}
