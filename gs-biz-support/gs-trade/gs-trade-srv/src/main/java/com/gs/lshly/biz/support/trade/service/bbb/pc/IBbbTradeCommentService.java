package com.gs.lshly.biz.support.trade.service.bbb.pc;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeCommentBuildDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeCommentDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeCommentQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbTradeCommentListVO;

public interface IBbbTradeCommentService {


    ResponseData<BbbTradeCommentDTO.IdDTO> orderComment(BbbTradeCommentBuildDTO.DTO dto);

    PageData<BbbTradeCommentListVO.ListVO> orderCommentPage(BbbTradeCommentQTO.CommentList qto);

    BbbTradeCommentDTO.IdDTO orderCommentAdditional(BbbTradeCommentBuildDTO.AdditionalDTO dto);

    void orderCommentAnonymous(BbbTradeCommentDTO.IdDTO dto);

    void deleteComment(BbbTradeCommentDTO.IdDTO dto);

    ResponseData<BbbTradeCommentListVO.GoodsGrade> goodsCommentGrade(BbbTradeCommentDTO.GoodsIdDTO dto);

    PageData<BbbTradeCommentListVO.ListVO> myOrderCommentPage(BbbTradeCommentQTO.CommentList qto);
}