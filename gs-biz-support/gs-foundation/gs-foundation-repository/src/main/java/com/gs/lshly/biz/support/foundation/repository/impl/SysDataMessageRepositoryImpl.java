package com.gs.lshly.biz.support.foundation.repository.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.foundation.entity.SysDataMessage;
import com.gs.lshly.biz.support.foundation.mapper.SysDataMessageMapper;
import com.gs.lshly.biz.support.foundation.repository.ISysDataMessageRepository;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  仓储服务
 * </p>
 * @author lxus
 * @since 2020-09-14
 */
@Component
public class SysDataMessageRepositoryImpl extends ServiceImpl<SysDataMessageMapper, SysDataMessage> implements ISysDataMessageRepository {

}
