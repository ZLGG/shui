package com.gs.lshly.biz.support.stock.rpc.common;

import com.gs.lshly.biz.support.stock.service.common.ICommonStockTemplateService;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateVO;
import com.gs.lshly.rpc.api.common.ICommonStockTemplateRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author Starry
 * @Date 17:07 2021/3/17
 */
@DubboService
public class CommonStockTemplateRpc implements ICommonStockTemplateRpc {

    @Autowired
    private ICommonStockTemplateService commonStockTemplateService;

    @Override
    public CommonStockTemplateVO.IdNameVO innerIdNameVO(String templateId) {
        return commonStockTemplateService.innerIdNameVO(templateId);
    }
}
