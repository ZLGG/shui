package com.gs.lshly.biz.support.foundation.repository.impl;

import com.gs.lshly.biz.support.foundation.entity.Oauth2Token;
import com.gs.lshly.biz.support.foundation.mapper.Oauth2TokenMapper;
import com.gs.lshly.biz.support.foundation.repository.IOauth2TokenRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 认证token 服务实现类
 * </p>
 *
 * @author lxus
 * @since 2020-10-26
 */
@Service
public class Oauth2TokenRepositoryImpl extends ServiceImpl<Oauth2TokenMapper, Oauth2Token> implements IOauth2TokenRepository {

}
