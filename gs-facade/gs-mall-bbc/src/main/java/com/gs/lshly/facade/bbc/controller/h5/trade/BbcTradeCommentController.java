package com.gs.lshly.facade.bbc.controller.h5.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeCommentBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeCommentDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeCommentQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeCommentListVO;
import com.gs.lshly.rpc.api.bbc.trade.IBbcTradeCommentRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
* <p>
*  前端控制器
* </p>
*
* @author oy
* @since 2020-11-06
*/
@RestController
@RequestMapping("/bbc/tradeComment")
@Api(tags = "交易评论管理")
public class BbcTradeCommentController {

    @DubboReference
    private IBbcTradeCommentRpc bbcTradeCommentRpc;


    @ApiOperation("订单商品评论")
    @PostMapping("/orderComment")
    public ResponseData<BbcTradeCommentDTO.IdDTO> orderComment(@Valid @RequestBody BbcTradeCommentBuildDTO.DTO dto) {

        return bbcTradeCommentRpc.orderComment(dto);
    }

    @ApiOperation("订单商品评论列表")
    @PostMapping("/orderCommentPage")
    public ResponseData<PageData<BbcTradeCommentListVO.ListVO>> orderCommentPage(@Valid @RequestBody BbcTradeCommentQTO.CommentList qto) {

        return ResponseData.data(bbcTradeCommentRpc.orderCommentPage(qto));
    }

}
