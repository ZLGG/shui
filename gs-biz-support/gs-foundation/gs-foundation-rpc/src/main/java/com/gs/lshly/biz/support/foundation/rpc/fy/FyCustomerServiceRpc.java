package com.gs.lshly.biz.support.foundation.rpc.fy;

import com.gs.lshly.biz.support.foundation.service.fy.IFyCustomerServiceService;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.fy.fundation.vo.FyCustomerServiceVO;
import com.gs.lshly.rpc.api.fy.IFyCustomerServiceRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author 陈奇
* @since 2020-10-12
*/
@DubboService
public class FyCustomerServiceRpc implements IFyCustomerServiceRpc {

    @Autowired
    private IFyCustomerServiceService fyCustomerServiceService;

    @Override
    public FyCustomerServiceVO.ServiceVO getService(BaseDTO dto) {
        return fyCustomerServiceService.getService(dto);
    }
}