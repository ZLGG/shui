package com.gs.lshly.biz.support.foundation.repository.impl;

import com.gs.lshly.biz.support.foundation.entity.Settings;
import com.gs.lshly.biz.support.foundation.mapper.SettingsMapper;
import com.gs.lshly.biz.support.foundation.repository.ISettingsRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 基本设置 服务实现类
 * </p>
 *
 * @author 陈奇
 * @since 2020-10-12
 */
@Service
public class SettingsRepositoryImpl extends ServiceImpl<SettingsMapper, Settings> implements ISettingsRepository {

}
