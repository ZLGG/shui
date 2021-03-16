package com.gs.lshly.biz.support.merchant.service.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMerchantArticleDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMerchantArticleQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantArticleVO;

public interface IPCMerchMerchantArticleService {

    PageData<PCMerchMerchantArticleVO.ListVO> pageData(PCMerchMerchantArticleQTO.QTO qto);

    void addMerchantArticle(PCMerchMerchantArticleDTO.ETO eto);

    void deleteMerchantArticle(PCMerchMerchantArticleDTO.IdDTO dto);

    void editMerchantArticle(PCMerchMerchantArticleDTO.ETO eto);

    PCMerchMerchantArticleVO.DetailVO detailMerchantArticle(PCMerchMerchantArticleDTO.IdDTO dto);

    PCMerchMerchantArticleVO.LinkListVO listLinkUrl(PCMerchMerchantArticleDTO.IdDTO dto);

}