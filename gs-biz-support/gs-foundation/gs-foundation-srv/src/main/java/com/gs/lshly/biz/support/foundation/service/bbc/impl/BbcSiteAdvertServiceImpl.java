package com.gs.lshly.biz.support.foundation.service.bbc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.SiteAdvert;
import com.gs.lshly.biz.support.foundation.repository.ISiteAdvertRepository;
import com.gs.lshly.biz.support.foundation.service.bbc.IBbcSiteAdvertService;
import com.gs.lshly.common.enums.PcH5Enum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteAdvertQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteAdvertVO;
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
public class BbcSiteAdvertServiceImpl implements IBbcSiteAdvertService {

    @Autowired
    private ISiteAdvertRepository repository;

    @Override
    public List<BbcSiteAdvertVO.CategoryListVO> categoryAdvertList(BbcSiteAdvertQTO.CategoryQTO qto) {
        QueryWrapper<SiteAdvert> wrapper = MybatisPlusUtil.query();
        wrapper.eq("terminal", TerminalEnum.BBC.getCode());
        wrapper.eq("pc_show", PcH5Enum.NO.getCode());
        wrapper.eq("is_category",TrueFalseEnum.是.getCode());
        List<SiteAdvert> siteAdverList = repository.list( wrapper);
        return ListUtil.listCover(BbcSiteAdvertVO.CategoryListVO.class,siteAdverList);
    }

    @Override
    public List<BbcSiteAdvertVO.SubjectListVO> subjectAdvertList(BbcSiteAdvertQTO.SubjectQTO qto) {
        QueryWrapper<SiteAdvert> wrapper =  MybatisPlusUtil.query();
        wrapper.eq("terminal",TerminalEnum.BBC.getCode());
        wrapper.eq("pc_show",PcH5Enum.NO.getCode());
        wrapper.eq("is_category", TrueFalseEnum.否.getCode());
        if(null != qto.getSubject()){
            wrapper.eq("subject",qto.getSubject());
        }
        List<SiteAdvert> siteAdverList = repository.list( wrapper);
        return ListUtil.listCover(BbcSiteAdvertVO.SubjectListVO.class,siteAdverList);
    }

    @Override
    public PageData<BbcSiteAdvertVO.SubjectListVO> subjectAdvertPageList(BbcSiteAdvertQTO.SubjectPageQTO qto) {
        QueryWrapper<SiteAdvert> wrapper =  MybatisPlusUtil.query();
        wrapper.eq("terminal",TerminalEnum.BBC.getCode());
        wrapper.eq("pc_show",PcH5Enum.NO.getCode());
        wrapper.eq("is_category", TrueFalseEnum.否.getCode());
        wrapper.eq("subject",qto.getSubject());
        IPage<SiteAdvert> pager = MybatisPlusUtil.pager(qto);
        repository.page(pager,wrapper);
        return MybatisPlusUtil.toPageData(qto,BbcSiteAdvertVO.SubjectListVO.class,pager);
    }

    @Override
    public List<BbcSiteAdvertVO.InnerCategoryAdvertListVO> innerCategoryAdvertList(BaseDTO dto) {
        QueryWrapper<SiteAdvert> wrapper = MybatisPlusUtil.query();
        wrapper.eq("terminal", TerminalEnum.BBC.getCode());
        wrapper.eq("pc_show", PcH5Enum.NO.getCode());
        wrapper.eq("is_category",TrueFalseEnum.是.getCode());
        List<SiteAdvert> siteAdverList = repository.list( wrapper);
        return ListUtil.listCover(BbcSiteAdvertVO.InnerCategoryAdvertListVO.class,siteAdverList);
    }

}
