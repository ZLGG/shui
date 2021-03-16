package com.gs.lshly.rpc.api.common;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.vo.CommonSiteCustomerServiceVO;

/**
*
* @author 陈奇
* @since 2020-10-12
*/
public interface ICommonSiteCustomerServiceRpc {

    CommonSiteCustomerServiceVO.ServiceVO getService(BaseDTO dto);

    /**
     * 获取默认图片
     * @param imageType
     * @return
     */
    String getDefaultImage(Integer imageType);

    /**
     * 获取运营商手机号码
     * @param dto
     * @return
     */
    String getDataPhone(BaseDTO dto);

}