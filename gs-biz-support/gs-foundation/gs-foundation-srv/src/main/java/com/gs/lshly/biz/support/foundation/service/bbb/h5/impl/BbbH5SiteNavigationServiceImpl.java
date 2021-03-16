package com.gs.lshly.biz.support.foundation.service.bbb.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.foundation.entity.SiteNavigation;
import com.gs.lshly.biz.support.foundation.repository.ISiteNavigationRepository;
import com.gs.lshly.biz.support.foundation.service.bbb.h5.IBbbH5SiteNavigationService;
import com.gs.lshly.common.enums.PcH5Enum;
import com.gs.lshly.common.enums.SiteNavigationEnum;
import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5SiteNavigationQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteNavigationVO;
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
* @since 2020-11-04
*/
@Component
public class BbbH5SiteNavigationServiceImpl implements IBbbH5SiteNavigationService {

    @Autowired
    private ISiteNavigationRepository repository;

    @Override
    public List<BbbH5SiteNavigationVO.ListVO> list(BbbH5SiteNavigationQTO.QTO qto) {
        QueryWrapper<SiteNavigation> wrapper =  MybatisPlusUtil.query();
        wrapper.eq("terminal", TerminalEnum.BBC.getCode());
        wrapper.eq("pc_show", PcH5Enum.NO.getCode());
        wrapper.eq("subject", SubjectEnum.默认.getCode());
        wrapper.eq("type", SiteNavigationEnum.菜单导航.getCode());
        wrapper.orderByAsc("idx");
        List<SiteNavigation> siteNavigationList=repository.list(wrapper);
        return ListUtil.listCover(BbbH5SiteNavigationVO.ListVO.class,siteNavigationList);
    }



}
