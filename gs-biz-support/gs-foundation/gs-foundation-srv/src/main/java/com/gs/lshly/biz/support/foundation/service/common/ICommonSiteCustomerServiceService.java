package com.gs.lshly.biz.support.foundation.service.common;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.vo.CommonSiteCustomerServiceVO;

public interface ICommonSiteCustomerServiceService {

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