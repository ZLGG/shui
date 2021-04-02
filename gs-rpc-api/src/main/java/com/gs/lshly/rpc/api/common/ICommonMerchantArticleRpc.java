package com.gs.lshly.rpc.api.common;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.dto.CommonMerchantArticleDTO;
import com.gs.lshly.common.struct.common.qto.CommonMerchantArticleQTO;
import com.gs.lshly.common.struct.common.vo.CommonMerchantArticleVO;

/**
 * @Author Starry
 * @Date 17:56 2021/3/21
 */
public interface ICommonMerchantArticleRpc {

    PageData<CommonMerchantArticleVO.ListVO> pageMerchantArticleVO(CommonMerchantArticleQTO.QTO qto);

    CommonMerchantArticleVO.DetailVO detailMerchantArticle(CommonMerchantArticleDTO.IdDTO dto);

}
