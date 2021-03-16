package com.gs.lshly.rpc.api.bbc.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.foundation.dto.BbcSiteBroadDTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteBroadQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteBroadVO;

import java.util.List;

/**
*
* @author hyy
* @since 2020-11-03
*/
public interface IBbcSiteBroadRpc {

    List<BbcSiteBroadVO.ListVO> list(BbcSiteBroadQTO.QTO qto);

    BbcSiteBroadVO.DetailsVO details(BbcSiteBroadDTO.IdDTO dto);
}