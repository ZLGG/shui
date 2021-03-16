package com.gs.lshly.biz.support.foundation.rpc.bbb.pc;
import com.gs.lshly.biz.support.foundation.service.bbb.pc.IBbbArticleCategoryService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.dto.BbbArticleCategoryDTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbArticleCategoryQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbArticleCategoryVO;
import com.gs.lshly.rpc.api.bbb.pc.foundation.IBbbArticleCategoryRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author hyy
* @since 2020-11-13
*/
@DubboService
public class BbbArticleCategoryRpc implements IBbbArticleCategoryRpc {

    @Autowired
    private IBbbArticleCategoryService bbbArticleCategoryService;


    @Override
    public List<BbbArticleCategoryVO.HelpListVO> helpList(BaseDTO dto) {
        return bbbArticleCategoryService.helpList(dto);
    }

    @Override
    public PageData<BbbArticleCategoryVO.ArticleListVO> articleList(BbbArticleCategoryQTO.ArticleQTO qto) {
        return bbbArticleCategoryService.articleList(qto);
    }

    @Override
    public List<BbbArticleCategoryVO.SearchListVO> search(BbbArticleCategoryQTO.QTO qto) {
        return bbbArticleCategoryService.search(qto);
    }

    @Override
    public BbbArticleCategoryVO.DetailsVO details(BbbArticleCategoryDTO.IdDTO dto) {
        return bbbArticleCategoryService.details(dto);
    }

    @Override
    public BbbArticleCategoryVO.ArticleLinksVO homeIndexArticleLinks(BaseDTO dto) {
        return bbbArticleCategoryService.homeIndexArticleLinks(dto);
    }
}