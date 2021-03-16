package com.gs.lshly.rpc.api.bbb.h5.commodity;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsCategoryVO;
import java.util.List;

/**
*
* @author Starry
* @since 2020-10-23
*/
public interface IBbbH5GoodsCategoryRpc {

    /**
     * 查询所有分类列表
     * @return
     */
    List<BbbH5GoodsCategoryVO.CategoryTreeVO> listGoodsCategory(BaseDTO dto);


}