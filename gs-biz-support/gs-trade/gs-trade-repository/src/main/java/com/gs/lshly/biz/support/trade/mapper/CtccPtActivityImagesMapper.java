package com.gs.lshly.biz.support.trade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.trade.entity.CtccPtActivityImages;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author yangxi
 * @create 2021/5/14 16:21
 */
@Mapper
public interface CtccPtActivityImagesMapper extends BaseMapper<CtccPtActivityImages> {
    @Select("select * from gs_ctcc_pt_activity_images where activity_id = #{id}")
    List<CtccPtActivityImages> selectByActivityId(@Param("id") String id);
}
