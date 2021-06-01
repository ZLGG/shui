package com.gs.lshly.biz.support.user.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.user.entity.Notice;
import com.gs.lshly.common.struct.bbc.user.vo.BbcSiteNoticeVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author yangxi
 * @create 2021/6/1 17:19
 */
@Repository
public interface INoticeMapper extends BaseMapper<Notice> {
    @Select("select id, name, content, udate from gs_site_notice where ${ew.sqlSegment}")
    List<BbcSiteNoticeVO.NoticeListVO> getNoticeList(@Param(Constants.WRAPPER) QueryWrapper wrapper);
}
