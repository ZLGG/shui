package com.gs.lshly.biz.support.user.rpc.bbc;

import com.gs.lshly.biz.support.user.service.bbc.IBbcMessageService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BBBMessageQTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteNoticeQTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcMessageQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcMessageVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcSiteNoticeVO;
import com.gs.lshly.rpc.api.bbc.user.IBbcMessageRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author yangxi
 * @create 2021/4/6 15:36
 */
@DubboService
public class BbcMessageRpc implements IBbcMessageRpc {

    @Autowired
    private IBbcMessageService messageService;

    @Override
    public BbcMessageVO.UnReadCountsVO getUnreadMessage(BbcMessageQTO.QTO qto) {
        return messageService.getUnreadMessage(qto);
    }

    @Override
    public BbcMessageVO.MessageListVO queryMessageList(BbcMessageQTO.QTO qto) {
        return messageService.queryMessageList(qto);
    }

    @Override
    public BbcMessageVO.MessageDetailVO getMessageDetail(BbcMessageQTO.DetailQTO qto) {
        return messageService.getMessageDetail(qto);
    }

    @Override
    public PageData<BbcSiteNoticeVO.NoticeListVO> getNoticeList(BbcMessageQTO.NoticeListQTO qto) {
        return messageService.getNoticeList(qto);
    }

    @Override
    public PageData<BbcMessageVO.MessageList> getSysOrActMessageList(BbcMessageQTO.MessageQTO qto) {
        return messageService.getSysOrActMessageList(qto);
    }

    @Override
    public BbcSiteNoticeVO.NoticeListVO getNoticeMessageDetail(BbcSiteNoticeQTO.IDQTO qto) {
        return messageService.getNoticeMessageDetail(qto);
    }
}
