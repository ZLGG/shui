package com.gs.lshly.biz.support.user.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.user.entity.Message;
import com.gs.lshly.common.struct.bbc.user.vo.BbcSiteNoticeVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


/**
 * @Author yangxi
 * @create 2021/4/6 16:08
 */
@Repository
public interface IMessageMapper extends BaseMapper<Message> {
    @Select("select id,type,type_detail,title,content,user_id,status,cdate from gs_message where ${ew.sqlSegment}")
    IPage<Message> queryMessageList(IPage<Message> page, @Param(Constants.WRAPPER) QueryWrapper<Message> queryWrapper);

    @Select("select count(*) from gs_message where user_id = #{userId} AND status = 0 and flag = 0")
    Integer getUnreadMessage(@Param("userId") String userId);

    @Select("select id, name, content, udate from gs_site_notice where id = #{id}")
    BbcSiteNoticeVO.NoticeListVO getNoticeMessageDetail(@Param("id") String id);

    @Select("select id, name, content, udate from gs_site_notice where ${ew.sqlSegment}")
    IPage<BbcSiteNoticeVO.NoticeListVO> getNoticeList(IPage<BbcSiteNoticeVO.NoticeListVO> pager, @Param(Constants.WRAPPER) QueryWrapper wrapper);
}
