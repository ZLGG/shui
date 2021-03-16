package com.gs.lshly.biz.support.trade.rpc.bbb.h5;
import com.gs.lshly.biz.support.trade.service.bbb.h5.IBbbH5TradeCommentService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeCommentBuildDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeCommentDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5TradeCommentQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeCommentListVO;
import com.gs.lshly.rpc.api.bbb.h5.trade.IBbbH5TradeCommentRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author oy
* @since 2020-11-06
*/
@DubboService
public class BbbH5TradeCommentRpc implements IBbbH5TradeCommentRpc {
    
    @Autowired
    private IBbbH5TradeCommentService bbcTradeCommentService;

    @Override
    public ResponseData<BbbH5TradeCommentDTO.IdDTO> orderComment(BbbH5TradeCommentBuildDTO.DTO dto) {
        return bbcTradeCommentService.orderComment(dto);
    }

    @Override
    public PageData<BbbH5TradeCommentListVO.ListVO> orderCommentPage(BbbH5TradeCommentQTO.CommentList qto) {
        return bbcTradeCommentService.orderCommentPage(qto);
    }

    @Override
    public BbbH5TradeCommentDTO.IdDTO orderCommentAdditional(BbbH5TradeCommentBuildDTO.AdditionalDTO dto) {
        return bbcTradeCommentService.orderCommentAdditional(dto);
    }

    @Override
    public void orderCommentAnonymous(BbbH5TradeCommentDTO.IdDTO dto) {
        bbcTradeCommentService.orderCommentAnonymous(dto);
    }

    @Override
    public void deleteComment(BbbH5TradeCommentDTO.IdDTO dto) {
        bbcTradeCommentService.deleteComment(dto);
    }

}