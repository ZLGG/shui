package com.gs.lshly.biz.support.foundation.service.bbb.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.dto.BbbArticleCategoryDTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbArticleCategoryQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbArticleCategoryVO;
import java.util.List;

public interface IBbbArticleCategoryService {

    List<BbbArticleCategoryVO.HelpListVO> helpList(BaseDTO dto);

    PageData<BbbArticleCategoryVO.ArticleListVO> articleList(BbbArticleCategoryQTO.ArticleQTO qto);

    List<BbbArticleCategoryVO.SearchListVO> search(BbbArticleCategoryQTO.QTO qto);

    BbbArticleCategoryVO.DetailsVO details(BbbArticleCategoryDTO.IdDTO dto);

    BbbArticleCategoryVO.ArticleLinksVO homeIndexArticleLinks(BaseDTO dto);

}