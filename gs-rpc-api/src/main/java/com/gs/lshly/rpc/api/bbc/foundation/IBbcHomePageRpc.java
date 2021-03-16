package com.gs.lshly.rpc.api.bbc.foundation;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcHomePageQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbbHomePageVO;


/**
*
* @author Starry
* @since 2020-10-23
*/
public interface IBbcHomePageRpc {

    /**
     * 查询2B商城商品分类列表以及菜单列表
     * @return
     */
    BbbHomePageVO.CategoryMenuVO getCategoryMenuVO(BbcHomePageQTO.QTO qto);
    
}