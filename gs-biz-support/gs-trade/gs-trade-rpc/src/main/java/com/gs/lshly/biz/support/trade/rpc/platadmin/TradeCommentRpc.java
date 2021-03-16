package com.gs.lshly.biz.support.trade.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeCommentDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeCommentQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeCommentVO;
import com.gs.lshly.rpc.api.platadmin.trade.ITradeCommentRpc;
import com.gs.lshly.biz.support.trade.service.platadmin.ITradeCommentService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author Starry
* @since 2020-11-17
*/
@DubboService
public class TradeCommentRpc implements ITradeCommentRpc{
    @Autowired
    private ITradeCommentService  TradeCommentService;


    @Override
    public PageData<TradeCommentVO.CommentAppealListVO> pageData(TradeCommentQTO.AppealCommentQTO qto) {
        return TradeCommentService.pageData(qto);
    }

    @Override
    public TradeCommentVO.CommentAppealDetailVO detailTradeComment(TradeCommentDTO.IdDTO dto) {
        return TradeCommentService.detailTradeComment(dto);
    }

    @Override
    public void checkCommentAppeal(TradeCommentDTO.CheckCommentAppealDTO dto) {
        TradeCommentService.checkCommentAppeal(dto);
    }

    @Override
    public PageData<TradeCommentVO.CommentListVO> commentList(TradeCommentQTO.CommentQTO qto) {
        return TradeCommentService.commentList(qto);
    }

    @Override
    public TradeCommentVO.CommentDetailVO commentDetail(TradeCommentQTO.CommentDetailQTO qto) {
        return TradeCommentService.commentDetail(qto);
    }

    @Override
    public void delete(TradeCommentDTO.IdsDTO dto) {
        TradeCommentService.delete(dto);
    }

}