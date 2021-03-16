package com.gs.lshly.rpc.api.bbb.pc.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.dto.BbbArticleCategoryDTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbArticleCategoryQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbArticleCategoryVO;

import java.util.List;

/**
*
* @author hyy
* @since 2020-11-13
*/
public interface IBbbArticleCategoryRpc {

    List<BbbArticleCategoryVO.HelpListVO> helpList(BaseDTO dto);

    PageData<BbbArticleCategoryVO.ArticleListVO> articleList(BbbArticleCategoryQTO.ArticleQTO qto);

    List<BbbArticleCategoryVO.SearchListVO> search(BbbArticleCategoryQTO.QTO qto);

    BbbArticleCategoryVO.DetailsVO details(BbbArticleCategoryDTO.IdDTO dto);


    BbbArticleCategoryVO.ArticleLinksVO homeIndexArticleLinks(BaseDTO dto);

}