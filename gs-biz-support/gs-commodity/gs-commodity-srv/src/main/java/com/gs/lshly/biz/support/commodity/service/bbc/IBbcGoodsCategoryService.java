package com.gs.lshly.biz.support.commodity.service.bbc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.commodity.entity.GoodsInfo;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsCategoryQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsCategoryVO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsInfoQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsBrandVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;

import java.util.List;

public interface IBbcGoodsCategoryService {
    List<BbcGoodsCategoryVO.CategoryTreeVO> listGoodsCategory();
    /**
     * 查询所有2C商城分类列表
     *
     * @return
     */
    List<BbcGoodsCategoryVO.CategoryTreeVO> goodsCategoryTree(BbcGoodsCategoryQTO.ListQTO listQTO);

    PageData<GoodsBrandVO.ListVO> brandList(GoodsInfoQTO.CategoryIdQTO categoryIdQTO);

    PageData<GoodsInfoVO.ListVO> goodsList(GoodsInfoQTO.CategoryIdQTO categoryIdQTO);
}