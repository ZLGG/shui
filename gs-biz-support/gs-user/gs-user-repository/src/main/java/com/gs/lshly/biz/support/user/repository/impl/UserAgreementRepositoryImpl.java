package com.gs.lshly.biz.support.user.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.user.entity.UserAgreement;
import com.gs.lshly.biz.support.user.mapper.UserAgreementMapper;
import com.gs.lshly.biz.support.user.repository.IUserAgreementRepository;
import org.springframework.stereotype.Service;

/**
* <p>
 *  服务实现类
 * </p>
*
* @author xxfc
 * @since 2021-01-14
*/
@Service
public class UserAgreementRepositoryImpl extends ServiceImpl<UserAgreementMapper, UserAgreement> implements IUserAgreementRepository {

}