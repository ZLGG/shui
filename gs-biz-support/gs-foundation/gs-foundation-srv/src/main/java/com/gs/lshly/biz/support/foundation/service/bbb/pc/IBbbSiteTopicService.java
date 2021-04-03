package com.gs.lshly.biz.support.foundation.service.bbb.pc;
import java.util.List;

import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteTopicQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteTopicVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;

public interface IBbbSiteTopicService {

    List<BbbSiteTopicVO.ListVO> list();
    
    
    /**
     * 热门查询
     * @return
     */
    List<GoodsInfoVO.DetailVO> listHotsearch(BbbSiteTopicQTO.QTO qto);

}