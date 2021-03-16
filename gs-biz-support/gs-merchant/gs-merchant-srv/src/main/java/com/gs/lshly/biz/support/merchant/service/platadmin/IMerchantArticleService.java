package com.gs.lshly.biz.support.merchant.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantArticleDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.MerchantArticleQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantArticleVO;
import org.apache.ibatis.annotations.Param;

public interface IMerchantArticleService {

    PageData<MerchantArticleVO.ListVO> pageData(MerchantArticleQTO.QTO qto);

    void deleteBatchMerchantArticle(MerchantArticleDTO.IdListDTO dto);

    void applyMerchantArticle(MerchantArticleDTO.ApplyDTO eto);

    MerchantArticleVO.DetailVO detailMerchantArticle(MerchantArticleDTO.IdDTO dto);
}