package com.gs.lshly.biz.support.user.service.bbc;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BBBMessageQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbMessageVO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteNoticeQTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcMessageQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcMessageVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcSiteNoticeVO;

/**
 * @Author yangxi
 * @create 2021/4/6 15:45
 */
public interface IBbcMessageService {
    /**
     * 获取未读消息数
     * @param qto
     * @return
     */
    BbcMessageVO.UnReadCountsVO getUnreadMessage(BbcMessageQTO.QTO qto);

    /**
     * 查询消息列表
     * @param qto
     * @return
     */
    BbcMessageVO.MessageListVO queryMessageList(BbcMessageQTO.QTO qto);

    /**
     * 根据消息id查询消息详情
     * @return
     */
    BbcMessageVO.MessageDetailVO getMessageDetail(BbcMessageQTO.DetailQTO qto);

    /**
     * 根据id查询公告消息详情
     * @return
     */
    BbcSiteNoticeVO.NoticeListVO getNoticeMessageDetail(BbcSiteNoticeQTO.IDQTO qto);

    /**
     * 获取系统/活动通知列表
     * @param qto
     * @return
     */
    PageData<BbcMessageVO.MessageList> getSysOrActMessageList(BbcMessageQTO.MessageQTO qto);

    /**
     * 获取公告列表
     * @param qto
     * @return
     */
    PageData<BbcSiteNoticeVO.NoticeListVO> getNoticeList(BbcMessageQTO.NoticeListQTO qto);
}
