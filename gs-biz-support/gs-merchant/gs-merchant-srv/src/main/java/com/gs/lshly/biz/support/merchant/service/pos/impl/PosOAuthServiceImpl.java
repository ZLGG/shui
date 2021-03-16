package com.gs.lshly.biz.support.merchant.service.pos.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.merchant.entity.QianmiToken;
import com.gs.lshly.biz.support.merchant.repository.IQianmiTokenRepository;
import com.gs.lshly.biz.support.merchant.service.pos.IPosOAuthService;
import com.gs.lshly.common.struct.pos.dto.PosOAuthDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 千米token服务实现
 * @author lxus
 * @since 2020-12-01
 */
@Service
public class PosOAuthServiceImpl implements IPosOAuthService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void persistence(PosOAuthDTO.TokenDTO dto) {

        QianmiToken token = new QianmiToken();

        BeanUtils.copyProperties(dto, token);

        //其他之前的token都删除
        repository.remove(new QueryWrapper<QianmiToken>().eq("app_key", dto.getAppKey()).eq("app_secret", dto.getAppSecret()));
        //新增新的token
        repository.save(token);

    }

    @Autowired
    private IQianmiTokenRepository repository;

    @Override
    public String getToken(String key, String secret) {
        QianmiToken token = repository.getOne(new QueryWrapper<QianmiToken>().eq("app_key", key).eq("app_secret", secret));
        return token.getId();
    }

    @Override
    public String getRefreshToken(String key, String secret) {
        QianmiToken token = repository.getOne(new QueryWrapper<QianmiToken>().eq("app_key", key).eq("app_secret", secret));
        return token.getRefreshToken();
    }


}
