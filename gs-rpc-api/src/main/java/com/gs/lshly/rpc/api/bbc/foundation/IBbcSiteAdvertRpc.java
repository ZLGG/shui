package com.gs.lshly.rpc.api.bbc.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.foundation.dto.BbcSiteAdvertDTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteAdvertQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteAdvertVO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteAdvertQTO;

import java.util.List;

/**
*
* @author hyy
* @since 2020-11-03
*/
public interface IBbcSiteAdvertRpc {

    List<BbcSiteAdvertVO.CategoryListVO> categoryAdvertList(BbcSiteAdvertQTO.CategoryQTO qto);

    List<BbcSiteAdvertVO.SubjectListVO> subjectAdvertList(BbcSiteAdvertQTO.SubjectQTO qto);

    
    
    PageData<BbcSiteAdvertVO.SubjectListVO> subjectAdvertPageList(BbcSiteAdvertQTO.SubjectPageQTO qto);

    /**
     * 站点分类广告图
     * @param dto
     * @return
     */
    List<BbcSiteAdvertVO.InnerCategoryAdvertListVO> innerCategoryAdvertList(BaseDTO dto);
    
    /**
     * 跟据主题查询对应的广告信息
     * @param qto
     * @return
     */
    List<BbcSiteAdvertVO.AdvertDetailVO> listBySubject(BbcSiteAdvertQTO.SubjectQTO qto);

}