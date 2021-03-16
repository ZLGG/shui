package com.gs.lshly.biz.support.foundation.complex;

import com.gs.lshly.common.struct.bbc.foundation.vo.BbcDataArticleCategoryVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteFloorVO;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ArticleCategoryComlex {


    private BbcDataArticleCategoryVO.ListVO category;

    private Map<String,BbcDataArticleCategoryVO.CategoryChildVO> categoryChildMap = new HashMap<>();


}
