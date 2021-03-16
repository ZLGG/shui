package com.gs.lshly.biz.support.trade.service.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeCommentDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeCommentQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeCommentVO;

public interface IPCMerchTradeCommentService {


    /**
     * 商家申诉列表
     * @param qto
     * @return
     */
    PageData<PCMerchTradeCommentVO.CommentAppealListVO> pageData(PCMerchTradeCommentQTO.AppealCommentQTO qto);

    /**
     * 对中评或者差评进行申诉
     * @param dto
     */
    void appealTradeComment(PCMerchTradeCommentDTO.AppealCommentETO dto);


    /**
     * 获取申诉详情
     * @param dto
     * @return
     */
    PCMerchTradeCommentVO.AppealDetailVO getAppealDetailVO(PCMerchTradeCommentDTO.IdDTO dto);


    void addTradeComment(PCMerchTradeCommentDTO.ETO eto);

    void deleteTradeComment(PCMerchTradeCommentDTO.IdDTO dto);


    void editTradeComment(PCMerchTradeCommentDTO.ETO eto);

    PCMerchTradeCommentVO.DetailVO detailTradeComment(PCMerchTradeCommentDTO.IdDTO dto);

    PageData<PCMerchTradeCommentVO.CommentListListVO> commentList(PCMerchTradeCommentQTO.QTO qto);

    PCMerchTradeCommentVO.CommentDetailVO commentDetail(PCMerchTradeCommentDTO.IdDTO dto);

    PCMerchTradeCommentVO.CommentOverViewVO commentOverView(PCMerchTradeCommentDTO.CommentListETO dto);

    void commentReply(PCMerchTradeCommentDTO.CommentReplyDTO dto);

    void commentAppendReply(PCMerchTradeCommentDTO.CommentReplyDTO dto);
}