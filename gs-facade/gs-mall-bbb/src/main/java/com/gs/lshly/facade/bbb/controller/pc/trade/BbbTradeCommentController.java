package com.gs.lshly.facade.bbb.controller.pc.trade;

import com.gs.lshly.common.constants.MsgConst;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/bbb")
@Api(tags = "交易评论管理")
public class BbbTradeCommentController {

    @DubboReference
    private IBbbTradeCommentRpc bbbTradeCommentRpc;


    @ApiOperation("订单商品评论")
    @PostMapping("/tradeComment/orderComment")
    public ResponseData<BbbTradeCommentDTO.IdDTO> orderComment(@Valid @RequestBody BbbTradeCommentBuildDTO.DTO dto) {

        return bbbTradeCommentRpc.orderComment(dto);
    }

    @ApiOperation("商品综合评分")
    @GetMapping("/tradeComment/goodsCommentGrade")
    public ResponseData<BbbTradeCommentListVO.GoodsGrade> goodsCommentGrade( BbbTradeCommentDTO.GoodsIdDTO dto) {

        return bbbTradeCommentRpc.goodsCommentGrade(dto);
    }


    @ApiOperation("我的评论列表")
    @PostMapping("/userCenter/tradeComment/myOrderCommentPage")
    public ResponseData<PageData<BbbTradeCommentListVO.ListVO>> myOrderCommentPage(@Valid @RequestBody BbbTradeCommentQTO.CommentList qto) {

        return ResponseData.data(bbbTradeCommentRpc.myOrderCommentPage(qto));
    }
    @ApiOperation("订单商品评论列表")
    @PostMapping("/tradeComment/orderCommentPage")
    public ResponseData<PageData<BbbTradeCommentListVO.ListVO>> orderCommentPage(@Valid @RequestBody BbbTradeCommentQTO.CommentList qto) {

        return ResponseData.data(bbbTradeCommentRpc.orderCommentPage(qto));
    }
    @ApiOperation("追加评论")
    @PostMapping("/tradeComment/orderCommentAdditional")
    public ResponseData<BbbTradeCommentDTO.IdDTO> orderCommentAdditional(@Valid @RequestBody BbbTradeCommentBuildDTO.AdditionalDTO dto) {

        return ResponseData.data(bbbTradeCommentRpc.orderCommentAdditional(dto));
    }
    @ApiOperation("匿名")
    @PostMapping("/tradeComment/anonymous")
    public ResponseData<Void> orderCommentAnonymous(@Valid @RequestBody BbbTradeCommentDTO.IdDTO dto) {
        bbbTradeCommentRpc.orderCommentAnonymous(dto);
        return ResponseData.success(MsgConst.OPERATOR_SUCCESS);
    }
    @ApiOperation("删除")
    @GetMapping("/tradeComment/deleteComment")
    public ResponseData<Void> deleteComment( BbbTradeCommentDTO.IdDTO dto) {
        bbbTradeCommentRpc.deleteComment(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

}
