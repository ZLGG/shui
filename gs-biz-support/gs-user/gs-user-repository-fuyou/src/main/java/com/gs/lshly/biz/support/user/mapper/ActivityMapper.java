package com.gs.lshly.biz.support.user.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.user.entity.Activity;
import com.gs.lshly.common.struct.fy.activity.vo.ActivityVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 活动表 Mapper 接口
 * </p>
 *
 * @author zhaoqiang
 * @since 2020-12-24
 */
@Repository
public interface ActivityMapper extends BaseMapper<Activity> {

    @Select("select act.*,actshop.shop_name shopName from fy_activity act \n" +
            "\tLEFT JOIN (select ashop.activity_id,GROUP_CONCAT(shop.shop_name) shop_name from fy_activity_shop ashop\n" +
            "\t\tINNER JOIN gs_shop shop on ashop.shop_id = shop.id\n" +
            "\t\tgroup by ashop.activity_id) actshop on act.id = actshop.activity_id\n" +
            "\t\twhere act.flag=0 AND ${ew.sqlSegment}")
    IPage<ActivityVO.ListVO> activityList(IPage<ActivityVO.ListVO> page,@Param(Constants.WRAPPER) QueryWrapper<ActivityVO.ListVO> qw);

    ActivityVO.DetailVO get(String id);
}
