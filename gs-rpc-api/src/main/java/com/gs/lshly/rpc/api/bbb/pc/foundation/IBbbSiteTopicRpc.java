package com.gs.lshly.rpc.api.bbb.pc.foundation;
import java.util.List;

import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteTopicQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteTopicVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;

/**
*
* @author xxfc
* @since 2020-11-02
*/
public interface IBbbSiteTopicRpc {

    List<BbbSiteTopicVO.ListVO> list();
    
    
    
    List<GoodsInfoVO.DetailVO> listHotsearch(BbbSiteTopicQTO.QTO qto);
}