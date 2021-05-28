package com.gs.lshly.facade.bbc.controller.h5.user;

import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteNoticeQTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcMessageQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcMessageVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcSiteNoticeVO;
import com.gs.lshly.rpc.api.bbc.user.IBbcMessageRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "消息管理-v1.1.0")
public class BbcMessageController {
    @DubboReference
    private IBbcMessageRpc messageRpc;

    @ApiOperation("查询未读消息数")
    @PostMapping("/getUnreadMessage")
    public ResponseData<BbcMessageVO.UnReadCountsVO> getUnreadMessage(BbcMessageQTO.QTO qto) {
        Optional.ofNullable(qto.getJwtUserId()).orElseThrow(() -> new BusinessException("请登陆后查看个人消息"));
        return ResponseData.data(messageRpc.getUnreadMessage(qto));
    }

    @ApiOperation("消息列表")
    @PostMapping("/queryMessageList")
    public ResponseData<BbcMessageVO.MessageListVO> queryMessageList(BbcMessageQTO.QTO qto) {
        Optional.ofNullable(qto.getJwtUserId()).orElseThrow(() -> new BusinessException("请登陆后查看个人消息"));
        BbcMessageVO.MessageListVO messageListVO = messageRpc.queryMessageList(qto);
        return ResponseData.data(messageListVO);
    }

    @ApiOperation("获取消息详情")
    @GetMapping("/getMessageDetail")
    public ResponseData<BbcMessageVO.MessageDetailVO> getMessageDetail(BbcMessageQTO.DetailQTO detailQTO) {
        BbcMessageVO.MessageDetailVO messageDetailVO = messageRpc.getMessageDetail(detailQTO);
        return ResponseData.data(messageDetailVO);
    }

    @ApiOperation("获取公告详情")
    @GetMapping("/getNoticeDetail")
    public ResponseData<BbcSiteNoticeVO.NoticeListVO> getNoticeMessageDetail(BbcSiteNoticeQTO.IDQTO qto) {
        BbcSiteNoticeVO.NoticeListVO notice = messageRpc.getNoticeMessageDetail(qto);
        return ResponseData.data(notice);
    }

    @ApiOperation("获取系统/活动通知列表")
    @GetMapping("/getSysOrActMessageList")
    public ResponseData<PageData<BbcMessageVO.MessageList>> getSysOrActMessageList(@Valid BbcMessageQTO.MessageQTO qto) {
        PageData<BbcMessageVO.MessageList> pageData = messageRpc.getSysOrActMessageList(qto);
        return ResponseData.data(pageData);
    }

    @ApiOperation("获取公告列表")
    @GetMapping("/getNoticeList")
    public ResponseData<PageData<BbcSiteNoticeVO.NoticeListVO>> getNoticeList(BbcMessageQTO.NoticeListQTO qto) {
        return ResponseData.data(messageRpc.getNoticeList(qto));
    }
}
