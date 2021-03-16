package com.gs.lshly.rpc.api.common;

import com.gs.lshly.common.struct.common.CommonLogisticsCompanyVO;
import java.util.List;

public interface ICommonLogisticsCompanyRpc {

    /**
     * 店铺支持的物流公司
     */
    List<CommonLogisticsCompanyVO.DetailVO> listShopLogisticsCompany(String shopId);

    /**
     * 根据物流公司ID返回物流公司信息
     */
    CommonLogisticsCompanyVO.DetailVO getLogisticsCompany(String id);


    CommonLogisticsCompanyVO.DetailVO getLogisticsName(String logisticsName);
}
