package com.gs.lshly.biz.support.user.repository.impl;

import com.gs.lshly.biz.support.user.entity.UserCard;
import com.gs.lshly.biz.support.user.mapper.UserCardMapper;
import com.gs.lshly.biz.support.user.repository.IUserCardRepository;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
* <p>
 *  服务实现类
 * </p>
*
* @author xxfc
 * @since 2020-10-27
*/
@Service
public class UserCardRepositoryImpl extends ServiceImpl<UserCardMapper, UserCard> implements IUserCardRepository {

}