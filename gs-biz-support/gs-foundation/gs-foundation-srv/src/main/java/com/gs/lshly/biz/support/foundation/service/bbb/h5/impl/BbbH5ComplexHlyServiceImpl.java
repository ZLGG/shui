package com.gs.lshly.biz.support.foundation.service.bbb.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.foundation.entity.SiteAdvert;
import com.gs.lshly.biz.support.foundation.entity.SiteBanner;
import com.gs.lshly.biz.support.foundation.entity.SiteVideo;
import com.gs.lshly.biz.support.foundation.repository.ISiteAdvertRepository;
import com.gs.lshly.biz.support.foundation.repository.ISiteBannerRepository;
import com.gs.lshly.biz.support.foundation.repository.ISiteVideoRepository;
import com.gs.lshly.biz.support.foundation.service.bbb.h5.IBbbH5ComplexHlyService;
import com.gs.lshly.common.enums.PcH5Enum;
import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteAdvertVO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteBannerVO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteVideoVO;
import com.gs.lshly.common.struct.bbb.h5.pages.qto.BbbH5HlyQTO;
import com.gs.lshly.common.struct.bbb.h5.pages.vo.BbbH5HlyVO;
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
public class BbbH5ComplexHlyServiceImpl implements IBbbH5ComplexHlyService {

    @Autowired
    private ISiteAdvertRepository siteAdvertRepository;

    @Autowired
    private ISiteBannerRepository siteBannerRepository;

    @Autowired
    private ISiteVideoRepository siteVideoRepository;

    @Override
    public BbbH5HlyVO.TopComplexVO topComplex(BbbH5HlyQTO.QTO qto) {
        BbbH5HlyVO.TopComplexVO topComplexVO = new BbbH5HlyVO.TopComplexVO();
        topComplexVO.setBannerList(this.bannerList(qto));
        topComplexVO.setVideoList(this.videoList(qto));
        topComplexVO.setAdvertList(this.advertList(qto));
        return topComplexVO;
    }


    private  List<BbbH5SiteBannerVO.ListVO> bannerList(BbbH5HlyQTO.QTO qto) {
        QueryWrapper<SiteBanner> wrapper =  MybatisPlusUtil.query();
        wrapper.eq("pc_show", PcH5Enum.NO.getCode());
        wrapper.eq("terminal", TerminalEnum.BBB.getCode());
        wrapper.eq("subject",SubjectEnum.好粮油.getCode());
        List<SiteBanner> siteBannerList = siteBannerRepository.list( wrapper);
        return ListUtil.listCover(BbbH5SiteBannerVO.ListVO.class,siteBannerList);
    }

    private List<BbbH5SiteVideoVO.ListVO> videoList(BbbH5HlyQTO.QTO qto) {
        QueryWrapper<SiteVideo> wrapper = MybatisPlusUtil.query();
        wrapper.eq("pc_show", PcH5Enum.NO.getCode());
        wrapper.eq("terminal", TerminalEnum.BBB.getCode());
        wrapper.eq("subject",SubjectEnum.好粮油.getCode());
        List<SiteVideo> siteVideoList = siteVideoRepository.list( wrapper);
        return ListUtil.listCover(BbbH5SiteVideoVO.ListVO.class,siteVideoList);
    }

    private  List<BbbH5SiteAdvertVO.SubjectListVO> advertList(BbbH5HlyQTO.QTO qto) {
        QueryWrapper<SiteAdvert> wrapper =  MybatisPlusUtil.query();
        wrapper.eq("pc_show", PcH5Enum.NO.getCode());
        wrapper.eq("terminal", TerminalEnum.BBB.getCode());
        wrapper.eq("subject",SubjectEnum.好粮油.getCode());
        List<SiteAdvert> siteAdvertList = siteAdvertRepository.list( wrapper);
        return ListUtil.listCover(BbbH5SiteAdvertVO.SubjectListVO.class,siteAdvertList);
    }

}
