package com.gs.lshly.biz.support.foundation.service.bbc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gs.lshly.biz.support.foundation.complex.SiteFloorGoodsComlex;
import com.gs.lshly.biz.support.foundation.entity.SiteFloor;
import com.gs.lshly.biz.support.foundation.entity.SiteFloorGoods;
import com.gs.lshly.biz.support.foundation.repository.ISiteFloorGoodsRepository;
import com.gs.lshly.biz.support.foundation.repository.ISiteFloorRepository;
import com.gs.lshly.biz.support.foundation.service.bbc.IBbcSiteFloorService;
import com.gs.lshly.common.enums.PcH5Enum;
import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsInfoQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteFloorQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteFloorVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsInfoRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.prefs.BackingStoreException;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-11-02
*/
@Component
public class BbcSiteFloorServiceImpl implements IBbcSiteFloorService {

    @Autowired
    private ISiteFloorRepository repository;

    @Autowired
    private ISiteFloorGoodsRepository siteFloorGoodsRepository;

    @DubboReference
    private IBbcGoodsInfoRpc bbcGoodsInfoRpc;

    @Override
    public List<BbcSiteFloorVO.ListVO> list(BbcSiteFloorQTO.QTO qto) {
        QueryWrapper<SiteFloor> wrapper =  MybatisPlusUtil.query();
        wrapper.eq("terminal", TerminalEnum.BBC.getCode());
        wrapper.eq("subject",qto.getSubject());
        wrapper.eq("pc_show", PcH5Enum.NO.getCode());
        wrapper.orderByAsc("idx");
        List<SiteFloor> floorList = repository.list(wrapper);
        List<String> floorIdList = new ArrayList<>();
        Map<String, SiteFloorGoodsComlex> voMap = new HashMap<>();
        for(SiteFloor floorItem:floorList){
            floorIdList.add(floorItem.getId());
            BbcSiteFloorVO.ListVO listVO = new BbcSiteFloorVO.ListVO();
            BeanUtils.copyProperties(floorItem,listVO);
            SiteFloorGoodsComlex siteFloorGoodsComlex = new SiteFloorGoodsComlex();
            siteFloorGoodsComlex.setListVO(listVO);
            voMap.put(floorItem.getId(),siteFloorGoodsComlex);
        }
        if(ObjectUtils.isNotEmpty(floorIdList)){
            QueryWrapper<SiteFloorGoods> siteFloorGoodsQueryWrapper = MybatisPlusUtil.query();
            siteFloorGoodsQueryWrapper.in("floor_id",floorIdList);
            List<SiteFloorGoods> floorGoodsList = siteFloorGoodsRepository.list(siteFloorGoodsQueryWrapper);
            List<String> goodsIdList = new ArrayList<>();
            //去重一个商品在多个楼层里
            for(SiteFloorGoods siteFloorGoodsItem:floorGoodsList){
                if(!goodsIdList.contains(siteFloorGoodsItem.getGoodsId())){
                    goodsIdList.add(siteFloorGoodsItem.getGoodsId());
                }
                SiteFloorGoodsComlex siteFloorGoodsComlex = voMap.get(siteFloorGoodsItem.getFloorId());
                siteFloorGoodsComlex.getFloorGoodsList().add(siteFloorGoodsItem);
            }
            if(ObjectUtils.isNotEmpty(goodsIdList)){
                BbcGoodsInfoQTO.GoodsIdListQTO goodsIdListQTO  = new BbcGoodsInfoQTO.GoodsIdListQTO();
                goodsIdListQTO.setIdList(goodsIdList);
                List< BbcGoodsInfoVO.HomeAndShopInnerServiceVO> innerGoodsVoList = bbcGoodsInfoRpc.getInnerServiceVO(goodsIdListQTO);
                if(ObjectUtils.isNotEmpty(innerGoodsVoList)){
                    for (SiteFloorGoodsComlex comlex : voMap.values()) {
                        for(BbcGoodsInfoVO.HomeAndShopInnerServiceVO innerGoodsVo:innerGoodsVoList){
                            for(SiteFloorGoods floorGoods:comlex.getFloorGoodsList()){
                                if(floorGoods.getGoodsId().equals(innerGoodsVo.getId())){
                                    innerGoodsVo.setIdx(floorGoods.getIdx());
                                    comlex.getListVO().getGoodsInfoList().add(innerGoodsVo);
                                }
                            }
                        }
                        comlex.getListVO().getGoodsInfoList().sort(Comparator.comparing(BbcGoodsInfoVO.HomeAndShopInnerServiceVO::getIdx));
                    }
                }
            }
        }
        List<BbcSiteFloorVO.ListVO> voList = new ArrayList<>();
        for (SiteFloorGoodsComlex comlex : voMap.values()) {
            voList.add(comlex.getListVO());
        }
        return voList;
    }



