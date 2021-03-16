package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteCustomerServiceDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteCustomerServiceQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteCustomerServiceVO;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteCustomerServiceService;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteCustomerServiceRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author 陈奇
* @since 2020-10-12
*/
@DubboService
public class SiteCustomerServiceRpc implements ISiteCustomerServiceRpc{

    @Autowired
    private ISiteCustomerServiceService siteCustomerServiceService;


    @Override
    public void addSiteCustomerServic(SiteCustomerServiceDTO.ETO eto) {
        siteCustomerServiceService.addSiteCustomerService(eto);
    }

    @Override
    public List<SiteCustomerServiceVO.ListVO> list(SiteCustomerServiceQTO.QTO qto) {
        return siteCustomerServiceService.list(qto);
    }

    @Override
    public List<SiteCustomerServiceVO.PhoneVO> listPhone(SiteCustomerServiceQTO.QTO qto) {
        return siteCustomerServiceService.listPhone(qto);
    }

    @Override
    public void addSiteCustomerServicPhone(SiteCustomerServiceDTO.ETOPhone  eto) {
          siteCustomerServiceService.addSiteCustomerServicePhone(eto);
    }


}