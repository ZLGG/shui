package com.gs.lshly.biz.support.stock.service.common;

import com.gs.lshly.common.struct.common.CommonLogisticsCompanyVO;

import java.util.List;

public interface ICommonLogisticsCompanyService {

    List<CommonLogisticsCompanyVO.DetailVO> listShopLogisticsCompany(String shopId);


    CommonLogisticsCompanyVO.DetailVO getLogisticsCompany(String id);

    CommonLogisticsCompanyVO.DetailVO getLogisticsName(String logisticsName);
}