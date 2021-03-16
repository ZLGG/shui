package com.gs.lshly.facade.merchant.controller.pc.trade;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeCommentDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeCommentQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeCommentVO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeCommentQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeCommentVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchTradeCommentRpc;
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
* @author Starry
* @since 2020-11-16
*/
@RestController
@RequestMapping("/merchadmin/tradeComment")
@Api(tags = "商家订单交易评论管理")
public class PCMerchTradeCommentController {

    @DubboReference
    private IPCMerchTradeCommentRpc pcMerchTradeCommentRpc;


    @ApiOperation("商家对中评或者差评进行一个申诉")
    @PutMapping(value = "appeal/{id}")
    public ResponseData<Void> appeal(@PathVariable String id, @Valid @RequestBody PCMerchTradeCommentDTO.AppealCommentETO eto) {
        eto.setCommentId(id);
        pcMerchTradeCommentRpc.appealTradeComment(eto);
        return ResponseData.success(MsgConst.APPEAL_COMMENT);
    }


    @ApiOperation("商家订单交易评论申诉列表")
    @GetMapping("")
    public ResponseData<PageData<PCMerchTradeCommentVO.CommentAppealListVO>> list(PCMerchTradeCommentQTO.AppealCommentQTO qto) {
        return ResponseData.data(pcMerchTradeCommentRpc.pageData(qto));
    }

    @ApiOperation("商家订单交易评论申诉详情")
    @GetMapping(value = "/{id}")
    public ResponseData<PCMerchTradeCommentVO.AppealDetailVO> get(@PathVariable String id) {
        return ResponseData.data(pcMerchTradeCommentRpc.getAppealDetailVO(new PCMerchTradeCommentDTO.IdDTO(id)));
    }

    @ApiOperation("新增商家订单交易评论")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody PCMerchTradeCommentDTO.ETO dto) {
            pcMerchTradeCommentRpc.addTradeComment(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除商家订单交易评论")
    @DeleteMapping(value = "/{id}")
    public ResponseData<Void> delete(@PathVariable String id) {
        PCMerchTradeCommentDTO.IdDTO dto = new PCMerchTradeCommentDTO.IdDTO(id);
        pcMerchTradeCommentRpc.deleteTradeComment(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("修改商家订单交易评论")
    @PutMapping(value = "/{id}")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody PCMerchTradeCommentDTO.ETO eto) {
        eto.setId(id);
        pcMerchTradeCommentRpc.editTradeComment(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }
    @ApiOperation("订单交易评论列表")
    @PostMapping("/comment")
    public ResponseData<PageData<PCMerchTradeCommentVO.CommentListListVO>> commentList( @Valid @RequestBody PCMerchTradeCommentQTO.QTO qto) {
        return ResponseData.data(pcMerchTradeCommentRpc.commentList(qto));
    }
    @ApiOperation("订单交易评论详情")
    @GetMapping("/commentDetail")
    public ResponseData<PCMerchTradeCommentVO.CommentDetailVO> commentDetail(PCMerchTradeCommentDTO.IdDTO dto) {
        return ResponseData.data(pcMerchTradeCommentRpc.commentDetail(dto));
    }
    @ApiOperation("评论概述")
    @GetMapping("/commentOverView")
    public ResponseData<PCMerchTradeCommentVO.CommentOverViewVO> commentOverView(PCMerchTradeCommentDTO.CommentListETO dto) {
        return ResponseData.data(pcMerchTradeCommentRpc.commentOverView(dto));
    }
    @ApiOperation("回复初评")
    @PostMapping("/commentReply")
    public ResponseData<Void> commentReply(@Valid @RequestBody PCMerchTradeCommentDTO.CommentReplyDTO dto) {
        pcMerchTradeCommentRpc.commentReply(dto);
        return ResponseData.success(MsgConst.REPLY_SUCCESS);
    }
    @ApiOperation("回复追评")
    @PostMapping("/commentAppendReply")
    public ResponseData<Void> commentAppendReply(@Valid @RequestBody PCMerchTradeCommentDTO.CommentReplyDTO dto) {
        pcMerchTradeCommentRpc.commentAppendReply(dto);
        return ResponseData.success(MsgConst.REPLY_SUCCESS);
    }

}
