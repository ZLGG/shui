package com.gs.lshly.biz.support.trade.service.bbc;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeCommentBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeCommentDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeCommentQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeCommentListVO;

public interface IBbcTradeCommentService {

    ResponseData<BbcTradeCommentDTO.IdDTO> orderComment(BbcTradeCommentBuildDTO.DTO dto);

    PageData<BbcTradeCommentListVO.ListVO> orderCommentPage(BbcTradeCommentQTO.CommentList qto);
}