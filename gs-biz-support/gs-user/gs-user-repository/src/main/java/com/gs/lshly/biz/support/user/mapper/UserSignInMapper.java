package com.gs.lshly.biz.support.user.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.user.entity.UserSignIn;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.common.struct.BaseDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 会员签到记录 Mapper 接口
 * </p>
 *
 * @author zdf
 * @since 2021-01-13
 */
public interface UserSignInMapper extends BaseMapper<UserSignIn> {
    @Select("select * from gs_user_sign_in where TO_DAYS(cdate)=TO_DAYS(now()) AND  ${ew.sqlSegment}")
    UserSignIn seletcNowDay(@Param(value = "ew") QueryWrapper<BaseDTO> qw);
}
