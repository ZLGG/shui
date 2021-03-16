package com.gs.lshly.biz.support.foundation.service.bbb.pc.impl;

import cn.hutool.core.lang.Console;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.foundation.entity.*;
import com.gs.lshly.biz.support.foundation.enums.SiteFloorMenuTypeEnum;
import com.gs.lshly.biz.support.foundation.repository.*;
import com.gs.lshly.biz.support.foundation.service.bbb.pc.IPCBbbFloorService;
import com.gs.lshly.common.enums.*;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsInfoVO;
import com.gs.lshly.common.struct.bbb.pc.pages.qto.PCBbbHomeQTO;
import com.gs.lshly.common.struct.bbb.pc.pages.vo.PCBbbHomeVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbb.pc.commodity.IPCBbbGoodsInfoRpc;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import java.util.*;

/**
* <p>
*  服务实现类
* </p>
* @author 陈奇
* @since 2020-10-14
*/
@Component
@Slf4j
public class PCBbbFloorServiceImpl implements IPCBbbFloorService {

    @Autowired
    private ISiteBannerRepository siteBannerRepository;

    @Autowired
    private ISiteAdvertRepository siteAdvertRepositorys;

    @Autowired
    private ISiteFloorRepository siteFloorRepository;

    @Autowired
    private ISiteFloorMenuGoodsRepository siteFloorMenuGoodsRepository;

    @Autowired
    private ISiteFloorMenuRepository siteFloorMenuRepository;

    @DubboReference
    private IPCBbbGoodsInfoRpc pcBbbGoodsInfoRpc;

    @Override
    public PCBbbHomeVO.FloorOrAdvertVO pageList(PCBbbHomeQTO.QTO qto) {
        PCBbbHomeVO.FloorOrAdvertVO floorOrAdvertVO  =  new PCBbbHomeVO.FloorOrAdvertVO();
        floorOrAdvertVO.setFloorList(this.homeFloorQuery(qto));
        this.homeAdvertQuery(floorOrAdvertVO,qto);
        return floorOrAdvertVO;
    }

    private  void homeAdvertQuery(PCBbbHomeVO.FloorOrAdvertVO floorOrAdvertVO,PCBbbHomeQTO.QTO qto){
        QueryWrapper<SiteAdvert> siteAdvertQueryWrapper = MybatisPlusUtil.query();
        siteAdvertQueryWrapper.eq("terminal", TerminalEnum.BBB.getCode());
        siteAdvertQueryWrapper.eq("pc_show", PcH5Enum.YES.getCode());
        siteAdvertQueryWrapper.eq("subject", qto.getSubject());
        siteAdvertQueryWrapper.eq("is_category", TrueFalseEnum.否.getCode());
        List<SiteAdvert> siteAdverts =  siteAdvertRepositorys.list(siteAdvertQueryWrapper);
        if(ObjectUtils.isNotEmpty(siteAdverts)){
            for(SiteAdvert siteAdvert:siteAdverts){
                if(AdvertTypeEnum.组合广告.getCode().equals(siteAdvert.getAdvertType())){
                    PCBbbHomeVO.PCAdvertVO advertVO = new PCBbbHomeVO.PCAdvertVO();
                    BeanUtils.copyProperties(siteAdvert,advertVO);
                    floorOrAdvertVO.getGroupAdvertList().add(advertVO);
                }else if(AdvertTypeEnum.单张广告.getCode().equals(siteAdvert.getAdvertType())){
                    PCBbbHomeVO.PCAdvertVO advertVO = new PCBbbHomeVO.PCAdvertVO();
                    BeanUtils.copyProperties(siteAdvert,advertVO);
                    floorOrAdvertVO.setAdvert(advertVO);
                }
            }
        }
    }

