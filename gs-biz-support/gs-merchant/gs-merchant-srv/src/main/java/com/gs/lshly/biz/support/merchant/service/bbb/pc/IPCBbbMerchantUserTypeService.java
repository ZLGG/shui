package com.gs.lshly.biz.support.merchant.service.bbb.pc;

import com.gs.lshly.common.struct.bbb.pc.merchant.vo.PCBbbMerchantUserTypeVO;

public interface IPCBbbMerchantUserTypeService {

    PCBbbMerchantUserTypeVO.DetailsVO details(String id);

}