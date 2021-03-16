package com.gs.lshly.biz.support.foundation.service.bbb.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.foundation.entity.SiteAdvert;
import com.gs.lshly.biz.support.foundation.repository.ISiteAdvertRepository;
import com.gs.lshly.biz.support.foundation.service.bbb.h5.IBbbH5SiteAdvertService;
import com.gs.lshly.common.enums.PcH5Enum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5SiteAdvertQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteAdvertVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author hyy
* @since 2020-11-03
*/
@Component
public class BbbH5SiteAdvertServiceImpl implements IBbbH5SiteAdvertService {

    @Autowired
    private ISiteAdvertRepository repository;

    @Override
    public List<BbbH5SiteAdvertVO.CategoryListVO> categoryAdvertList(BbbH5SiteAdvertQTO.CategoryQTO qto) {
        QueryWrapper<SiteAdvert> wrapper = MybatisPlusUtil.query();
        wrapper.eq("terminal", TerminalEnum.BBC.getCode());
        wrapper.eq("pc_show", PcH5Enum.NO.getCode());
        wrapper.eq("is_category",TrueFalseEnum.是.getCode());
        List<SiteAdvert> siteAdverList = repository.list( wrapper);
        return ListUtil.listCover(BbbH5SiteAdvertVO.CategoryListVO.class,siteAdverList);
    }

    @Override
    public List<BbbH5SiteAdvertVO.SubjectListVO> subjectAdvertList(BbbH5SiteAdvertQTO.SubjectQTO qto) {
        QueryWrapper<SiteAdvert> wrapper =  MybatisPlusUtil.query();
        wrapper.eq("terminal",TerminalEnum.BBC.getCode());
        wrapper.eq("pc_show",PcH5Enum.NO.getCode());
        wrapper.eq("is_category", TrueFalseEnum.否.getCode());
        if(null != qto.getSubject()){
            wrapper.eq("subject",qto.getSubject());
        }
        List<SiteAdvert> siteAdverList = repository.list( wrapper);
        return ListUtil.listCover(BbbH5SiteAdvertVO.SubjectListVO.class,siteAdverList);
    }

    @Override
    public List<BbbH5SiteAdvertVO.InnerCategoryAdvertListVO> innerCategoryAdvertList(BaseDTO dto) {
        QueryWrapper<SiteAdvert> wrapper = MybatisPlusUtil.query();
        wrapper.eq("terminal", TerminalEnum.BBC.getCode());
        wrapper.eq("pc_show", PcH5Enum.NO.getCode());
        wrapper.eq("is_category",TrueFalseEnum.是.getCode());
        List<SiteAdvert> siteAdverList = repository.list( wrapper);
        return ListUtil.listCover(BbbH5SiteAdvertVO.InnerCategoryAdvertListVO.class,siteAdverList);
    }

}