    private List<PCBbbHomeVO.PCFloorVO> homeFloorQuery(PCBbbHomeQTO.QTO qto){
        QueryWrapper<SiteFloor> floorQueryWrapper =  MybatisPlusUtil.query();
        floorQueryWrapper.eq("terminal", TerminalEnum.BBB.getCode());
        floorQueryWrapper.eq("subject", qto.getSubject());
        floorQueryWrapper.eq("pc_show", PcH5Enum.YES.getCode());
        floorQueryWrapper.orderByAsc("idx");
        List<SiteFloor> floorList = siteFloorRepository.list(floorQueryWrapper);
        List<String> floorIdList = new ArrayList<>();
        List<PCBbbHomeVO.PCFloorVO> voList = new ArrayList<>();
        //楼层查询
        for(SiteFloor siteFloor:floorList){
            PCBbbHomeVO.PCFloorVO floorVO = new PCBbbHomeVO.PCFloorVO();
            BeanUtils.copyProperties(siteFloor,floorVO);
            voList.add(floorVO);
            floorIdList.add(siteFloor.getId());
        }
        if(ObjectUtils.isNotEmpty(floorIdList)){
            //楼层菜单查询
            QueryWrapper<SiteFloorMenu> floorMenuQueryWrapper =  MybatisPlusUtil.query();
            floorMenuQueryWrapper.in("floor_id",floorIdList);
            floorMenuQueryWrapper.orderByAsc("idx");
            List<SiteFloorMenu> floorMenuList = siteFloorMenuRepository.list(floorMenuQueryWrapper);
            if(ObjectUtils.isNotEmpty(floorMenuList)){
                for(SiteFloorMenu floorMenu :floorMenuList){
                    if(SiteFloorMenuTypeEnum.楼层顶部.getCode().equals(floorMenu.getMenuType())){
                        PCBbbHomeVO.PCFloorMenu floorMenuVo = new PCBbbHomeVO.PCFloorMenu();
                        BeanUtils.copyProperties(floorMenu,floorMenuVo);
                        for(PCBbbHomeVO.PCFloorVO floorVO:voList){
                            if(floorVO.getId().equals(floorMenu.getFloorId())){
                                floorVO.getFloorMenuList().add(floorMenuVo);
                            }
                        }
                    }else if(SiteFloorMenuTypeEnum.左侧链接.getCode().equals(floorMenu.getMenuType())){
                        PCBbbHomeVO.PCFloorLink floorLinkVo = new PCBbbHomeVO.PCFloorLink();
                        BeanUtils.copyProperties(floorMenu,floorLinkVo);
                        for(PCBbbHomeVO.PCFloorVO floorVO:voList){
                            if(floorVO.getId().equals(floorMenu.getFloorId())){
                                floorVO.getFloorLinkList().add(floorLinkVo);
                            }
                        }
                    }
                }
                //每个楼层的第一个楼层菜单ID数组
                List<String> firstFloorMenuIdList = new ArrayList<>();
                //排序楼层的菜单
                for(PCBbbHomeVO.PCFloorVO floorVo:voList){
                    if(ObjectUtils.isNotEmpty( floorVo.getFloorMenuList())){
                        floorVo.getFloorMenuList().sort(Comparator.comparing(PCBbbHomeVO.PCFloorMenu::getIdx));
                        firstFloorMenuIdList.add(floorVo.getFloorMenuList().get(0).getId());
                    }
                }
                if(ObjectUtils.isNotEmpty(firstFloorMenuIdList)){
                    QueryWrapper<SiteFloorMenuGoods> menuGoodsQueryWrapper =  MybatisPlusUtil.query();
                    menuGoodsQueryWrapper.in("floor_menu_id",firstFloorMenuIdList);
                    List<SiteFloorMenuGoods> menuGoodsList = siteFloorMenuGoodsRepository.list(menuGoodsQueryWrapper);
                    //要取信息的商品数组
                    List<String> goodsIdList = new ArrayList<>();
                    for(SiteFloorMenuGoods menuGoods:menuGoodsList){
                        PCBbbHomeVO.PCFloorMenuGoods menuGoodsVo = new PCBbbHomeVO.PCFloorMenuGoods();
                        BeanUtils.copyProperties(menuGoods,menuGoodsVo);
                        if(!goodsIdList.contains(menuGoods.getGoodsId())){
                            goodsIdList.add(menuGoods.getGoodsId());
                        }
                        for(PCBbbHomeVO.PCFloorVO floorVO:voList){
                            for(PCBbbHomeVO.PCFloorMenu floorMenu:floorVO.getFloorMenuList()){
                                if(menuGoods.getFloorMenuId().equals(floorMenu.getId())){
                                    floorMenu.getMenuGoodsList().add(menuGoodsVo);
                                }
                            }
                        }
                }
                    //商品信息
                    if(ObjectUtils.isNotEmpty(goodsIdList)){
                        List<PCBbbGoodsInfoVO.HomeInnerServiceVO> innerGoodsList = pcBbbGoodsInfoRpc.getHomeGoodsInnerServiceVO(goodsIdList,
                                new BaseDTO().setJwtUserId(qto.getJwtUserId()).setJwtUserType(qto.getJwtUserType()));
                        if(ObjectUtils.isNotEmpty(innerGoodsList)){
                            for(PCBbbHomeVO.PCFloorVO floorVO:voList){
                                for(PCBbbHomeVO.PCFloorMenu floorMenu:floorVO.getFloorMenuList()){
                                    for(PCBbbHomeVO.PCFloorMenuGoods menuGoodsVO:floorMenu.getMenuGoodsList()){
                                        for(PCBbbGoodsInfoVO.HomeInnerServiceVO innerGoodsItem:innerGoodsList){
                                            if(menuGoodsVO.getGoodsId().equals(innerGoodsItem.getId())){
                                                BeanUtils.copyProperties(innerGoodsItem,menuGoodsVO);
                                            }
                                        }
                                    }
                                    for(int i=0;i<floorMenu.getMenuGoodsList().size();i++){
                                        PCBbbHomeVO.PCFloorMenuGoods menuGoodsVO =  floorMenu.getMenuGoodsList().get(i);
                                        if(StringUtils.isBlank(menuGoodsVO.getGoodsName())){
                                           floorMenu.getMenuGoodsList().remove(i);
                                       }
                                    }
                                }
                            }
                        }
                    }
                }
                for(PCBbbHomeVO.PCFloorVO floorVo:voList){
                    floorVo.getFloorLinkList().sort(Comparator.comparing(PCBbbHomeVO.PCFloorLink::getIdx));
                }
                return voList;
            }
        }
        return new ArrayList<>();
    }

