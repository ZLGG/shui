package com.gs.lshly.facade.platform.controller.trade;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeCommentDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeCommentQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeCommentVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.trade.ITradeCommentRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

/**
* <p>
*  前端控制器
* </p>
*
* @author Starry
* @since 2020-11-17
*/
@RestController
@RequestMapping("/platadmin/tradeComment")
@Api(tags = "订单交易评论管理")
//@Module(code = "listComment",parent = "transaction",name = "评论列表",index = 8)
public class TradeCommentController {

    @DubboReference
    private ITradeCommentRpc TradeCommentRpc;

    @ApiOperation("订单交易评论列表")
    @GetMapping("/comment")
//    @Func(code = "view",name = "查")
    public ResponseData<PageData<TradeCommentVO.CommentListVO>> commentList(TradeCommentQTO.CommentQTO qto) {
        return ResponseData.data(TradeCommentRpc.commentList(qto));
    }
    @ApiOperation("订单交易评论详情")
    @GetMapping("/commentDetail")
//    @Func(code = "view",name = "查")
    public ResponseData<TradeCommentVO.CommentDetailVO> commentDetail(TradeCommentQTO.CommentDetailQTO qto) {
        return ResponseData.data(TradeCommentRpc.commentDetail(qto));
    }
    @ApiOperation("删除评论")
    @GetMapping("/commentDelete")
//    @Func(code = "delete",name = "删除")
    public ResponseData<Void> commentDelete(TradeCommentDTO.IdsDTO dto) {
        TradeCommentRpc.delete(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

}
