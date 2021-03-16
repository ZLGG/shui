package com.gs.lshly.biz.support.foundation.repository.impl;

import com.gs.lshly.biz.support.foundation.entity.SettingsPayMethod;
import com.gs.lshly.biz.support.foundation.mapper.SettingsPayMethodMapper;
import com.gs.lshly.biz.support.foundation.repository.ISettingsPayMethodRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付方式设置 服务实现类
 * </p>
 *
 * @author 陈奇
 * @since 2020-10-12
 */
@Service
public class SettingsPayMethodRepositoryImpl extends ServiceImpl<SettingsPayMethodMapper, SettingsPayMethod> implements ISettingsPayMethodRepository {

}
