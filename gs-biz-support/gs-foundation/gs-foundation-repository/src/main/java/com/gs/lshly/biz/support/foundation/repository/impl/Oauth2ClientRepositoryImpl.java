package com.gs.lshly.biz.support.foundation.repository.impl;

import com.gs.lshly.biz.support.foundation.entity.Oauth2Client;
import com.gs.lshly.biz.support.foundation.mapper.Oauth2ClientMapper;
import com.gs.lshly.biz.support.foundation.repository.IOauth2ClientRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 认证客户端 服务实现类
 * </p>
 *
 * @author lxus
 * @since 2020-10-26
 */
@Service
public class Oauth2ClientRepositoryImpl extends ServiceImpl<Oauth2ClientMapper, Oauth2Client> implements IOauth2ClientRepository {

}
