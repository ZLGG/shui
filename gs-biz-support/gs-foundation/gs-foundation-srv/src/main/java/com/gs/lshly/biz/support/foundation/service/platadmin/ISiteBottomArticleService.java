package com.gs.lshly.biz.support.foundation.service.platadmin;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteBottomArticleDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteBottomArticleQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteBottomArticleVO;

import java.util.List;

public interface ISiteBottomArticleService {

    List<SiteBottomArticleVO.PCListVO> list(SiteBottomArticleQTO.PCQTO qto);

    void bottomArticleEditor(SiteBottomArticleDTO.PCUDTO eto);
}