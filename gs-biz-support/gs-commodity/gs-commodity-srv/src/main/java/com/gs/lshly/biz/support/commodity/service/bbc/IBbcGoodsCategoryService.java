package com.gs.lshly.biz.support.commodity.service.bbc;
import java.util.List;

import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsCategoryQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsCategoryVO;

public interface IBbcGoodsCategoryService {

    /**
     * 查询所有2C商城分类列表
     * @return
     */
    List<BbcGoodsCategoryVO.CategoryTreeVO> listGoodsCategory();

}