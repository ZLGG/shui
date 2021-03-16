package com.gs.lshly.biz.support.foundation.rpc.bbb.h5;

import com.gs.lshly.biz.support.foundation.service.bbb.h5.IBbbH5SiteVideoService;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5SiteVideoQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteVideoVO;
import com.gs.lshly.rpc.api.bbb.h5.foundation.IBbbH5SiteVideoRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
*
* @author hyy
* @since 2020-11-04
*/
@DubboService
public class BbbH5SiteVideoRpc implements IBbbH5SiteVideoRpc {
    
    @Autowired
    private IBbbH5SiteVideoService bbbH5SiteVideoService;

    @Override
    public List<BbbH5SiteVideoVO.ListVO> video(BbbH5SiteVideoQTO.QTO qto){
        return bbbH5SiteVideoService.video(qto);
    }
}