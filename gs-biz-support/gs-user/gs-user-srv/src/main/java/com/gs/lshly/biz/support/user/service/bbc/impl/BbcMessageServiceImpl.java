package com.gs.lshly.biz.support.user.service.bbc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gs.lshly.biz.support.user.entity.Message;
import com.gs.lshly.biz.support.user.entity.Notice;
import com.gs.lshly.biz.support.user.entity.UserFavoritesGoods;
import com.gs.lshly.biz.support.user.mapper.IMessageMapper;
import com.gs.lshly.biz.support.user.mapper.INoticeMapper;
import com.gs.lshly.biz.support.user.repository.IMessageRepository;
import com.gs.lshly.biz.support.user.service.bbb.h5.IBbbH5MessageService;
import com.gs.lshly.biz.support.user.service.bbc.IBbcMessageService;
import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BBBMessageQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbMessageVO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteNoticeQTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcMessageQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcMessageVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcSiteNoticeVO;
import com.gs.lshly.common.utils.BeanUtils;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteNoticeRpc;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author yangxi
 * @create 2021/4/6 15:46gs_site_notice
 */
@Component
@Slf4j
public class BbcMessageServiceImpl implements IBbcMessageService {

    @Autowired
    private IMessageMapper messageMapper;
    @Autowired
    private IMessageRepository repository;
    @Autowired
    private INoticeMapper noticeMapper;

    @Override
    public BbcMessageVO.UnReadCountsVO getUnreadMessage(BbcMessageQTO.QTO qto) {
        BbcMessageVO.UnReadCountsVO unReadCountsVO = new BbcMessageVO.UnReadCountsVO();
        QueryWrapper<Message> wrapper = new QueryWrapper<Message>()
                .eq("status", 0)
                .eq("user_id", qto.getJwtUserId());
        List<Message> list = repository.list(wrapper);
        // 获取所有未读消息数量
        unReadCountsVO.setCounts(list.size());
        // 获取未读系统消息数量
        List<Message> sysList = list.stream().filter(m-> m.getType() == 1).collect(Collectors.toList());
        unReadCountsVO.setSysCounts(sysList.size());
        // 获取未读活动消息数量
        unReadCountsVO.setActCounts(list.size()-sysList.size());
        return unReadCountsVO;
    }

    @Override
    public PageData<BbcSiteNoticeVO.NoticeListVO> getNoticeList(BbcMessageQTO.NoticeListQTO qto) {
        QueryWrapper<Notice> wrapper = MybatisPlusUtil.query();
        wrapper.eq("terminal", TerminalEnum.BBC.getCode());
        wrapper.eq("subject", SubjectEnum.积分商城.getCode());
        wrapper.orderByDesc("udate");
        IPage<Notice> page = new Page<>(qto.getPageNum(),qto.getPageSize());
        IPage<Notice> pageData = noticeMapper.selectPage(page,wrapper);
        List<BbcSiteNoticeVO.NoticeListVO> resultList = ListUtil.listCover(BbcSiteNoticeVO.NoticeListVO.class, pageData.getRecords());
        return new PageData<>(resultList, qto.getPageNum(),qto.getPageSize(),pageData.getTotal());
    }

    @Override
    public PageData<BbcMessageVO.MessageList> getSysOrActMessageList(BbcMessageQTO.MessageQTO qto) {
        QueryWrapper qw = MybatisPlusUtil.query();
        qw.eq("user_id", qto.getJwtUserId());
        qw.eq("flag", false);
        if (qto.getType() == 1) {
            qw.eq("type",1);
        }else {
            qw.eq("type",2);
        }
        qw.orderByDesc("cdate");
        IPage<Message> pager = MybatisPlusUtil.pager(qto);
        repository.page(pager,qw);
        if(ObjectUtils.isEmpty(pager.getRecords())){
            return new PageData<>(new ArrayList<>(),qto.getPageNum(),qto.getPageSize(),pager.getTotal());
        }
        List<BbcMessageVO.MessageList> list = ListUtil.listCover(BbcMessageVO.MessageList.class, pager.getRecords());
        return new PageData<>(list,qto.getPageNum(),qto.getPageSize(),pager.getTotal());
    }

    @Override
    public BbcSiteNoticeVO.NoticeListVO getNoticeMessageDetail(BbcSiteNoticeQTO.IDQTO qto) {
        BbcSiteNoticeVO.NoticeListVO noticeListVO = messageMapper.getNoticeMessageDetail(qto.getId());
        return noticeListVO;
    }

    @Override
    public BbcMessageVO.MessageDetailVO getMessageDetail(BbcMessageQTO.DetailQTO qto) {
        Message message = repository.getById(qto.getId());
        BbcMessageVO.MessageDetailVO messageDetailVO = new BbcMessageVO.MessageDetailVO();
        BeanUtils.copyProperties(message,messageDetailVO);
        return messageDetailVO;
    }

    @Override
    public BbcMessageVO.MessageListVO queryMessageList(BbcMessageQTO.QTO qto) {
        BbcMessageVO.MessageListVO messageListVO = new BbcMessageVO.MessageListVO();
        // 获取最新一条系统消息
        QueryWrapper<Message> sysWrapper = new QueryWrapper<>();
        sysWrapper.eq("user_id", qto.getJwtUserId());
        sysWrapper.eq("type", 1);
        sysWrapper.orderByDesc("cdate");
        sysWrapper.last("limit 1");
        Message systemMessage = repository.getOne(sysWrapper);
        // 获取最新一条活动消息
        QueryWrapper<Message> actWrapper = new QueryWrapper<>();
        actWrapper.eq("user_id", qto.getJwtUserId());
        actWrapper.eq("type", 2);
        actWrapper.orderByDesc("cdate");
        actWrapper.last("limit 1");
        Message activityMessage = repository.getOne(actWrapper);
        if (ObjectUtils.isNotEmpty(activityMessage)) {
            BbcMessageVO.MessageList activity = new BbcMessageVO.MessageList();
            BeanUtils.copyProperties(activityMessage, activity);
            messageListVO.setActivityMessage(activity);
        }
        if (ObjectUtils.isNotEmpty(systemMessage)) {
            BbcMessageVO.MessageList system = new BbcMessageVO.MessageList();
            BeanUtils.copyProperties(systemMessage, system);
            messageListVO.setSystemMessage(system);
        }
        return messageListVO;
    }

}

