package com.gs.lshly.biz.support.foundation.rpc.bbb.h5;

import com.gs.lshly.biz.support.foundation.service.bbb.h5.IBbbH5SiteNavigationService;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5SiteNavigationQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteNavigationVO;
import com.gs.lshly.rpc.api.bbb.h5.foundation.IBbbH5SiteNavigationRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
*
* @author hyy
* @since 2020-11-04
*/
@DubboService
public class BbbH5SiteNavigationRpc implements IBbbH5SiteNavigationRpc {
    
    @Autowired
    private IBbbH5SiteNavigationService bbcSiteNavigationService;

    @Override
    public List<BbbH5SiteNavigationVO.ListVO> list(BbbH5SiteNavigationQTO.QTO qto){
        return bbcSiteNavigationService.list(qto);
    }

}