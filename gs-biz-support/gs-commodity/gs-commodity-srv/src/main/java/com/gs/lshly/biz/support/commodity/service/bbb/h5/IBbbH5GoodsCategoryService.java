package com.gs.lshly.biz.support.commodity.service.bbb.h5;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsCategoryVO;

import java.util.List;

public interface IBbbH5GoodsCategoryService {

    /**
     * 查询所有2B商城分类列表
     * @param dto
     * @return
     */
    List<BbbH5GoodsCategoryVO.CategoryTreeVO> listGoodsCategory(BaseDTO dto);


}