package com.gs.lshly.biz.support.trade.rpc.bbc;

import com.gs.lshly.biz.support.trade.service.bbc.IBbcTradeCommentService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeCommentBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeCommentDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeCommentQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeCommentListVO;
import com.gs.lshly.rpc.api.bbc.trade.IBbcTradeCommentRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author oy
* @since 2020-11-06
*/
@DubboService
public class BbcTradeCommentRpc implements IBbcTradeCommentRpc{
    @Autowired
    private IBbcTradeCommentService  bbcTradeCommentService;

    @Override
    public ResponseData<BbcTradeCommentDTO.IdDTO> orderComment(BbcTradeCommentBuildDTO.DTO dto) {
        return bbcTradeCommentService.orderComment(dto);
    }

    @Override
    public PageData<BbcTradeCommentListVO.ListVO> orderCommentPage(BbcTradeCommentQTO.CommentList qto) {
        return bbcTradeCommentService.orderCommentPage(qto);
    }

}