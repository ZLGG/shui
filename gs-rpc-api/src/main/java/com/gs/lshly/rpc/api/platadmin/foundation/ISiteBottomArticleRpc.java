package com.gs.lshly.rpc.api.platadmin.foundation;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteBottomArticleDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteBottomArticleQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteBottomArticleVO;

import java.util.List;

/**
*
* @author 陈奇
* @since 2020-10-08
*/
public interface ISiteBottomArticleRpc {

    List<SiteBottomArticleVO.PCListVO> list(SiteBottomArticleQTO.PCQTO qto);

    void bottomArticleEditor(SiteBottomArticleDTO.PCUDTO eto);

}