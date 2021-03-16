package com.gs.lshly.biz.support.stock.rpc.merchadmin.pc;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateDTO;
import com.gs.lshly.common.struct.merchadmin.pc.stock.qto.PCMerchStockTemplateQTO;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateVO;
import com.gs.lshly.rpc.api.merchadmin.pc.stock.IPCMerchStockTemplateRpc;
import com.gs.lshly.biz.support.stock.service.merchadmin.pc.IPCMerchStockTemplateService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author zzg
* @since 2020-10-24
*/
@DubboService
public class PCMerchStockTemplateRpc implements IPCMerchStockTemplateRpc{
    @Autowired
    private IPCMerchStockTemplateService  pCMerchStockTemplateService;

    @Override
    public List<CommonStockTemplateVO.IdNameVO> idNames(BaseDTO dto) {
        return pCMerchStockTemplateService.idNames(dto);
    }

    @Override
    public CommonStockTemplateVO.IdNameVO getIdNameVo(String shopId, String templateName) {
        return pCMerchStockTemplateService.getIdNameVo(shopId, templateName);
    }

    @Override
    public int checkTemplate(String shopId, String templateName) {
        return pCMerchStockTemplateService.checkTemplate(shopId, templateName);
    }

    @Override
    public void editStockTemplate(CommonStockTemplateDTO.ETO eto){
        if (StringUtils.isBlank(eto.getTemplateName())) {
            throw new BusinessException("模板名称不能为空");
        }
        pCMerchStockTemplateService.editStockTemplate(eto);
    }

    @Override
    public CommonStockTemplateVO.ListDetailVO detailStockTemplate(CommonStockTemplateDTO.IdDTO dto){
        return  pCMerchStockTemplateService.stockTemplateDetail(dto);
    }

    @Override
    public void delete(CommonStockTemplateDTO.IdDTO idDTO) {
        pCMerchStockTemplateService.delete(idDTO);
    }

    @Override
    public List<CommonStockTemplateVO.ListDetailVO> detailList(PCMerchStockTemplateQTO.QTO qto) {
        return pCMerchStockTemplateService.detailList(qto);
    }


}