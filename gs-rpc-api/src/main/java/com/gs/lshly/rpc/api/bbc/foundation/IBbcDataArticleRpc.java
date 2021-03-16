package com.gs.lshly.rpc.api.bbc.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.foundation.dto.BbcDataArticleDTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcDataArticleQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcDataArticleVO;

import java.util.List;

/**
*
* @author hyy
* @since 2020-11-13
*/
public interface IBbcDataArticleRpc {

    List<BbcDataArticleVO.ListVO> list(BaseDTO dto);

}