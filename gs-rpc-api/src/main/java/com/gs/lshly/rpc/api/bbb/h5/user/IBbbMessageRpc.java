package com.gs.lshly.rpc.api.bbb.h5.user;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BBBMessageQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbMessageVO;

/**
 * @Author yangxi
 * @create 2021/4/6 15:33
 */
public interface IBbbMessageRpc {
    /**
     * 获取未读消息数
     * @param qto
     * @return
     */
    Integer getUnreadMessage(BBBMessageQTO.QTO qto);

    /**
     * 查询消息列表
     * @param qto
     * @return
     */
    PageData<BbbMessageVO.MessageListVO> queryMessageList(BBBMessageQTO.QTO qto);

    /**
     * 根据消息id查询消息详情
     * @param msgId
     * @return
     */
    BbbMessageVO.MessageDetailVO getMessageDetail(String msgId);
}
