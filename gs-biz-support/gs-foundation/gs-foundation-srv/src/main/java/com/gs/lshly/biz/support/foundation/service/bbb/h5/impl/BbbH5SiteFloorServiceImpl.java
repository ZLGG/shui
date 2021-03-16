package com.gs.lshly.biz.support.foundation.service.bbb.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.foundation.complex.SiteFloorGoodsComlex;
import com.gs.lshly.biz.support.foundation.complex.bbb.h5.BbbH5SiteFloorGoodsComlex;
import com.gs.lshly.biz.support.foundation.entity.SiteFloor;
import com.gs.lshly.biz.support.foundation.entity.SiteFloorGoods;
import com.gs.lshly.biz.support.foundation.repository.ISiteFloorGoodsRepository;
import com.gs.lshly.biz.support.foundation.repository.ISiteFloorRepository;
import com.gs.lshly.biz.support.foundation.service.bbb.h5.IBbbH5SiteFloorService;
import com.gs.lshly.common.enums.PcH5Enum;
import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.qto.BbbH5GoodsInfoQTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5SiteFloorQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteFloorVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbb.h5.commodity.IBbbH5GoodsInfoRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-11-02
*/
@Component
public class BbbH5SiteFloorServiceImpl implements IBbbH5SiteFloorService {

    @Autowired
    private ISiteFloorRepository repository;

    @Autowired
    private ISiteFloorGoodsRepository siteFloorGoodsRepository;

    @DubboReference
    private IBbbH5GoodsInfoRpc bbbH5GoodsInfoRpc;

    @Override
    public List<BbbH5SiteFloorVO.ListVO> list(BbbH5SiteFloorQTO.QTO qto) {
        QueryWrapper<SiteFloor> wrapper =  MybatisPlusUtil.query();
        wrapper.eq("terminal", TerminalEnum.BBB.getCode());
        wrapper.eq("subject", SubjectEnum.默认.getCode());
        wrapper.eq("pc_show", PcH5Enum.NO.getCode());
        wrapper.orderByAsc("idx");
        List<SiteFloor> floorList = repository.list(wrapper);
        List<String> floorIdList = new ArrayList<>();
        Map<String, BbbH5SiteFloorGoodsComlex> voMap = new HashMap<>();
        for(SiteFloor floorItem:floorList){
            floorIdList.add(floorItem.getId());
            BbbH5SiteFloorVO.ListVO listVO = new BbbH5SiteFloorVO.ListVO();
            BeanUtils.copyProperties(floorItem,listVO);
            BbbH5SiteFloorGoodsComlex siteFloorGoodsComlex = new BbbH5SiteFloorGoodsComlex();
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
                BbbH5SiteFloorGoodsComlex siteFloorGoodsComlex = voMap.get(siteFloorGoodsItem.getFloorId());
                siteFloorGoodsComlex.getFloorGoodsList().add(siteFloorGoodsItem);
            }
            if(ObjectUtils.isNotEmpty(goodsIdList)){
                BbbH5GoodsInfoQTO.GoodsIdListQTO goodsIdListQTO  = new BbbH5GoodsInfoQTO.GoodsIdListQTO();
                goodsIdListQTO.setIdList(goodsIdList);
            }
        }
        List<BbbH5SiteFloorVO.ListVO> voList = new ArrayList<>();
        for (BbbH5SiteFloorGoodsComlex comlex : voMap.values()) {
            voList.add(comlex.getListVO());
        }
        return voList;
    }

    @Override
    public PageData<BbbH5SiteFloorVO.GoodsListVO> goodsList(BbbH5SiteFloorQTO.GoodsListQTO qto) {
        QueryWrapper<SiteFloorGoods> siteFloorGoodsQueryWrapper = MybatisPlusUtil.query();
        siteFloorGoodsQueryWrapper.eq("floor_id",qto.getFloorId());
        siteFloorGoodsQueryWrapper.orderByAsc("idx");
        IPage<SiteFloorGoods> pager = MybatisPlusUtil.pager(qto);
        siteFloorGoodsRepository.page(pager,siteFloorGoodsQueryWrapper);
        List<String> goodsIdLst  = ListUtil.getIdList(SiteFloorGoods.class,pager.getRecords(),"goodsId");
        PageData<BbbH5SiteFloorVO.GoodsListVO> pageData = MybatisPlusUtil.toPageData(qto,BbbH5SiteFloorVO.GoodsListVO.class,pager);
        if(ObjectUtils.isNotEmpty(goodsIdLst)){
            List<BbbH5GoodsInfoVO.HomeInnerServiceVO> innerGoodsList = bbbH5GoodsInfoRpc.getHomeGoodsInnerServiceVO(goodsIdLst,new BaseDTO());
            if(ObjectUtils.isNotEmpty(innerGoodsList)){
                for(BbbH5SiteFloorVO.GoodsListVO goodsListVo:pageData.getContent()){
                    for(BbbH5GoodsInfoVO.HomeInnerServiceVO innerGoodsVo:innerGoodsList){
                        if(goodsListVo.getGoodsId().equals(innerGoodsVo.getGoodsId())){
                            goodsListVo.setGoodsInfo(innerGoodsVo);
                        }
                    }
                }
            }
        }
        return pageData;
    }
}
