package com.gs.lshly.biz.support.foundation.rpc.bbb.pc;

import com.gs.lshly.biz.support.foundation.service.bbb.pc.IBbbSiteBannerService;
import com.gs.lshly.biz.support.foundation.service.bbc.IBbcSiteBannerService;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteBannerQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteBannerVO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteBannerQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteBannerVO;
import com.gs.lshly.rpc.api.bbb.pc.foundation.IBbbSiteBannerRpc;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSiteBannerRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-11-02
*/
@DubboService
public class BbbSiteBannerRpc implements IBbbSiteBannerRpc {

    @Autowired
    private IBbbSiteBannerService bbbSiteBannerService;

    @Override
    public List<BbbSiteBannerVO.ListVO> list(BbbSiteBannerQTO.QTO qto) {
        return bbbSiteBannerService.list(qto);
    }
}