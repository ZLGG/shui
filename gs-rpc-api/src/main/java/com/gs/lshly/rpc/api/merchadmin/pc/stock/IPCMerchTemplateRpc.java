package com.gs.lshly.rpc.api.merchadmin.pc.stock;

import com.gs.lshly.common.struct.common.stock.CommonTemplateDTO;

public interface IPCMerchTemplateRpc {

    /**
     * 新增运费模板
     * @param eto
     * @return
     */
    Boolean addTemplate(CommonTemplateDTO.AddETO eto);

    /**
     * 编辑运费模板
     * @param eto
     * @return
     */
    Boolean editTemplate(CommonTemplateDTO.UpdateETO eto);
}
