package com.gs.lshly.biz.support.trade.rpc.bbb.pc;

import com.gs.lshly.biz.support.trade.service.bbb.pc.IBbbTradeCommentService;
import com.gs.lshly.biz.support.trade.service.bbc.IBbcTradeCommentService;
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
import com.gs.lshly.rpc.api.bbb.pc.trade.IBbbTradeCommentRpc;
import com.gs.lshly.rpc.api.bbc.trade.IBbcTradeCommentRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author oy
* @since 2020-11-06
*/
@DubboService
public class BbbTradeCommentRpc implements IBbbTradeCommentRpc {
    @Autowired
    private IBbbTradeCommentService bbbTradeCommentService;


    @Override
    public ResponseData<BbbTradeCommentDTO.IdDTO> orderComment(BbbTradeCommentBuildDTO.DTO dto) {
        return bbbTradeCommentService.orderComment(dto);
    }

    @Override
    public PageData<BbbTradeCommentListVO.ListVO> orderCommentPage(BbbTradeCommentQTO.CommentList qto) {
        return bbbTradeCommentService.orderCommentPage(qto);
    }

    @Override
    public BbbTradeCommentDTO.IdDTO orderCommentAdditional(BbbTradeCommentBuildDTO.AdditionalDTO dto) {
        return bbbTradeCommentService.orderCommentAdditional(dto);
    }

    @Override
    public void orderCommentAnonymous(BbbTradeCommentDTO.IdDTO dto) {
        bbbTradeCommentService.orderCommentAnonymous(dto);
    }

    @Override
    public void deleteComment(BbbTradeCommentDTO.IdDTO dto) {
        bbbTradeCommentService.deleteComment(dto);
    }

    @Override
    public ResponseData<BbbTradeCommentListVO.GoodsGrade> goodsCommentGrade(BbbTradeCommentDTO.GoodsIdDTO dto) {
        return bbbTradeCommentService.goodsCommentGrade(dto);
    }

    @Override
    public PageData<BbbTradeCommentListVO.ListVO> myOrderCommentPage(BbbTradeCommentQTO.CommentList qto) {
        return bbbTradeCommentService.myOrderCommentPage(qto);
    }
}