    @Override
    public List<BbcSiteFloorVO.List2VO> list2(BbcSiteFloorQTO.QTO qto) {
        QueryWrapper<SiteFloor> wrapper =  MybatisPlusUtil.query();
        wrapper.eq("terminal", TerminalEnum.BBC.getCode());
        wrapper.eq("subject",qto.getSubject());
        wrapper.eq("pc_show", PcH5Enum.NO.getCode());
        wrapper.orderByAsc("idx");
        List<SiteFloor> floorList = repository.list(wrapper);
        return ListUtil.listCover(BbcSiteFloorVO.List2VO.class,floorList);
    }

    @Override
    public List<BbcGoodsInfoVO.HomeAndShopInnerServiceVO> ListGoodsMax(BbcSiteFloorQTO.GoodsMaxQTO dto) {

        if(StringUtils.isBlank(dto.getFloorId())){
            throw new BusinessException("楼层ID不能为空");
        }
        SiteFloor siteFloor =  repository.getById(dto.getFloorId());
        if(null == siteFloor){
            return  null;
        }
       IPage<SiteFloorGoods> pager =  new Page<>(1, siteFloor.getGoodsMax());
        QueryWrapper<SiteFloorGoods> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("floor_id",dto.getFloorId());
        siteFloorGoodsRepository.page(pager,queryWrapper);
        if(ObjectUtils.isEmpty(pager.getRecords())){
            return new ArrayList<>();
        }
        List<String> goodsIdList = new ArrayList<>();
        for(SiteFloorGoods siteFloorGoodsItem:pager.getRecords()){
            goodsIdList.add(siteFloorGoodsItem.getGoodsId());
        }
        BbcGoodsInfoQTO.GoodsIdListQTO goodsIdListQTO  = new BbcGoodsInfoQTO.GoodsIdListQTO();
        goodsIdListQTO.setIdList(goodsIdList);
        List< BbcGoodsInfoVO.HomeAndShopInnerServiceVO> innerGoodsVoList = bbcGoodsInfoRpc.getInnerServiceVO(goodsIdListQTO);
        return innerGoodsVoList;
    }

    @Override
    public PageData<BbcGoodsInfoVO.HomeAndShopInnerServiceVO> goodsMore(BbcSiteFloorQTO.GoodsMoreQTO qto) {
        if(StringUtils.isBlank(qto.getFloorId())){
            throw new BusinessException("楼层ID不能为空");
        }
        IPage<SiteFloorGoods> page = MybatisPlusUtil.pager(qto);
        QueryWrapper<SiteFloorGoods> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("floor_id",qto.getFloorId());
        siteFloorGoodsRepository.page(page,queryWrapper);
        if(ObjectUtils.isEmpty(page.getRecords())){
            return MybatisPlusUtil.toPageData(new ArrayList<>(),qto.getPageNum(),qto.getPageSize(),page.getTotal());
        }
        List<String> goodsIdList = new ArrayList<>();
        for(SiteFloorGoods siteFloorGoodsItem:page.getRecords()){
            if(!goodsIdList.contains(siteFloorGoodsItem.getGoodsId())){
                goodsIdList.add(siteFloorGoodsItem.getGoodsId());
            }
        }
        BbcGoodsInfoQTO.GoodsIdListQTO goodsIdListQTO  = new BbcGoodsInfoQTO.GoodsIdListQTO();
        goodsIdListQTO.setIdList(goodsIdList);
        List< BbcGoodsInfoVO.HomeAndShopInnerServiceVO> innerGoodsVoList = bbcGoodsInfoRpc.getInnerServiceVO(goodsIdListQTO);
        return MybatisPlusUtil.toPageData(innerGoodsVoList,qto.getPageNum(),qto.getPageSize(),page.getTotal());
    }
}
