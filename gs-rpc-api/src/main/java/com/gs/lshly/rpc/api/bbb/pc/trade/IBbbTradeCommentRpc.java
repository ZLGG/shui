package com.gs.lshly.rpc.api.bbb.pc.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeCommentBuildDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeCommentDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeCommentQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbTradeCommentListVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeCommentBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeCommentDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeCommentQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeCommentListVO;

/**
*
* @author oy
* @since 2020-11-06
*/
public interface IBbbTradeCommentRpc {


    ResponseData<BbbTradeCommentDTO.IdDTO> orderComment(BbbTradeCommentBuildDTO.DTO dto);

    PageData<BbbTradeCommentListVO.ListVO> orderCommentPage(BbbTradeCommentQTO.CommentList qto);

    BbbTradeCommentDTO.IdDTO orderCommentAdditional(BbbTradeCommentBuildDTO.AdditionalDTO dto);

    void orderCommentAnonymous(BbbTradeCommentDTO.IdDTO dto);

    void deleteComment(BbbTradeCommentDTO.IdDTO dto);

    ResponseData<BbbTradeCommentListVO.GoodsGrade> goodsCommentGrade(BbbTradeCommentDTO.GoodsIdDTO dto);

    PageData<BbbTradeCommentListVO.ListVO> myOrderCommentPage(BbbTradeCommentQTO.CommentList qto);
}