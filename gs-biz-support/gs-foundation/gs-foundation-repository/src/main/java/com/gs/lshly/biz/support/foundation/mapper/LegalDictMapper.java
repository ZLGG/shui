package com.gs.lshly.biz.support.foundation.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.foundation.entity.LegalDict;
import com.gs.lshly.biz.support.foundation.mapper.view.LegalCortpTypeView;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 法人单位 Mapper 接口
 * </p>
 *
 * @author xxfc
 * @since 2020-10-06
 */
@Repository
public interface LegalDictMapper extends BaseMapper<LegalDict> {

    @Select("SELECT lg.*,ct.type_name corpTypeName\n" +
            " FROM gs_legal_dict lg\n" +
            " LEFT JOIN gs_corp_type_dict ct ON lg.`corp_type_id` = ct.id\n" +
            " WHERE lg.flag=0 and ${ew.sqlSegment}" )
    IPage<LegalCortpTypeView> pageLegalCortpType(IPage<LegalCortpTypeView> pager,@Param(value = "ew") QueryWrapper<LegalCortpTypeView> ew);

    @Select("SELECT lg.*,ct.type_name corpTypeName\n" +
            " FROM gs_legal_dict lg\n" +
            " LEFT JOIN gs_corp_type_dict ct ON lg.`corp_type_id` = ct.id \n" +
            " WHERE lg.flag=0 and ${ew.sqlSegment}" )
    LegalCortpTypeView selectLegalCortpType(@Param(value = "ew")QueryWrapper<LegalCortpTypeView> ew);
}
