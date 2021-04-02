package com.gs.lshly.biz.support.merchant.service.common;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.dto.CommonMerchantArticleDTO;
import com.gs.lshly.common.struct.common.qto.CommonMerchantArticleQTO;
import com.gs.lshly.common.struct.common.vo.CommonMerchantArticleVO;

/**
 * @Author Starry
 * @Date 17:39 2021/3/21
 */
public interface ICommonMerchantArticleService {

    PageData<CommonMerchantArticleVO.ListVO> pageMerchantArticleVO(CommonMerchantArticleQTO.QTO qto);

    CommonMerchantArticleVO.DetailVO detailMerchantArticle(CommonMerchantArticleDTO.IdDTO dto);


}
