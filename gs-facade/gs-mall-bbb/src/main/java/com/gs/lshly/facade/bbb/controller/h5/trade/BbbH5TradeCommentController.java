package com.gs.lshly.facade.bbb.controller.h5.trade;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeCommentBuildDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeCommentDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5TradeCommentQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeCommentListVO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeCommentBuildDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeCommentDTO;
import com.gs.lshly.rpc.api.bbb.h5.trade.IBbbH5TradeCommentRpc;
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
@RequestMapping("/bbb/h5")
@Api(tags = "交易评论管理")
public class BbbH5TradeCommentController {

    @DubboReference
    private IBbbH5TradeCommentRpc bbbH5TradeCommentRpc;


    @ApiOperation("订单商品评论")
    @PostMapping("/userCenter/tradeComment/orderComment")
    public ResponseData<BbbH5TradeCommentDTO.IdDTO> orderComment(@Valid @RequestBody BbbH5TradeCommentBuildDTO.DTO dto) {

        return bbbH5TradeCommentRpc.orderComment(dto);
    }

    @ApiOperation("订单商品评论列表")
    @PostMapping("/orderComment/orderCommentPage")
    public ResponseData<PageData<BbbH5TradeCommentListVO.ListVO>> orderCommentPage(@Valid @RequestBody BbbH5TradeCommentQTO.CommentList qto) {

        return ResponseData.data(bbbH5TradeCommentRpc.orderCommentPage(qto));
    }
    @ApiOperation("追加评论")
    @PostMapping("/userCenter/tradeComment/orderCommentAdditional")
    public ResponseData<BbbH5TradeCommentDTO.IdDTO> orderCommentAdditional(@Valid @RequestBody BbbH5TradeCommentBuildDTO.AdditionalDTO dto) {

        return ResponseData.data(bbbH5TradeCommentRpc.orderCommentAdditional(dto));
    }
    @ApiOperation("匿名")
    @PostMapping("/userCenter/tradeComment/anonymous")
    public ResponseData<Void> orderCommentAnonymous(@Valid @RequestBody BbbH5TradeCommentDTO.IdDTO dto) {
        bbbH5TradeCommentRpc.orderCommentAnonymous(dto);
        return ResponseData.success(MsgConst.OPERATOR_SUCCESS);
    }
    @ApiOperation("删除")
    @PostMapping("/userCenter/tradeComment/deleteComment")
    public ResponseData<Void> deleteComment( BbbH5TradeCommentDTO.IdDTO dto) {
        bbbH5TradeCommentRpc.deleteComment(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

}
