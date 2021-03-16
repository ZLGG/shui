package com.gs.lshly.biz.support.merchant.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMerchantArticleDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMerchantArticleQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantArticleVO;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchMerchantArticleRpc;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchMerchantArticleService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author hyy
* @since 2020-11-07
*/
@DubboService
public class PCMerchMerchantArticleRpc implements IPCMerchMerchantArticleRpc{
    @Autowired
    private IPCMerchMerchantArticleService  pCMerchMerchantArticleService;

    @Override
    public PageData<PCMerchMerchantArticleVO.ListVO> pageData(PCMerchMerchantArticleQTO.QTO qto){
        return pCMerchMerchantArticleService.pageData(qto);
    }

    @Override
    public void addMerchantArticle(PCMerchMerchantArticleDTO.ETO eto){
        pCMerchMerchantArticleService.addMerchantArticle(eto);
    }

    @Override
    public void deleteMerchantArticle(PCMerchMerchantArticleDTO.IdDTO dto){
        pCMerchMerchantArticleService.deleteMerchantArticle(dto);
    }


    @Override
    public void editMerchantArticle(PCMerchMerchantArticleDTO.ETO eto){
        pCMerchMerchantArticleService.editMerchantArticle(eto);
    }

    @Override
    public PCMerchMerchantArticleVO.DetailVO detailMerchantArticle(PCMerchMerchantArticleDTO.IdDTO dto){
        return  pCMerchMerchantArticleService.detailMerchantArticle(dto);
    }

    @Override
    public PCMerchMerchantArticleVO.LinkListVO listLinkUrl(PCMerchMerchantArticleDTO.IdDTO dto) {

        return pCMerchMerchantArticleService.listLinkUrl(dto);
    }

}