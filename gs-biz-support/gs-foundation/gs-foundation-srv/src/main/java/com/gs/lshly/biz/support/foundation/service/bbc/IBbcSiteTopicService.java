package com.gs.lshly.biz.support.foundation.service.bbc;
import java.util.List;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteTopicQTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteTopicQTO.EnjoyQTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteTopicQTO.SearchmoreQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteTopicVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteTopicVO.CategoryListVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO.DetailVO;

public interface IBbcSiteTopicService {

    List<BbcSiteTopicVO.CategoryListVO> list(BbcSiteTopicQTO.QTO qto);
    
    PageData<DetailVO> pageEnjoy(EnjoyQTO qto);
    
    List<CategoryListVO> listPointHome(BbcSiteTopicQTO.QTO qto);
    
    CategoryListVO topicGoods(String topicId);
    
    /**
     * 查询当前分页下面的更多产品
     * @param qto
     * @return
     */
    BbcSiteTopicVO.GoodsVO pageMore(SearchmoreQTO qto);
}