    @Override
    public List<PCBbbHomeVO.PCFloorMenuGoods> floorMenuGoodsQuery(PCBbbHomeQTO.MenuGoodsQTO qto) {
        if(StringUtils.isBlank(qto.getFloorMenuId())){
            return new ArrayList<>();
        }
        QueryWrapper<SiteFloorMenuGoods> siteFloorMenuGoodsQueryWrapper = MybatisPlusUtil.query();
        siteFloorMenuGoodsQueryWrapper.eq("floor_menu_id",qto.getFloorMenuId());
        siteFloorMenuGoodsQueryWrapper.orderByAsc("idx");
        List<PCBbbHomeVO.PCFloorMenuGoods> checkSelectList = new ArrayList<>();
        List<SiteFloorMenuGoods> menuGoodsList = siteFloorMenuGoodsRepository.list(siteFloorMenuGoodsQueryWrapper);
        if(ObjectUtils.isEmpty(menuGoodsList)){
            return checkSelectList;
        }
        List<PCBbbHomeVO.PCFloorMenuGoods> floorMenuGoodsList = new ArrayList<>();
        List<String> goodsIdList = new ArrayList<>();
        for(SiteFloorMenuGoods menuGoods:menuGoodsList){
            PCBbbHomeVO.PCFloorMenuGoods floorMenuGoodsVo = new PCBbbHomeVO.PCFloorMenuGoods();
            floorMenuGoodsVo.setGoodsId(menuGoods.getGoodsId());
            floorMenuGoodsVo.setIdx(menuGoods.getIdx());
            floorMenuGoodsList.add(floorMenuGoodsVo);
            if(!goodsIdList.contains(menuGoods.getGoodsId())){
                goodsIdList.add(menuGoods.getGoodsId());
            }
        }
        List<PCBbbGoodsInfoVO.HomeInnerServiceVO> innerGoodsList = pcBbbGoodsInfoRpc.getHomeGoodsInnerServiceVO(goodsIdList,
                new BaseDTO().setJwtUserId(qto.getJwtUserId()).setJwtUserType(qto.getJwtUserType()));
        if(ObjectUtils.isNotEmpty(innerGoodsList)){
            for(PCBbbHomeVO.PCFloorMenuGoods menuGoodsVO:floorMenuGoodsList){
                for(PCBbbGoodsInfoVO.HomeInnerServiceVO innerGoodsItem:innerGoodsList){
                    if(menuGoodsVO.getGoodsId().equals(innerGoodsItem.getId())){
                        BeanUtils.copyProperties(innerGoodsItem,menuGoodsVO);
                        checkSelectList.add(menuGoodsVO);
                    }
                }
            }
        }
        return checkSelectList;
    }

}
