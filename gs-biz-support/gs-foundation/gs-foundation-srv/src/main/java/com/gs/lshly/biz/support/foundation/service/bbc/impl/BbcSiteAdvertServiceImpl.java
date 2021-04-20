package com.gs.lshly.biz.support.foundation.service.bbc.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.SiteAdvert;
import com.gs.lshly.biz.support.foundation.repository.ISiteAdvertRepository;
import com.gs.lshly.biz.support.foundation.service.bbc.IBbcSiteAdvertService;
import com.gs.lshly.common.enums.PcH5Enum;
import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteAdvertQTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteAdvertQTO.SubjectQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteAdvertVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteAdvertVO.AdvertDetailVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

import cn.hutool.core.collection.CollectionUtil;

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

	@Override
	public List<AdvertDetailVO> listBySubject(SubjectQTO qto) {
		QueryWrapper<SiteAdvert> wrapper = MybatisPlusUtil.query();
        wrapper.eq("terminal", TerminalEnum.BBC.getCode());
        wrapper.eq("subject",SubjectEnum.电信国际.getCode());
        List<SiteAdvert> siteAdverList = repository.list( wrapper);
        List<AdvertDetailVO> retList = new ArrayList<AdvertDetailVO>();
        if(CollectionUtil.isNotEmpty(siteAdverList)){
        	AdvertDetailVO advertDetailVO = null;
        	for(SiteAdvert siteAdvert:siteAdverList){
        		advertDetailVO = new AdvertDetailVO();
        		BeanCopyUtils.copyProperties(siteAdvert, advertDetailVO);
        		advertDetailVO.setName(siteAdvert.getText());
        		retList.add(advertDetailVO);
        	}
        }
        return retList;
	}

}
