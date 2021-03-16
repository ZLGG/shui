package com.gs.lshly.biz.support.merchant.repository.impl;

import com.gs.lshly.biz.support.merchant.entity.Settings;
import com.gs.lshly.biz.support.merchant.mapper.SettingsMapper;
import com.gs.lshly.biz.support.merchant.repository.ISettingsRepository;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
* <p>
 *  服务实现类
 * </p>
*
* @author Starry
 * @since 2020-10-30
*/
@Service
public class SettingsRepositoryImpl extends ServiceImpl<SettingsMapper, Settings> implements ISettingsRepository {

}