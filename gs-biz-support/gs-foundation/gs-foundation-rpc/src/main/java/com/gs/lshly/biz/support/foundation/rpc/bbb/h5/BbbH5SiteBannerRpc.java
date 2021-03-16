package com.gs.lshly.biz.support.foundation.rpc.bbb.h5;

import com.gs.lshly.biz.support.foundation.service.bbb.h5.IBbbH5SiteBannerService;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5SiteBannerQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteBannerVO;
import com.gs.lshly.rpc.api.bbb.h5.foundation.IBbbH5SiteBannerRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
*
* @author xxfc
* @since 2020-11-02
*/
@DubboService
public class BbbH5SiteBannerRpc implements IBbbH5SiteBannerRpc {

    @Autowired
    private IBbbH5SiteBannerService bbbSiteBannerService;

    @Override
    public List<BbbH5SiteBannerVO.ListVO> list(BbbH5SiteBannerQTO.QTO qto){
        return bbbSiteBannerService.list(qto);
    }



}