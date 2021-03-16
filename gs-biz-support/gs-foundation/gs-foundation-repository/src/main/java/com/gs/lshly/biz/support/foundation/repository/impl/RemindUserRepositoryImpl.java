package com.gs.lshly.biz.support.foundation.repository.impl;

import com.gs.lshly.biz.support.foundation.entity.RemindUser;
import com.gs.lshly.biz.support.foundation.mapper.RemindUserMapper;
import com.gs.lshly.biz.support.foundation.repository.IRemindUserRepository;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
* <p>
 *  服务实现类
 * </p>
*
* @author xxfc
 * @since 2021-02-05
*/
@Service
public class RemindUserRepositoryImpl extends ServiceImpl<RemindUserMapper, RemindUser> implements IRemindUserRepository {

}