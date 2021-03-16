package com.gs.lshly.biz.support.foundation.rpc.common;

import com.gs.lshly.biz.support.foundation.service.common.ICommonSiteCustomerServiceService;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteCustomerServiceService;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.qto.CommonSiteCustomerServiceQTO;
import com.gs.lshly.common.struct.common.vo.CommonSiteCustomerServiceVO;
import com.gs.lshly.rpc.api.common.ICommonSiteCustomerServiceRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author 陈奇
* @since 2020-10-12
*/
@DubboService
public class CommonSiteCustomerServiceRpc implements ICommonSiteCustomerServiceRpc {

    @Autowired
    private ICommonSiteCustomerServiceService commonSiteCustomerServiceService;


    @Override
    public CommonSiteCustomerServiceVO.ServiceVO getService(BaseDTO dto) {
        return commonSiteCustomerServiceService.getService(dto);
    }

    @Override
    public String getDefaultImage(Integer imageType) {
        return commonSiteCustomerServiceService.getDefaultImage(imageType);
    }

    @Override
    public String getDataPhone(BaseDTO dto) {
        return commonSiteCustomerServiceService.getDataPhone(dto);
    }
}