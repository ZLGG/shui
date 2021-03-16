package com.gs.lshly.biz.support.foundation.rpc.bbc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.foundation.dto.BbcSiteNavigationDTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteNavigationQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteNavigationVO;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSiteNavigationRpc;
import com.gs.lshly.biz.support.foundation.service.bbc.IBbcSiteNavigationService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author hyy
* @since 2020-11-04
*/
@DubboService
public class BbcSiteNavigationRpc implements IBbcSiteNavigationRpc{
    @Autowired
    private IBbcSiteNavigationService  bbcSiteNavigationService;

    @Override
    public List<BbcSiteNavigationVO.ListVO> list(BbcSiteNavigationQTO.QTO qto){
        return bbcSiteNavigationService.list(qto);
    }

}