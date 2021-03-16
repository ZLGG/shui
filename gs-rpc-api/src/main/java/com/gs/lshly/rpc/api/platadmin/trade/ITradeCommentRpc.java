package com.gs.lshly.rpc.api.platadmin.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeCommentDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeCommentQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeCommentVO;

/**
*
* @author Starry
* @since 2020-11-17
*/
public interface ITradeCommentRpc {

    /**
     * 评论申诉列表
     * @param qto
     * @return
     */
    PageData<TradeCommentVO.CommentAppealListVO> pageData(TradeCommentQTO.AppealCommentQTO qto);


    /**
     * 评论申诉详情信息
     * @param dto
     * @return
     */
    TradeCommentVO.CommentAppealDetailVO detailTradeComment(TradeCommentDTO.IdDTO dto);


    /**
     * 评论申诉审核
     * @param dto
     */
    void checkCommentAppeal(TradeCommentDTO.CheckCommentAppealDTO dto);


    PageData<TradeCommentVO.CommentListVO> commentList(TradeCommentQTO.CommentQTO qto);

    TradeCommentVO.CommentDetailVO commentDetail(TradeCommentQTO.CommentDetailQTO qto);

    void delete(TradeCommentDTO.IdsDTO dto);
}