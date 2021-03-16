package com.gs.lshly.biz.support.foundation.rpc.bbb.pc;

import com.gs.lshly.biz.support.foundation.service.bbb.pc.IBbbSiteVideoService;
import com.gs.lshly.biz.support.foundation.service.bbc.IBbcSiteVideoService;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteVideoQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteVideoVO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteVideoQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteVideoVO;
import com.gs.lshly.rpc.api.bbb.pc.foundation.IBbbSiteVideoRpc;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSiteVideoRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author hyy
* @since 2020-11-04
*/
@DubboService
public class BbbSiteVideoRpc implements IBbbSiteVideoRpc {

    @Autowired
    private IBbbSiteVideoService bbbSiteVideoService;

    @Override
    public List<BbbSiteVideoVO.ListVO> list(BbbSiteVideoQTO.QTO qto){
        return bbbSiteVideoService.list(qto);
    }
}