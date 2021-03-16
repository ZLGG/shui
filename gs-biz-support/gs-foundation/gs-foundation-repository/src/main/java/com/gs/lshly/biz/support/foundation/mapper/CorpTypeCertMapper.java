package com.gs.lshly.biz.support.foundation.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.foundation.entity.CorpTypeCert;
import com.gs.lshly.biz.support.foundation.mapper.view.CorpTypeCertView;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 企业类型证照 Mapper 接口
 * </p>
 *
 * @author xxfc
 * @since 2020-10-09
 */
@Repository
public interface CorpTypeCertMapper extends BaseMapper<CorpTypeCert> {


   @Select("SELECT distinct d.id,d.type_group,d.type_name,d.idx,tc.id loc_id,tc.cert_id,cd.cert_name,cd.is_need " +
           "FROM gs_corp_type_dict d " +
           "LEFT JOIN gs_corp_type_cert tc ON d.id = tc.corp_type_id " +
           "LEFT JOIN gs_corp_cert_dict cd ON tc.cert_id = cd.id " +
           "WHERE d.flag=0 and ${ew.sqlSegment}")
    List<CorpTypeCertView> mapperListCorpTypeCert(@Param(value = "ew") QueryWrapper<CorpTypeCertView> ew);
}
