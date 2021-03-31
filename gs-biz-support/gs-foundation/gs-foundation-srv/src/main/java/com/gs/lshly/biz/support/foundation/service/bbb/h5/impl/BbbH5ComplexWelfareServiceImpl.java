package com.gs.lshly.biz.support.foundation.service.bbb.h5.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.SiteBanner;
import com.gs.lshly.biz.support.foundation.entity.SiteFloor;
import com.gs.lshly.biz.support.foundation.entity.SiteFloorGoods;
import com.gs.lshly.biz.support.foundation.repository.ISiteAdvertRepository;
import com.gs.lshly.biz.support.foundation.repository.ISiteBannerRepository;
import com.gs.lshly.biz.support.foundation.repository.ISiteFloorGoodsRepository;
import com.gs.lshly.biz.support.foundation.repository.ISiteFloorRepository;
import com.gs.lshly.biz.support.foundation.service.bbb.h5.IBbbH5ComplexWelfareService;
import com.gs.lshly.common.enums.PcH5Enum;
import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteBannerVO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteFloorVO;
import com.gs.lshly.common.struct.bbb.h5.pages.qto.BbbH5WelfareQTO;
import com.gs.lshly.common.struct.bbb.h5.pages.vo.BbbH5WelfareVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbb.h5.commodity.IBbbH5GoodsInfoRpc;
import org.apache.dubbo.config.annotation.DubboReference;
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
public class BbbH5ComplexWelfareServiceImpl implements IBbbH5ComplexWelfareService {

    @Autowired
    private ISiteAdvertRepository repository;
    @Autowired
    private ISiteBannerRepository siteBannerRepository;
    @Autowired
    private ISiteFloorRepository siteFloorRepository;
    @Autowired
    private ISiteFloorGoodsRepository siteFloorGoodsRepository;
    
    @DubboReference
    private IBbbH5GoodsInfoRpc bbbH5GoodsInfoRpc;

    @Override
    public BbbH5WelfareVO.TopComplexVO topComplex(BbbH5WelfareQTO.QTO qto) {
        BbbH5WelfareVO.TopComplexVO topComplexVO  = new BbbH5WelfareVO.TopComplexVO();
        topComplexVO.setBannerList(this.bannerList(qto));
        topComplexVO.setFloorList(this.floorList(qto));
        return topComplexVO;
    }


    private  List<BbbH5SiteBannerVO.ListVO> bannerList(BbbH5WelfareQTO.QTO qto) {
        QueryWrapper<SiteBanner> wrapper =  MybatisPlusUtil.query();
        wrapper.eq("pc_show", PcH5Enum.NO.getCode());
        wrapper.eq("terminal", TerminalEnum.BBB.getCode());
        wrapper.eq("subject", SubjectEnum.扶贫.getCode());
        List<SiteBanner> siteBannerList = siteBannerRepository.list( wrapper);
        return ListUtil.listCover(BbbH5SiteBannerVO.ListVO.class,siteBannerList);
    }

    private List<BbbH5SiteFloorVO.ListVO> floorList(BbbH5WelfareQTO.QTO qto) {
        QueryWrapper<SiteFloor> siteFloorQueryWrapper =  MybatisPlusUtil.query();
        siteFloorQueryWrapper.eq("pc_show", PcH5Enum.NO.getCode());
        siteFloorQueryWrapper.eq("terminal", TerminalEnum.BBB.getCode());
        siteFloorQueryWrapper.eq("subject", SubjectEnum.扶贫.getCode());
        siteFloorQueryWrapper.orderByAsc("idx");
        List<SiteFloor> floorList = siteFloorRepository.list(siteFloorQueryWrapper);
        return ListUtil.listCover(BbbH5SiteFloorVO.ListVO.class,floorList);
    }


    @Override
    public PageData<BbbH5GoodsInfoVO.HomeInnerServiceVO> floorGoodsQuery(BbbH5WelfareQTO.FloorGoodsQTO qto) {
        QueryWrapper<SiteFloorGoods> siteFloorGoodsQueryWrapper = MybatisPlusUtil.query();
        siteFloorGoodsQueryWrapper.eq("floor_id",qto.getFloorId());
        siteFloorGoodsQueryWrapper.orderByAsc("idx");
        IPage<SiteFloorGoods> pager = MybatisPlusUtil.pager(qto);
        siteFloorGoodsRepository.page(pager,siteFloorGoodsQueryWrapper);
        List<String> goodsIdLst  = ListUtil.getIdList(SiteFloorGoods.class,pager.getRecords(),"goodsId");
        if (ObjectUtil.isEmpty(goodsIdLst)) {
            PageData<BbbH5GoodsInfoVO.HomeInnerServiceVO> pageData = MybatisPlusUtil.toPageData(new ArrayList<>(),qto.getPageNum(),qto.getPageSize(),Long.valueOf(0));
            return pageData;
        }
        List<BbbH5GoodsInfoVO.HomeInnerServiceVO> innerGoodsList = bbbH5GoodsInfoRpc.getHomeGoodsInnerServiceVO(goodsIdLst,new BaseDTO());
        PageData<BbbH5GoodsInfoVO.HomeInnerServiceVO> pageData = MybatisPlusUtil.toPageData(innerGoodsList,qto.getPageNum(),qto.getPageNum(),pager.getTotal());
        return pageData;
    }
}
