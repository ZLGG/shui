package com.gs.lshly.rpc.api.bbc.commodity;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsCategoryDTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsCategoryQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsCategoryVO;

import java.util.List;

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


}