package com.gs.lshly.rpc.api.platadmin.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.MerchantArticleDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.MerchantArticleQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.MerchantArticleVO;

/**
*
* @author hyy
* @since 2020-10-29
*/
public interface IMerchantArticleRpc {

    PageData<MerchantArticleVO.ListVO> pageData(Integer pageNum,Integer pageSize );

    MerchantArticleVO.DetailVO detailMerchantArticle(MerchantArticleDTO.IdDTO dto);

    void deleteBatchMerchantArticle(MerchantArticleDTO.IdListDTO dto);

    void applyMerchantArticle(MerchantArticleDTO.ApplyDTO dto);
}