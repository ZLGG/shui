package com.gs.lshly.rpc.api.bbc.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.foundation.dto.BbcDataArticleCategoryDTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcDataArticleCategoryQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcDataArticleCategoryVO;

import java.util.List;

/**
*
* @author hyy
* @since 2020-11-13
*/
public interface IBbcDataArticleCategoryRpc {

    List<BbcDataArticleCategoryVO.ListVO> list(BaseDTO dto);

    List<BbcDataArticleCategoryVO.SearchListVO> search(BbcDataArticleCategoryQTO.QTO qto);

    BbcDataArticleCategoryVO.DetailsVO details(BbcDataArticleCategoryDTO.IdDTO dto);


}