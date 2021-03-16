package com.gs.lshly.biz.support.foundation.service.fy;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.fy.fundation.vo.FyCustomerServiceVO;

public interface IFyCustomerServiceService {

    FyCustomerServiceVO.ServiceVO getService(BaseDTO dto);

}