package com.gs.lshly.biz.support.foundation.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.MerchantArticleDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.MerchantArticleQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.MerchantArticleVO;

public interface IMerchantArticleService {

    PageData<MerchantArticleVO.ListVO> pageData(Integer pageNum,Integer pageSize);

    MerchantArticleVO.DetailVO detailMerchantArticle(MerchantArticleDTO.IdDTO dto);

    void deleteBatchMerchantArticle(MerchantArticleDTO.IdListDTO dto);

    void applyMerchantArticle(MerchantArticleDTO.ApplyDTO dto);
}