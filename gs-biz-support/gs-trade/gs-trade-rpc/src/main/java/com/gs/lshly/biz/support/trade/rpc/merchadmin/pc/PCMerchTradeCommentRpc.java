package com.gs.lshly.biz.support.trade.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeCommentDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeCommentQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeCommentVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchTradeCommentRpc;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchTradeCommentService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author Starry
* @since 2020-11-16
*/
@DubboService
public class PCMerchTradeCommentRpc implements IPCMerchTradeCommentRpc{
    @Autowired
    private IPCMerchTradeCommentService  pCMerchTradeCommentService;


    @Override
    public void appealTradeComment(PCMerchTradeCommentDTO.AppealCommentETO dto) {
        pCMerchTradeCommentService.appealTradeComment(dto);
    }

    @Override
    public PageData<PCMerchTradeCommentVO.CommentAppealListVO> pageData(PCMerchTradeCommentQTO.AppealCommentQTO qto) {
        return pCMerchTradeCommentService.pageData(qto);
    }

    @Override
    public PCMerchTradeCommentVO.AppealDetailVO getAppealDetailVO(PCMerchTradeCommentDTO.IdDTO dto) {
        return pCMerchTradeCommentService.getAppealDetailVO(dto);
    }


    @Override
    public void addTradeComment(PCMerchTradeCommentDTO.ETO eto){
        pCMerchTradeCommentService.addTradeComment(eto);
    }

    @Override
    public void deleteTradeComment(PCMerchTradeCommentDTO.IdDTO dto){
        pCMerchTradeCommentService.deleteTradeComment(dto);
    }


    @Override
    public void editTradeComment(PCMerchTradeCommentDTO.ETO eto){
        pCMerchTradeCommentService.editTradeComment(eto);
    }

    @Override
    public PCMerchTradeCommentVO.DetailVO detailTradeComment(PCMerchTradeCommentDTO.IdDTO dto){
        return  pCMerchTradeCommentService.detailTradeComment(dto);
    }

    @Override
    public PageData<PCMerchTradeCommentVO.CommentListListVO> commentList(PCMerchTradeCommentQTO.QTO qto) {
        return pCMerchTradeCommentService.commentList(qto);
    }

    @Override
    public PCMerchTradeCommentVO.CommentDetailVO commentDetail(PCMerchTradeCommentDTO.IdDTO dto) {
        return pCMerchTradeCommentService.commentDetail(dto);
    }

    @Override
    public PCMerchTradeCommentVO.CommentOverViewVO commentOverView(PCMerchTradeCommentDTO.CommentListETO dto) {
        return pCMerchTradeCommentService.commentOverView(dto);
    }

    @Override
    public void commentReply(PCMerchTradeCommentDTO.CommentReplyDTO dto) {
        pCMerchTradeCommentService.commentReply(dto);
    }

    @Override
    public void commentAppendReply(PCMerchTradeCommentDTO.CommentReplyDTO dto) {
        pCMerchTradeCommentService.commentAppendReply(dto);
    }

}