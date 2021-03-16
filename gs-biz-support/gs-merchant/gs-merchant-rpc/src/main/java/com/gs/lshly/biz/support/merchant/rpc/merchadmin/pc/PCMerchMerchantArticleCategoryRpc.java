package com.gs.lshly.biz.support.merchant.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMerchantArticleCategoryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMerchantArticleCategoryQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantArticleCategoryVO;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchMerchantArticleCategoryRpc;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchMerchantArticleCategoryService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author hyy
* @since 2020-11-07
*/
@DubboService
public class PCMerchMerchantArticleCategoryRpc implements IPCMerchMerchantArticleCategoryRpc{
    @Autowired
    private IPCMerchMerchantArticleCategoryService  pCMerchMerchantArticleCategoryService;

    @Override
    public PageData<PCMerchMerchantArticleCategoryVO.ListVO> pageData(PCMerchMerchantArticleCategoryQTO.QTO qto){
        return pCMerchMerchantArticleCategoryService.pageData(qto);
    }

    @Override
    public void addMerchantArticleCategory(PCMerchMerchantArticleCategoryDTO.ETO eto){
        pCMerchMerchantArticleCategoryService.addMerchantArticleCategory(eto);
    }

    @Override
    public void deleteMerchantArticleCategory(PCMerchMerchantArticleCategoryDTO.IdDTO dto){
        pCMerchMerchantArticleCategoryService.deleteMerchantArticleCategory(dto);
    }

    @Override
    public void editMerchantArticleCategory(PCMerchMerchantArticleCategoryDTO.ETO eto){
        pCMerchMerchantArticleCategoryService.editMerchantArticleCategory(eto);
    }
}