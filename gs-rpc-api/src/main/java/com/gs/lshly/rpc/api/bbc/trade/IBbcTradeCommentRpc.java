package com.gs.lshly.rpc.api.bbc.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeCommentBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeCommentDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeCommentQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeCommentListVO;

/**
*
* @author oy
* @since 2020-11-06
*/
public interface IBbcTradeCommentRpc {


    ResponseData<BbcTradeCommentDTO.IdDTO> orderComment(BbcTradeCommentBuildDTO.DTO dto);

    PageData<BbcTradeCommentListVO.ListVO> orderCommentPage(BbcTradeCommentQTO.CommentList qto);
}