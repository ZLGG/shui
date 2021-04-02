package com.gs.lshly.biz.support.user.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.user.entity.UserIntegral;
import com.gs.lshly.biz.support.user.entity.UserShoppingCar;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.user.mapper.view.UserIntegralView;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 购物车 Mapper 接口
 * </p>
 *
 * @author xxfc
 * @since 2020-10-27
 */
@Repository
public interface UserShoppingCarMapper extends BaseMapper<UserShoppingCar> {
    @Select("SELECT IFNULL(SUM(quantity),0) quantity FROM gs_user_shopping_car WHERE  ${ew.sqlSegment}")
    int countShoppingCarGoods(@Param(Constants.WRAPPER) QueryWrapper<UserShoppingCar> qw);
}
