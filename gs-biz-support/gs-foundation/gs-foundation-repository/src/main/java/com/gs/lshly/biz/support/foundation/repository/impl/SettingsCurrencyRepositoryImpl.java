package com.gs.lshly.biz.support.foundation.repository.impl;

import com.gs.lshly.biz.support.foundation.entity.SettingsCurrency;
import com.gs.lshly.biz.support.foundation.mapper.SettingsCurrencyMapper;
import com.gs.lshly.biz.support.foundation.repository.ISettingsCurrencyRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 货币设置 服务实现类
 * </p>
 *
 * @author 陈奇
 * @since 2020-10-12
 */
@Service
public class SettingsCurrencyRepositoryImpl extends ServiceImpl<SettingsCurrencyMapper, SettingsCurrency> implements ISettingsCurrencyRepository {

}
