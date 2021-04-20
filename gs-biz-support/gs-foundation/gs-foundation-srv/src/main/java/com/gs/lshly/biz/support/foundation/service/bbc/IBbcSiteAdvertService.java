package com.gs.lshly.biz.support.foundation.service.bbc;
import java.util.List;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteAdvertQTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteAdvertQTO.SubjectQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteAdvertVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteAdvertVO.AdvertDetailVO;

public interface IBbcSiteAdvertService {

    List<BbcSiteAdvertVO.CategoryListVO> categoryAdvertList(BbcSiteAdvertQTO.CategoryQTO qto);

    List<BbcSiteAdvertVO.SubjectListVO> subjectAdvertList(BbcSiteAdvertQTO.SubjectQTO qto);

    PageData<BbcSiteAdvertVO.SubjectListVO> subjectAdvertPageList(BbcSiteAdvertQTO.SubjectPageQTO qto);

    List<BbcSiteAdvertVO.InnerCategoryAdvertListVO> innerCategoryAdvertList(BaseDTO dto);
    
    /**
     * 跟据主题查询广告信息
     * @param qto
     * @return
     */
    List<AdvertDetailVO> listBySubject(SubjectQTO qto);

}