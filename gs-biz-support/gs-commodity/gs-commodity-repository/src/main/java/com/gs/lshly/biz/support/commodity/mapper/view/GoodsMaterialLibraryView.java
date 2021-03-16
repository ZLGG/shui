package com.gs.lshly.biz.support.commodity.mapper.view;

import com.gs.lshly.biz.support.commodity.entity.GoodsMaterialLibrary;
import lombok.Data;

/**
 * @Author Starry
 * @Date 17:54 2020/12/10
 */
@Data
public class GoodsMaterialLibraryView extends GoodsMaterialLibrary {

    private String gsCategoryName;

    private String brandName;
}
