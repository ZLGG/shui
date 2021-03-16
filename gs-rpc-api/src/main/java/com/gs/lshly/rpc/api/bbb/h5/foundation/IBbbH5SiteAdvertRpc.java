package com.gs.lshly.rpc.api.bbb.h5.foundation;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5SiteAdvertQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteAdvertVO;
import java.util.List;

/**
*
* @author hyy
* @since 2020-11-03
*/
public interface IBbbH5SiteAdvertRpc {

    List<BbbH5SiteAdvertVO.CategoryListVO> categoryAdvertList(BbbH5SiteAdvertQTO.CategoryQTO qto);

    List<BbbH5SiteAdvertVO.SubjectListVO> subjectAdvertList(BbbH5SiteAdvertQTO.SubjectQTO qto);

    /**
     * 站点分类广告图
     * @param dto
     * @return
     */
    List<BbbH5SiteAdvertVO.InnerCategoryAdvertListVO> innerCategoryAdvertList(BaseDTO dto);

}