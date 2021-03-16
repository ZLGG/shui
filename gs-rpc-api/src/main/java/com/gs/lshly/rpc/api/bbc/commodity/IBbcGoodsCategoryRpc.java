package com.gs.lshly.rpc.api.bbc.commodity;
import java.util.List;

import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsCategoryQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsCategoryVO;

/**
*
* @author Starry
* @since 2020-10-23
*/
public interface IBbcGoodsCategoryRpc {

    /**
     * 查询所有分类列表
     * @return
     */
    List<BbcGoodsCategoryVO.CategoryTreeVO> listGoodsCategory();
    
    /**
     * 查询2B商城商品分类列表以及菜单列表
     * @return
     */
    BbcGoodsCategoryVO.CategoryMenuVO getCategoryMenuVO(BbcGoodsCategoryQTO.QTO qto);


}