package com.gs.lshly.biz.support.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.user.entity.ActivityShop;
import com.gs.lshly.common.struct.fy.activity.vo.ActivityShopVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 活动关联商家表 Mapper 接口
 * </p>
 *
 * @author zhaoqiang
 * @since 2020-12-24
 */
@Repository
public interface ActivityShopMapper extends BaseMapper<ActivityShop> {


    @Select("select shop.id,shop.shop_name from fy_activity_shop actshop \n" +
            "\tINNER JOIN gs_shop shop on actshop.shop_id = shop.id\n" +
            "\twhere shop.flag = 0 and actshop.activity_id=#{activityId}")
    List<ActivityShopVO.ListVO> activityShop(@Param("activityId")String activityId);

}
