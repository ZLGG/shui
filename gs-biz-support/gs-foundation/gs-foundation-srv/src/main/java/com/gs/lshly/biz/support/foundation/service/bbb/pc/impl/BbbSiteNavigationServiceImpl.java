package com.gs.lshly.biz.support.foundation.service.bbb.pc.impl;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.foundation.entity.SiteBanner;
import com.gs.lshly.biz.support.foundation.entity.SiteNavigation;
import com.gs.lshly.biz.support.foundation.repository.ISiteBannerRepository;
import com.gs.lshly.biz.support.foundation.repository.ISiteNavigationRepository;
import com.gs.lshly.biz.support.foundation.service.bbb.pc.IBbbSiteNavigationService;
import com.gs.lshly.common.enums.PcH5Enum;
import com.gs.lshly.common.enums.SiteNavigationEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.struct.bbb.pc.commodity.qto.PCBbbGoodsCategoryQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteNavigationQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteNavigationVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

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
    public BbbSiteNavigationVO.HomeVO homeDetail(PCBbbGoodsCategoryQTO.QTO qto) {
        QueryWrapper<SiteNavigation> wrapper = MybatisPlusUtil.query();
        wrapper.eq("subject", qto.getSubject());
        wrapper.eq("terminal", qto.getTerminal());
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
        siteBannerQueryWrapper.eq("subject", qto.getSubject());
        siteBannerQueryWrapper.eq("terminal",qto.getTerminal());
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
