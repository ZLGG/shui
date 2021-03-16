package com.gs.lshly.biz.support.foundation.service.bbc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.foundation.dto.BbcSiteAdvertDTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteAdvertQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteAdvertVO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteAdvertQTO;

import java.util.List;

public interface IBbcSiteAdvertService {

    List<BbcSiteAdvertVO.CategoryListVO> categoryAdvertList(BbcSiteAdvertQTO.CategoryQTO qto);

    List<BbcSiteAdvertVO.SubjectListVO> subjectAdvertList(BbcSiteAdvertQTO.SubjectQTO qto);

    PageData<BbcSiteAdvertVO.SubjectListVO> subjectAdvertPageList(BbcSiteAdvertQTO.SubjectPageQTO qto);

    List<BbcSiteAdvertVO.InnerCategoryAdvertListVO> innerCategoryAdvertList(BaseDTO dto);

}