package com.gs.lshly.biz.support.user.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.user.mapper.view.UserView;
import com.gs.lshly.common.struct.bbc.user.dto.UserDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 会员 Mapper 接口
 * </p>
 *
 * @author xxfc
 * @since 2020-10-05
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT distinct us.*, tm.*,shop.id shop_id,shop.shop_name,point.point_balance FROM gs_user us LEFT JOIN (" +
            "SELECT ul.user_id,GROUP_CONCAT(dict.label_name) label_names ,GROUP_CONCAT(ul.label_id) label_ids,GROUP_CONCAT(dict.label_color) colors FROM gs_user_label ul " +
            "LEFT JOIN gs_user_label_dict dict ON ul.label_id = dict.id WHERE dict.flag= 0 " +
            "GROUP BY ul.user_id) tm ON us.id = tm.user_id " +
            "LEFT JOIN gs_shop shop ON us.from_shop_id = shop.id " +
            "LEFT JOIN gs_user_ctcc_point point ON us.id = point.user_id " +
            "where us.flag=0 and ${ew.sqlSegment}")
    IPage<UserView> pageList(IPage<UserView> page, @Param(value = "ew") QueryWrapper<UserView> qw);


    @Insert("INSERT INTO gs_user ( id, state, type, phone, cdate, udate, flag,member_type ) VALUES(#{user.id},#{user.state},#{user.type},#{user.phone},now(),now(),#{user.flag},#{user.memberType}) ")
    void saveUserInfo(@Param("user") UserDTO.ETO user);
}
