package com.gs.lshly.rpc.api.bbb.pc.foundation;
import java.util.List;

import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteTopicVO;

/**
*
* @author xxfc
* @since 2020-11-02
*/
public interface IBbbSiteTopicRpc {

    List<BbbSiteTopicVO.ListVO> list();
}