package com.gs.lshly.biz.support.user.service.bbb.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.user.entity.Message;
import com.gs.lshly.biz.support.user.mapper.IMessageMapper;
import com.gs.lshly.biz.support.user.service.bbb.h5.IBbbH5MessageService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BBBMessageQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbMessageVO;
import com.gs.lshly.common.utils.BeanUtils;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author yangxi
 * @create 2021/4/6 15:46
 */
@Component
@Slf4j
public class BbbH5MessageServiceImpl implements IBbbH5MessageService {

    @Autowired
    private IMessageMapper messageMapper;

    @Override
    public Integer getUnreadMessage(BBBMessageQTO.QTO qto) {
        Integer count = messageMapper.getUnreadMessage(qto.getUserId());
        return count;
    }

    @Override
    public BbbMessageVO.MessageDetailVO getMessageDetail(String msgId) {
        BbbMessageVO.MessageDetailVO messageDetailVO = new BbbMessageVO.MessageDetailVO();
        Message message = messageMapper.selectById(msgId);
        BeanUtils.copyProperties(message,messageDetailVO);
        return messageDetailVO;
    }

    @Override
    public PageData<BbbMessageVO.MessageListVO> queryMessageList(BBBMessageQTO.QTO qto) {
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", qto.getUserId());
        queryWrapper.eq("flag",false);
        IPage<Message> page = MybatisPlusUtil.pager(qto);
        // 查询消息列表
        IPage<Message> pageData = messageMapper.queryMessageList(page,queryWrapper);
        // list转换
        List<BbbMessageVO.MessageListVO> messageListVOList = ListUtil.listCover(BbbMessageVO.MessageListVO.class,pageData.getRecords());
        return new PageData<>(messageListVOList, qto.getPageNum(), qto.getPageSize(), pageData.getTotal());
    }

}

