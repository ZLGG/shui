package com.gs.lshly.biz.support.foundation.repository.impl;

import com.gs.lshly.biz.support.foundation.entity.Message;
import com.gs.lshly.biz.support.foundation.mapper.MessageMapper;
import com.gs.lshly.biz.support.foundation.repository.IMessageRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yingjun
 * @since 2021-03-29
 */
@Service
public class MessageRepositoryImpl extends ServiceImpl<MessageMapper, Message> implements IMessageRepository {

}
