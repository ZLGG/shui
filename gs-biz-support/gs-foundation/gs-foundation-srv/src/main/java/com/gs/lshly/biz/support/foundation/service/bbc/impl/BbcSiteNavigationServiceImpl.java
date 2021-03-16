package com.gs.lshly.biz.support.foundation.service.bbc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.SiteNavigation;
import com.gs.lshly.biz.support.foundation.repository.ISiteNavigationRepository;
import com.gs.lshly.biz.support.foundation.service.bbc.IBbcSiteNavigationService;
import com.gs.lshly.common.enums.PcH5Enum;
import com.gs.lshly.common.enums.SiteNavigationEnum;
import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.foundation.dto.BbcSiteNavigationDTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteNavigationQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteNavigationVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author hyy
* @since 2020-11-04
*/
@Component
public class BbcSiteNavigationServiceImpl implements IBbcSiteNavigationService {

    @Autowired
    private ISiteNavigationRepository repository;

    @Override
    public List<BbcSiteNavigationVO.ListVO> list(BbcSiteNavigationQTO.QTO qto) {
        QueryWrapper<SiteNavigation> wrapper =  MybatisPlusUtil.query();
        wrapper.eq("terminal", TerminalEnum.BBC.getCode());
        wrapper.eq("pc_show", PcH5Enum.NO.getCode());
        wrapper.eq("subject", SubjectEnum.默认.getCode());
        wrapper.eq("type", SiteNavigationEnum.菜单导航.getCode());
        wrapper.orderByAsc("idx");
        List<SiteNavigation> siteNavigationList=repository.list(wrapper);
        return ListUtil.listCover(BbcSiteNavigationVO.ListVO.class,siteNavigationList);
    }



}
