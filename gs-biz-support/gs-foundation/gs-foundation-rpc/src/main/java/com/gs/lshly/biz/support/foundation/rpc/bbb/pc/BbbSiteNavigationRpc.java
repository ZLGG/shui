package com.gs.lshly.biz.support.foundation.rpc.bbb.pc;
import com.gs.lshly.biz.support.foundation.service.bbb.pc.IBbbSiteNavigationService;
import com.gs.lshly.common.struct.bbb.pc.commodity.qto.PCBbbGoodsCategoryQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteNavigationQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteNavigationVO;
import com.gs.lshly.rpc.api.bbb.pc.foundation.IBbbSiteNavigationRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author 陈奇
* @since 2020-10-14
*/
@DubboService
public class BbbSiteNavigationRpc implements IBbbSiteNavigationRpc{

    @Autowired
    private IBbbSiteNavigationService bbbSiteNavigationService;

    @Override
    public BbbSiteNavigationVO.HomeVO homeDetail(PCBbbGoodsCategoryQTO.QTO qto) {
        return bbbSiteNavigationService.homeDetail(qto);
    }

    @Override
    public BbbSiteNavigationVO.DetailVO subjectDetail(BbbSiteNavigationQTO.BQTO qto){
        return bbbSiteNavigationService.subjectDetail(qto);
    }


}