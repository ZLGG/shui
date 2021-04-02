package com.gs.lshly.biz.support.foundation.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gs.lshly.biz.support.foundation.entity.DataNotice;
import com.gs.lshly.biz.support.foundation.mapper.view.DataNoticeView;
import com.gs.lshly.common.struct.merchadmin.pc.foundation.vo.PCMerchDataNoticeVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataNoticeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 * @author lxus
 * @since 2020/09/14
 */
@Repository
public interface DataNoticeMapper extends BaseMapper<DataNotice> {

    @Select("select nt.*,recv.id recv_id,recv.notice_id,IFNULL(recv.state,10) state,recv.shop_id " +
            "from gs_data_notice nt " +
            "left join gs_data_notice_recv recv on nt.id=recv.notice_id " +
            "where nt.flag=0 and ${ew.sqlSegment}")
    IPage<DataNoticeView> mapperPage(IPage<DataNoticeView> page, @Param(value = "ew") QueryWrapper<DataNoticeView> qw);


    @Select("select  nt.*,recv.id recv_id,recv.notice_id,IFNULL(recv.state,10) state,recv.shop_id " +
            "from gs_data_notice nt " +
            "left join gs_data_notice_recv recv on nt.id=recv.notice_id " +
            "where nt.flag=0 and ${ew.sqlSegment}")
    DataNoticeView mapperOne(@Param(value = "ew") QueryWrapper<DataNoticeView> qw);
    @Select("select nt.*,recv.id recv_id,recv.notice_id,IFNULL(recv.state,10) state,recv.shop_id " +
            "from gs_data_notice nt " +
            "left join gs_data_notice_recv recv on nt.id=recv.notice_id " +
            "where nt.flag=0 and ${ew.sqlSegment}")
    List<PCMerchDataNoticeVO.ListVO> innerList(@Param(value = "ew") QueryWrapper<DataNoticeView> wrapper);
}
