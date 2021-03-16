package com.gs.lshly.biz.support.foundation.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.foundation.entity.LegalCert;
import com.gs.lshly.biz.support.foundation.mapper.view.LegalCertView;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 法人单位证照 Mapper 接口
 * </p>
 *
 * @author xxfc
 * @since 2020-10-31
 */
@Repository
public interface LegalCertMapper extends BaseMapper<LegalCert> {

    @Select("SELECT distinct lc.id, lc.cert_id,lc.`legal_id`,lc.`cert_url`,cert.`cert_name` " +
            "FROM gs_legal_cert lc " +
            "LEFT JOIN gs_corp_cert_dict cert ON lc.`cert_id` = cert.id " +
            "WHERE cert.flag=0 and ${ew.sqlSegment}" )
    List<LegalCertView> listrLegalCert(@Param(value = "ew") QueryWrapper<LegalCertView> ew);
}
