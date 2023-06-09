package com.gs.lshly.rpc.api.merchadmin.pc.stock;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.stock.CommonTemplateDTO;
import com.gs.lshly.common.struct.common.stock.TemplateVO;
import com.gs.lshly.common.struct.common.qto.TemplateQTO;

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

    /**
     * 运费模板详情
     * @param id
     * @return
     */
    TemplateVO.DetailVO detail(String id);

    /**
     * 删除
     * @param id
     * @return
     */
    Boolean delete(String id);

    /**
     * 分页列表
     * @param qto
     * @return
     */
    PageData<TemplateVO.ListVO> list(TemplateQTO.QTO qto);
}
