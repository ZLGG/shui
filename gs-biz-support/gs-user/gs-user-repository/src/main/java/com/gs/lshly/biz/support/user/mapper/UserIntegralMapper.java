package com.gs.lshly.biz.support.user.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.user.entity.UserIntegral;
import com.gs.lshly.biz.support.user.mapper.view.UserIntegralView;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 会员积分 Mapper 接口
 * </p>
 *
 * @author xxfc
 * @since 2020-11-20
 */
@Repository
public interface UserIntegralMapper extends BaseMapper<UserIntegral> {

    @Select("SELECT SUM(quantity) quantity,user_id FROM gs_user_integral " +
            "WHERE end_date > CURRENT_TIMESTAMP AND TIMESTAMPDIFF(DAY,CURRENT_TIMESTAMP,end_date) < #{passDay} and ${ew.sqlSegment}")
    UserIntegralView sumCountPass(@Param("passDay") Integer passDay,@Param(value = "ew") QueryWrapper<UserIntegral> qw);

    @Select("SELECT SUM(quantity) quantity,user_id FROM gs_user_integral WHERE end_date > CURRENT_TIMESTAMP  and ${ew.sqlSegment} " )
    UserIntegralView sumCount(@Param(value = "ew") QueryWrapper<UserIntegral> qw);

    @Select("select gs.direction_integral,gs.phone, gi.point_balance as okIntegral, gi.year_balance from gs_user gs left join gs_user_ctcc_point gi on gs.id = gi.user_id where gs.id = #{userId}")
    BbcUserVO.MyIntegralVO myIntegral(@Param("userId") String userId);

    @Select("select count(1) from gs_user_shopping_car where user_id = #{userId}")
    Integer goodsIsInCart(@Param("userId") String jwtUserId);
}
