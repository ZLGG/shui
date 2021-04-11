package com.gs.lshly.biz.support.user.rpc.bbc;

import com.gs.lshly.biz.support.user.service.bbb.h5.IBbbH5MessageService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BBBMessageQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbMessageVO;
import com.gs.lshly.rpc.api.bbb.h5.user.IBbbMessageRpc;
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
    private IBbbH5MessageService messageService;

    @Override
    public Integer getUnreadMessage(BBBMessageQTO.QTO qto) {
        return messageService.getUnreadMessage(qto);
    }

    @Override
    public PageData<BbbMessageVO.MessageListVO> queryMessageList(BBBMessageQTO.QTO qto) {
        return messageService.queryMessageList(qto);
    }

    @Override
    public BbbMessageVO.MessageDetailVO getMessageDetail(String msgId) {
        return messageService.getMessageDetail(msgId);
    }
}
