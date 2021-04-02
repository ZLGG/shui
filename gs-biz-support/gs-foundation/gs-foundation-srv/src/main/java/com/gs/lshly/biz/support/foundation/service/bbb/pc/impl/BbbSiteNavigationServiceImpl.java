package com.gs.lshly.biz.support.foundation.service.bbb.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.SiteBanner;
import com.gs.lshly.biz.support.foundation.entity.SiteNavigation;
import com.gs.lshly.biz.support.foundation.repository.ISiteBannerRepository;
import com.gs.lshly.biz.support.foundation.repository.ISiteNavigationRepository;
import com.gs.lshly.biz.support.foundation.service.bbb.pc.IBbbSiteNavigationService;
import com.gs.lshly.common.enums.*;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.foundation.dto.BbbSiteNavigationDTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteNavigationQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteNavigationVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteFloorVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import java.util.Comparator;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author 陈奇
* @since 2020-10-14
*/
@Component
public class BbbSiteNavigationServiceImpl implements IBbbSiteNavigationService {

    @Autowired
    private ISiteNavigationRepository repository;

    @Autowired
    private ISiteBannerRepository siteBannerRepository;

    @Override
    public BbbSiteNavigationVO.HomeVO homeDetail() {
        QueryWrapper<SiteNavigation> wrapper = MybatisPlusUtil.query();
        wrapper.eq("subject", SubjectEnum.默认.getCode());
        wrapper.eq("terminal", TerminalEnum.BBB.getCode());
        wrapper.eq("pc_show", PcH5Enum.YES.getCode());
        List<SiteNavigation> siteNavigationList = repository.list(wrapper);
        BbbSiteNavigationVO.HomeVO detailVO = new BbbSiteNavigationVO.HomeVO();
        if(ObjectUtils.isNotEmpty(siteNavigationList)){
            for(SiteNavigation siteNavigation:siteNavigationList){
                if(siteNavigation.getType().equals(SiteNavigationEnum.顶部链接.getCode())){
                    BbbSiteNavigationVO.TopLinkVO topLinkVO = new BbbSiteNavigationVO.TopLinkVO();
                    BeanUtils.copyProperties(siteNavigation,topLinkVO);
                    detailVO.getTopLinkList().add(topLinkVO);
                }else if(siteNavigation.getType().equals(SiteNavigationEnum.菜单导航.getCode())){
                    BbbSiteNavigationVO.MenuVO menuVO = new BbbSiteNavigationVO.MenuVO();
                    BeanUtils.copyProperties(siteNavigation,menuVO);
                    detailVO.getMenuList().add(menuVO);
                }
            }
            detailVO.getTopLinkList().sort(Comparator.comparing(BbbSiteNavigationVO.TopLinkVO::getIdx));
            detailVO.getMenuList().sort(Comparator.comparing(BbbSiteNavigationVO.MenuVO::getIdx));
        }
        QueryWrapper<SiteBanner> siteBannerQueryWrapper = MybatisPlusUtil.query();
        siteBannerQueryWrapper.eq("subject", SubjectEnum.默认.getCode());
        siteBannerQueryWrapper.eq("terminal", TerminalEnum.BBB.getCode());
        siteBannerQueryWrapper.eq("pc_show", PcH5Enum.YES.getCode());
        siteBannerQueryWrapper.eq("is_classify", TrueFalseEnum.否.getCode());
        siteBannerQueryWrapper.orderByAsc("idx");
        List<SiteBanner> siteBannerList = siteBannerRepository.list(siteBannerQueryWrapper);
        detailVO.getBannerList().addAll(ListUtil.listCover(BbbSiteNavigationVO.BannerVO.class,siteBannerList));
        return detailVO;
    }

    @Override
    public BbbSiteNavigationVO.DetailVO subjectDetail(BbbSiteNavigationQTO.BQTO qto) {
        QueryWrapper<SiteNavigation> wrapper = MybatisPlusUtil.query();
        wrapper.eq("subject",qto.getSubject());
        wrapper.eq("terminal", TerminalEnum.BBB.getCode());
        wrapper.eq("pc_show", PcH5Enum.YES.getCode());
        List<SiteNavigation> siteNavigationList = repository.list(wrapper);
        BbbSiteNavigationVO.DetailVO detailVO = new BbbSiteNavigationVO.DetailVO();
        if(ObjectUtils.isNotEmpty(siteNavigationList)){
            for(SiteNavigation siteNavigation:siteNavigationList){
                if(siteNavigation.getType().equals(SiteNavigationEnum.顶部链接.getCode())){
                    BbbSiteNavigationVO.TopLinkVO topLinkVO = new BbbSiteNavigationVO.TopLinkVO();
                    BeanUtils.copyProperties(siteNavigation,topLinkVO);
                    detailVO.getTopLinkList().add(topLinkVO);
                }else if(siteNavigation.getType().equals(SiteNavigationEnum.菜单导航.getCode())){
                    BbbSiteNavigationVO.MenuVO menuVO = new BbbSiteNavigationVO.MenuVO();
                    BeanUtils.copyProperties(siteNavigation,menuVO);
                    detailVO.getMenuList().add(menuVO);
                }
            }
            detailVO.getTopLinkList().sort(Comparator.comparing(BbbSiteNavigationVO.TopLinkVO::getIdx));
            detailVO.getMenuList().sort(Comparator.comparing(BbbSiteNavigationVO.MenuVO::getIdx));
        }
        return detailVO;
    }

}
