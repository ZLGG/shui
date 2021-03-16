package com.gs.lshly.rpc.api.merchadmin.pc.stock;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateDTO;
import com.gs.lshly.common.struct.merchadmin.pc.stock.qto.PCMerchStockTemplateQTO;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateVO;

import java.util.List;

/**
*
* @author lxus
* @since 2020-10-24
*/
public interface IPCMerchStockTemplateRpc {


    List<CommonStockTemplateVO.ListDetailVO> detailList(PCMerchStockTemplateQTO.QTO qto);

    void editStockTemplate(CommonStockTemplateDTO.ETO eto);

    CommonStockTemplateVO.ListDetailVO detailStockTemplate(CommonStockTemplateDTO.IdDTO dto);

    void delete(CommonStockTemplateDTO.IdDTO idDTO);

    List<CommonStockTemplateVO.IdNameVO> idNames(BaseDTO dto);

    //--------------------内部服务--------------------


    CommonStockTemplateVO.IdNameVO getIdNameVo(String shopId,String templateName);

    int  checkTemplate(String shopId,String templateName);
}