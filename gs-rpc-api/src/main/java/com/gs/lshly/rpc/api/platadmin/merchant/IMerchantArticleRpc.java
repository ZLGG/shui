package com.gs.lshly.rpc.api.platadmin.merchant;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantArticleDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.MerchantArticleQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantArticleVO;

/**
*
* @author xxfc
* @since 2020-11-09
*/
public interface IMerchantArticleRpc {

    PageData<MerchantArticleVO.ListVO> pageData(MerchantArticleQTO.QTO qto);

    void deleteBatchMerchantArticle(MerchantArticleDTO.IdListDTO dto);

    void applyMerchantArticle(MerchantArticleDTO.ApplyDTO dto);

    MerchantArticleVO.DetailVO detailMerchantArticle(MerchantArticleDTO.IdDTO dto);
}