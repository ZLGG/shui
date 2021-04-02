package com.gs.lshly.biz.support.foundation.service.bbb.h5.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.*;
import com.gs.lshly.biz.support.foundation.repository.*;
import com.gs.lshly.biz.support.foundation.service.bbb.h5.IBbbH5ComplexHomeService;
import com.gs.lshly.common.enums.PcH5Enum;
import com.gs.lshly.common.enums.SiteNavigationEnum;
import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5ComplexHomeQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5SiteFloorQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.*;
import com.gs.lshly.common.struct.common.dto.CommonSiteActiveDTO;
import com.gs.lshly.common.struct.common.vo.CommonSiteActiveVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbb.h5.commodity.IBbbH5GoodsInfoRpc;
import com.gs.lshly.rpc.api.common.ICommonSiteActiveRpc;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author hyy
* @since 2020-11-03
*/
@Component
public class BbbH5ComplexHomeServiceImpl implements IBbbH5ComplexHomeService {

    @Autowired
    private ISiteAdvertRepository siteAdvertRepository;

    @Autowired
    private ISiteBannerRepository siteBannerRepository;

    @Autowired
    private ISiteNavigationRepository siteNavigationRepository;

    @Autowired
    private ISiteVideoRepository siteVideoRepository;

    @Autowired
    private ISiteFloorRepository siteFloorRepository;

    @Autowired
    private ISiteFloorGoodsRepository siteFloorGoodsRepository;

    @DubboReference
    private IBbbH5GoodsInfoRpc bbbH5GoodsInfoRpc;

    @DubboReference
    private ICommonSiteActiveRpc commonSiteActiveRpc;

    @Override
    public BbbH5ComplexHomeVO.TopComplexVO topComplex(BbbH5ComplexHomeQTO.QTO qto) {
        BbbH5ComplexHomeVO.TopComplexVO topComplexVO = new BbbH5ComplexHomeVO.TopComplexVO();
        topComplexVO.setNavigationList(this.navigationList(qto));
        topComplexVO.setBannerList(this.bannerList(qto));
        topComplexVO.setVideoList(this.videoList(qto));
        topComplexVO.setFloorList(this.floorList(qto));
        CommonSiteActiveVO.ListVO listVO = getSiteActiveVO();
        if (null != listVO ){
            topComplexVO.setSiteActiveVO(listVO);
        }
        return topComplexVO;
    }

    private CommonSiteActiveVO.ListVO getSiteActiveVO(){
        CommonSiteActiveDTO.QueryDTO queryDTO = new CommonSiteActiveDTO.QueryDTO();
        queryDTO.setPcShow(PcH5Enum.NO.getCode());
        queryDTO.setTerminal(TerminalEnum.BBB.getCode());
        return commonSiteActiveRpc.getCommonSiteActiveVO(queryDTO);
    }





    private List<BbbH5SiteNavigationVO.ListVO> navigationList(BbbH5ComplexHomeQTO.QTO qto) {
        QueryWrapper<SiteNavigation> wrapper =  MybatisPlusUtil.query();
        wrapper.eq("terminal", TerminalEnum.BBB.getCode());
        wrapper.eq("pc_show", PcH5Enum.NO.getCode());
        wrapper.eq("subject", SubjectEnum.默认.getCode());
        wrapper.eq("type", SiteNavigationEnum.菜单导航.getCode());
        wrapper.orderByAsc("idx");
        List<SiteNavigation> siteNavigationList = siteNavigationRepository.list(wrapper);
        return ListUtil.listCover(BbbH5SiteNavigationVO.ListVO.class,siteNavigationList);
    }

    private  List<BbbH5SiteBannerVO.ListVO> bannerList(BbbH5ComplexHomeQTO.QTO qto) {
        QueryWrapper<SiteBanner> wrapper =  MybatisPlusUtil.query();
        wrapper.eq("pc_show", PcH5Enum.NO.getCode());
        wrapper.eq("terminal", TerminalEnum.BBB.getCode());
        wrapper.eq("subject",SubjectEnum.默认.getCode());
        List<SiteBanner> siteBannerList = siteBannerRepository.list( wrapper);
        return ListUtil.listCover(BbbH5SiteBannerVO.ListVO.class,siteBannerList);
    }

    private List<BbbH5SiteVideoVO.ListVO> videoList(BbbH5ComplexHomeQTO.QTO qto) {

        List<BbbH5SiteVideoVO.ListVO> listVOS = new ArrayList<>();

        QueryWrapper<SiteVideo> wrapper = MybatisPlusUtil.query();
        wrapper.eq("pc_show", PcH5Enum.NO.getCode());
        wrapper.eq("terminal", TerminalEnum.BBB.getCode());
        wrapper.eq("subject",SubjectEnum.默认.getCode());
        List<SiteVideo> siteVideoList = siteVideoRepository.list( wrapper);
        if (ObjectUtil.isEmpty(siteVideoList)){
            return listVOS;
        }
        for (SiteVideo video : siteVideoList){
            if (StringUtils.isNotBlank(video.getVideoUrl())){
                BbbH5SiteVideoVO.ListVO listVO = new BbbH5SiteVideoVO.ListVO();
                BeanUtils.copyProperties(video,listVO);
                listVOS.add(listVO);
            }
        }
        return listVOS;
    }

    private List<BbbH5SiteFloorVO.ListVO> floorList(BbbH5ComplexHomeQTO.QTO qto){
        QueryWrapper<SiteFloor> wrapper =  MybatisPlusUtil.query();
        wrapper.eq("terminal", TerminalEnum.BBB.getCode());
        wrapper.eq("subject", SubjectEnum.默认.getCode());
        wrapper.eq("pc_show", PcH5Enum.NO.getCode());
        wrapper.orderByAsc("idx");
        List<SiteFloor> floorList = siteFloorRepository.list(wrapper);
        return ListUtil.listCover(BbbH5SiteFloorVO.ListVO.class,floorList);
    }

    @Override
    public PageData<BbbH5GoodsInfoVO.HomeInnerServiceVO> goodsList(BbbH5SiteFloorQTO.GoodsListQTO qto) {
        QueryWrapper<SiteFloorGoods> siteFloorGoodsQueryWrapper = MybatisPlusUtil.query();
        siteFloorGoodsQueryWrapper.eq("floor_id",qto.getFloorId());
        siteFloorGoodsQueryWrapper.orderByAsc("idx");
        IPage<SiteFloorGoods> pager = MybatisPlusUtil.pager(qto);
        siteFloorGoodsRepository.page(pager,siteFloorGoodsQueryWrapper);
        List<String> goodsIdList  = ListUtil.getIdList(SiteFloorGoods.class,pager.getRecords(),"goodsId");
        if(ObjectUtil.isEmpty(goodsIdList)){
            PageData<BbbH5GoodsInfoVO.HomeInnerServiceVO> pageData = MybatisPlusUtil.toPageData(new ArrayList<>(),qto.getPageNum(),qto.getPageSize(),Long.valueOf(0));
            return pageData;
        }
        List<BbbH5GoodsInfoVO.HomeInnerServiceVO> innerGoodsList = bbbH5GoodsInfoRpc.getHomeGoodsInnerServiceVO(goodsIdList,new BaseDTO());
        PageData<BbbH5GoodsInfoVO.HomeInnerServiceVO> pageData = MybatisPlusUtil.toPageData(innerGoodsList,qto.getPageNum(),qto.getPageSize(),pager.getTotal());
        return pageData;
    }
}
