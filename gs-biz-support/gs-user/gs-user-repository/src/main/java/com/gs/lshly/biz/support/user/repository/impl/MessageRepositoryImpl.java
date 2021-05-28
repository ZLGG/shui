package com.gs.lshly.biz.support.user.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.user.entity.Message;
import com.gs.lshly.biz.support.user.mapper.IMessageMapper;
import com.gs.lshly.biz.support.user.repository.IMessageRepository;
import org.springframework.stereotype.Service;

/**
 * @Author yangxi
 * @create 2021/5/27 20:56
 */
@Service
public class MessageRepositoryImpl extends ServiceImpl<IMessageMapper, Message> implements IMessageRepository {
}
