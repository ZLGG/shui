package com.gs.lshly.rpc.api.bbb.h5.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeCommentBuildDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeCommentDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5TradeCommentQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeCommentListVO;


/**
*
* @author oy
* @since 2020-11-06
*/
public interface IBbbH5TradeCommentRpc {


    ResponseData<BbbH5TradeCommentDTO.IdDTO> orderComment(BbbH5TradeCommentBuildDTO.DTO dto);

    PageData<BbbH5TradeCommentListVO.ListVO> orderCommentPage(BbbH5TradeCommentQTO.CommentList qto);

    BbbH5TradeCommentDTO.IdDTO orderCommentAdditional(BbbH5TradeCommentBuildDTO.AdditionalDTO dto);

    void orderCommentAnonymous(BbbH5TradeCommentDTO.IdDTO dto);

    void deleteComment(BbbH5TradeCommentDTO.IdDTO dto);
}