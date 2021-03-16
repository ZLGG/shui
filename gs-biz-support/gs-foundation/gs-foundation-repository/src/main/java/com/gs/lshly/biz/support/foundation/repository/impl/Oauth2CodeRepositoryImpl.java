package com.gs.lshly.biz.support.foundation.repository.impl;

import com.gs.lshly.biz.support.foundation.entity.Oauth2Code;
import com.gs.lshly.biz.support.foundation.mapper.Oauth2CodeMapper;
import com.gs.lshly.biz.support.foundation.repository.IOauth2CodeRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 认证code 服务实现类
 * </p>
 *
 * @author lxus
 * @since 2020-10-26
 */
@Service
public class Oauth2CodeRepositoryImpl extends ServiceImpl<Oauth2CodeMapper, Oauth2Code> implements IOauth2CodeRepository {

}
