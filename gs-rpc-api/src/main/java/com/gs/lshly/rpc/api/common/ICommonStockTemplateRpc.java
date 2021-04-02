package com.gs.lshly.rpc.api.common;

import com.gs.lshly.common.struct.common.stock.CommonStockTemplateVO;

/**
 * @Author Starry
 * @Date 17:00 2021/3/17
 */
public interface ICommonStockTemplateRpc {

    /**
     * 通过运费模板id获取运费模板名称
     * @param templateId
     * @return
     */
    CommonStockTemplateVO.IdNameVO innerIdNameVO(String templateId);
}
