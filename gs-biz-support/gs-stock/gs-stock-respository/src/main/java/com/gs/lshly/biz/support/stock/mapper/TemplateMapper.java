package com.gs.lshly.biz.support.stock.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.stock.entity.Template;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.common.struct.common.stock.TemplateVO;
import com.gs.lshly.common.struct.common.qto.TemplateQTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 运费模板表 Mapper 接口
 * </p>
 *
 * @author chenyang
 * @since 2021-06-18
 */
public interface TemplateMapper extends BaseMapper<Template> {


    @Select("select id,template_name,udate from gs_template " +
            "where flag =0 AND ${ew.sqlSegment}")
    IPage<TemplateVO.ListVO> list(IPage<TemplateVO.ListVO> page, @Param(Constants.WRAPPER) QueryWrapper<TemplateQTO.QTO> qw);

}
