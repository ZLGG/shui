package com.gs.lshly.biz.support.user.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.user.entity.UserSignIn;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.lshly.common.struct.BaseDTO;

/**
 * <p>
 * 会员签到记录 服务类
 * </p>
 *
 * @author zdf
 * @since 2021-01-13
 */
public interface IUserSignInRepository extends IService<UserSignIn> {

    UserSignIn seletcNowDay(QueryWrapper<BaseDTO> query);
}
