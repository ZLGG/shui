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
@Api(tags = "订单交易评论申述管理")
//@Module(code = "commentsOverview",parent = "transaction",name = "评论概述",index = 8)
public class TradeCommentOverviewController {

    @DubboReference
    private ITradeCommentRpc TradeCommentRpc;

    @ApiOperation("订单交易评论申诉列表")
    @GetMapping("")
//    @Func(code = "view",name = "查")
    public ResponseData<PageData<TradeCommentVO.CommentAppealListVO>> list(TradeCommentQTO.AppealCommentQTO qto) {
        return ResponseData.data(TradeCommentRpc.pageData(qto));
    }

    @ApiOperation("订单交易评论申诉详情")
    @GetMapping(value = "/{id}")
//    @Func(code = "view",name = "查")
    public ResponseData<TradeCommentVO.CommentAppealDetailVO> get(@PathVariable String id) {
        return ResponseData.data(TradeCommentRpc.detailTradeComment(new TradeCommentDTO.IdDTO(id)));
    }

    @ApiOperation("审核订单交易评论申诉")
    @PostMapping(value = "checkAppeal")
//    @Func(code = "edit",name = "改")
    public ResponseData<Void> get(@RequestBody TradeCommentDTO.CheckCommentAppealDTO dto) {
       TradeCommentRpc.checkCommentAppeal(dto);
       return ResponseData.success(MsgConst.APPLY_SUCCESS);
    }


}
