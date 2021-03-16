package com.gs.lshly.biz.support.user.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.user.mapper.view.UserLabelView;
import com.gs.lshly.biz.support.user.entity.UserLabel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 会员标签关系 Mapper 接口
 * </p>
 *
 * @author xxfc
 * @since 2020-10-05
 */
@Repository
public interface UserLabelMapper extends BaseMapper<UserLabel> {

    @Select("select distinct dt.label_name,dt.label_color,md.user_id,md.label_id from gs_user_label md " +
            "left join gs_user_label_dict dt on md.label_id = dt.id " +
            "where dt.flag=0 and ${we.sqlSegment}")
    List<UserLabelView> getUserLabel(@Param(value = "we") QueryWrapper<UserLabelView> qw);

}
