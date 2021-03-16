package com.gs.lshly.biz.support.commodity.mapper.view;

import com.gs.lshly.biz.support.commodity.entity.GoodsCategory;
import lombok.Data;

/**
 * @Author Starry
 * @Date 11:38 2020/12/4
 */
@Data
public class GoodsCategoryView extends GoodsCategory {
    private String level3IdList;
}
