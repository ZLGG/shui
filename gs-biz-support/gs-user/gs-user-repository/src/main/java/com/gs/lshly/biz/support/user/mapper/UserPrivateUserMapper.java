package com.gs.lshly.biz.support.user.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.user.entity.UserPrivateUser;
import com.gs.lshly.biz.support.user.mapper.view.UserPrivateUserView;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 会员的私域店铺 Mapper 接口
 * </p>
 *
 * @author xxfc
 * @since 2020-10-10
 */
@Repository
public interface UserPrivateUserMapper extends BaseMapper<UserPrivateUser> {

    @Select("select distinct u.id user_id,u.user_name,u.legal_id,u.email,u.birthday,u.phone,u.sex,pu.id,pu.user_type_id,pu.bind_state,pu.state,pu.reject_why,pu.cdate " +
            "from gs_user_private_user pu " +
            "left join gs_user u on u.id = pu.user_id " +
            "where u.flag=0 and ${ew.sqlSegment}")
    IPage<UserPrivateUserView> selectUserPrivateUser(IPage<UserPrivateUserView> page, @Param(value = "ew") QueryWrapper<UserPrivateUserView> qw);

    @Select("select distinct u.id user_id,u.user_name,u.legal_id,u.email,u.birthday,u.phone,u.sex,pu.id,pu.user_type_id,pu.bind_state,pu.state,pu.reject_why,pu.cdate " +
            "from gs_user_private_user pu " +
            "left join gs_user u on u.id = pu.user_id " +
            "where u.flag=0 and ${ew.sqlSegment}")
    UserPrivateUserView  detailUserPrivateUser(@Param(value = "ew") QueryWrapper<UserPrivateUserView> qw);

    @Select("select distinct u.id user_id,u.user_name,u.legal_id,u.email,u.birthday,u.phone,u.sex,pu.id,pu.user_type_id,pu.bind_state,pu.state,pu.reject_why,pu.cdate " +
            "from gs_user_private_user pu " +
            "left join gs_user u on u.id = pu.user_id " +
            "where u.flag=0 and ${ew.sqlSegment}")
    List<UserPrivateUserView> listUserPrivateUser(@Param(value = "ew") QueryWrapper<UserPrivateUserView> qw);

}
