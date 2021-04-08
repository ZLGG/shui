package com.gs.lshly.facade.bbc.controller.h5.user;

import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BBBMessageQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbMessageVO;
import com.gs.lshly.rpc.api.bbb.h5.user.IBbbMessageRpc;
import com.gs.lshly.rpc.api.bbc.user.IBbcMessageRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @Author yangxi
 * @create 2021/4/6 14:34
 */
@RestController
@RequestMapping("/bbc/userCenter/message")
@Api(tags = "信息管理-v1.1.0")
public class BbcMessageController {
    @DubboReference
    private IBbcMessageRpc messageRpc;

    @ApiOperation("查询未读消息数")
    @PostMapping("/getUnreadMessage")
    public ResponseData getUnreadMessage(@Valid @RequestBody BBBMessageQTO.QTO qto) {
        Optional.ofNullable(qto.getUserId()).orElseThrow(() -> new BusinessException("请求的用户不存在"));
        return ResponseData.data(messageRpc.getUnreadMessage(qto));
    }

    @ApiOperation("获取消息列表")
    @PostMapping("/queryMessageList")
    public ResponseData<PageData<BbbMessageVO.MessageListVO>> queryMessageList(@Valid @RequestBody BBBMessageQTO.QTO qto) {
        Optional.ofNullable(qto.getUserId()).orElseThrow(() -> new BusinessException("请求的用户不存在"));
        PageData<BbbMessageVO.MessageListVO> messageListVOPageData = messageRpc.queryMessageList(qto);
        return ResponseData.data(messageListVOPageData);
    }

    @ApiOperation("获取消息详情")
    @GetMapping("/getMessageDetail")
    public ResponseData getMessageDetail(@ApiParam(name = "msgId",value = "消息id") @RequestParam(value = "msgId",required = true) String msgId) {
        BbbMessageVO.MessageDetailVO messageDetailVO = messageRpc.getMessageDetail(msgId);
        return ResponseData.data(messageDetailVO);
    }

}
