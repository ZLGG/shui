package com.gs.lshly.biz.support.foundation.service.bbb.h5;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5SiteAdvertQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteAdvertVO;
import java.util.List;

public interface IBbbH5SiteAdvertService {

    List<BbbH5SiteAdvertVO.CategoryListVO> categoryAdvertList(BbbH5SiteAdvertQTO.CategoryQTO qto);

    List<BbbH5SiteAdvertVO.SubjectListVO> subjectAdvertList(BbbH5SiteAdvertQTO.SubjectQTO qto);

    List<BbbH5SiteAdvertVO.InnerCategoryAdvertListVO> innerCategoryAdvertList(BaseDTO dto);
}