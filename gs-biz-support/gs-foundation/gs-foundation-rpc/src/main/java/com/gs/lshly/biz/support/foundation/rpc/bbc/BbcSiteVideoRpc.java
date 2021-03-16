package com.gs.lshly.biz.support.foundation.rpc.bbc;
import com.gs.lshly.biz.support.foundation.service.bbc.IBbcSiteVideoService;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteVideoQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteVideoVO;
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
public class BbcSiteVideoRpc implements IBbcSiteVideoRpc{

    @Autowired
    private IBbcSiteVideoService  bbcSiteVideoService;

    @Override
    public List<BbcSiteVideoVO.ListVO> list(BbcSiteVideoQTO.QTO qto){
        return bbcSiteVideoService.list(qto);
    }

    @Override
    public BbcSiteVideoVO.ListVO video(BbcSiteVideoQTO.QTO qto) {
        return bbcSiteVideoService.video(qto);
    }
}