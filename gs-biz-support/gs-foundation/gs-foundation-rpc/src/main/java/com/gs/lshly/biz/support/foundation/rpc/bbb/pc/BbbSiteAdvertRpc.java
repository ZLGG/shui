package com.gs.lshly.biz.support.foundation.rpc.bbb.pc;

import com.gs.lshly.biz.support.foundation.service.bbb.pc.IBbbSiteAdvertService;
import com.gs.lshly.biz.support.foundation.service.bbc.IBbcSiteAdvertService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteAdvertQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteAdvertVO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteAdvertQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteAdvertVO;
import com.gs.lshly.rpc.api.bbb.pc.foundation.IBbbSiteAdvertRpc;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSiteAdvertRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author hyy
* @since 2020-11-03
*/
@DubboService
public class BbbSiteAdvertRpc implements IBbbSiteAdvertRpc {

    @Autowired
    private IBbbSiteAdvertService bbbSiteAdvertService;


    @Override
    public PageData<BbbSiteAdvertVO.SubjectListVO> pageList(BbbSiteAdvertQTO.QTO qto) {
        return bbbSiteAdvertService.pageList(qto);
    }
}