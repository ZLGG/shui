package com.gs.lshly.biz.support.commodity.service.bbc;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsCategoryVO;

import java.util.List;

public interface IBbcGoodsCategoryService {

    /**
     * 查询所有2C商城分类列表
     * @return
     */
    List<BbcGoodsCategoryVO.CategoryTreeVO> listGoodsCategory();

}