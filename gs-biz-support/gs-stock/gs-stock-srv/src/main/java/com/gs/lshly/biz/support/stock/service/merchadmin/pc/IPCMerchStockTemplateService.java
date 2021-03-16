package com.gs.lshly.biz.support.stock.service.merchadmin.pc;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateDTO;
import com.gs.lshly.common.struct.merchadmin.pc.stock.qto.PCMerchStockTemplateQTO;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateVO;

import java.util.List;

public interface IPCMerchStockTemplateService {


    void editStockTemplate(CommonStockTemplateDTO.ETO eto);

    CommonStockTemplateVO.ListDetailVO stockTemplateDetail(CommonStockTemplateDTO.IdDTO dto);


    List<CommonStockTemplateVO.ListDetailVO> detailList(PCMerchStockTemplateQTO.QTO qto);

    void delete(CommonStockTemplateDTO.IdDTO idDTO);

    List<CommonStockTemplateVO.IdNameVO> idNames(BaseDTO dto);


    //--------------------内部服务--------------------


    CommonStockTemplateVO.IdNameVO getIdNameVo(String shopId,String templateName);

    int  checkTemplate(String shopId,String templateName);
}