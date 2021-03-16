package com.gs.lshly.biz.support.trade.service.platadmin;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeCommentRecordDTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeCommentRecordVO;

public interface ITradeCommentRecordService {

    /**
     * 查询评论申诉信息
     * @param dto
     * @return
     */
    TradeCommentRecordVO.AppealRecordDetailVO getAppealDetailInfo(TradeCommentRecordDTO.CommentIdDTO dto);

}