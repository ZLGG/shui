package com.gs.lshly.rpc.api.bbc.commodity;
import java.util.List;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsCategoryQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsCategoryVO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsInfoQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsBrandVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;

/**
*
* @author Starry
* @since 2020-10-23
*/
public interface IBbcGoodsCategoryRpc {

    /**
     * 查询分类列表
     * @return
     */
    List<BbcGoodsCategoryVO.CategoryTreeVO> listGoodsCategory();

    List<BbcGoodsCategoryVO.CategoryTreeVO> listGoodsCategory(BbcGoodsCategoryQTO.ListQTO listQTO);
    
    /**
     * 查询2B商城商品分类列表以及菜单列表
     * @return
     */
    BbcGoodsCategoryVO.CategoryMenuVO getCategoryMenuVO(BbcGoodsCategoryQTO.QTO qto);


    PageData<GoodsBrandVO.ListVO> brandList(GoodsInfoQTO.CategoryIdQTO categoryIdQTO);

    PageData<GoodsInfoVO.ListVO> goodsList(GoodsInfoQTO.CategoryIdQTO categoryIdQTO);
}