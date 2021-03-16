package com.gs.lshly.biz.support.stock.rpc.common;

import com.gs.lshly.biz.support.stock.service.common.ICommonLogisticsCompanyService;
import com.gs.lshly.common.struct.common.CommonLogisticsCompanyVO;
import com.gs.lshly.rpc.api.common.ICommonLogisticsCompanyRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-30
*/
@DubboService
public class CommonLogisticsCompanyRpc implements ICommonLogisticsCompanyRpc {

    @Autowired
    private ICommonLogisticsCompanyService logisticsCompanyService;

    @Override
    public List<CommonLogisticsCompanyVO.DetailVO> listShopLogisticsCompany(String shopId) {
        return logisticsCompanyService.listShopLogisticsCompany(shopId);
    }

    @Override
    public CommonLogisticsCompanyVO.DetailVO getLogisticsCompany(String id) {
        return logisticsCompanyService.getLogisticsCompany(id);
    }

    @Override
    public CommonLogisticsCompanyVO.DetailVO getLogisticsName(String logisticsName) {
        return logisticsCompanyService.getLogisticsName(logisticsName);
    }

}