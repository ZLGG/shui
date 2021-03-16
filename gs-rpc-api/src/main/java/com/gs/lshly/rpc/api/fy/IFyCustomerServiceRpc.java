package com.gs.lshly.rpc.api.fy;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.fy.fundation.vo.FyCustomerServiceVO;

/**
*
* @author 陈奇
* @since 2020-10-12
*/
public interface IFyCustomerServiceRpc {

    FyCustomerServiceVO.ServiceVO getService(BaseDTO dto);

}