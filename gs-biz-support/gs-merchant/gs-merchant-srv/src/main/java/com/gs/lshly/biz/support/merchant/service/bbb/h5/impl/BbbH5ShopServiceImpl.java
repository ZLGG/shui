package com.gs.lshly.biz.support.merchant.service.bbb.h5.impl;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.merchant.entity.*;
import com.gs.lshly.biz.support.merchant.enums.MerchShopNavigationEnum;
import com.gs.lshly.biz.support.merchant.repository.*;
import com.gs.lshly.biz.support.merchant.service.bbb.h5.IBbbH5ShopService;
import com.gs.lshly.common.enums.ShopNavigationMenuTypeEnum;
import com.gs.lshly.common.enums.SitePCShowEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.enums.merchant.ShopStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.merchant.dto.BbbH5ShopDTO;
import com.gs.lshly.common.struct.bbb.h5.merchant.qto.BbbH5ShopQTO;
import com.gs.lshly.common.struct.bbb.h5.merchant.vo.*;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopVO;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopDTO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.common.utils.QQMapUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbb.h5.commodity.IBbbH5GoodsInfoRpc;
import com.gs.lshly.rpc.api.bbb.h5.user.IBbbH5UserFavoritesShopRpc;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-13
*/
@Component
@Slf4j
public class BbbH5ShopServiceImpl implements IBbbH5ShopService {

    @Autowired
    private IShopRepository repository;
    @Autowired
    private IShopBannerRepository shopBannerRepository;
    @Autowired
    private IShopNavigationMenuRepository shopNavigationMenuRepository;
    @Autowired
    private IShopNavigationRepository shopNavigationRepository;
    @Autowired
    private IShopFloorRepository shopFloorRepository;
    @Autowired
    private IShopFloorGoodsRepository shopFloorGoodsRepository;
    @Autowired
    private IShopDeliveryStyleRepository shopDeliveryStyleRepository;
    @DubboReference
    private IBbbH5GoodsInfoRpc bbbGoodsInfoRpc;
    @DubboReference
    private IBbbH5UserFavoritesShopRpc bbcUserFavoritesShopRpc;


    @Override
    public PageData<BbbH5ShopVO.ListVO> pageData(BbbH5ShopQTO.QTO qto) {
        QueryWrapper<Shop> queryWrapper = MybatisPlusUtil.query();
        if(StringUtils.isNotBlank(qto.getShopName())) {
            queryWrapper.like("shop_name", qto.getShopName());
        }
        queryWrapper.eq("shop_state", ShopStateEnum.开启状态.getCode());
        queryWrapper.orderByDesc("cdate");
        IPage<Shop> page = MybatisPlusUtil.pager(qto);
        repository.page(page, queryWrapper);
        return MybatisPlusUtil.toPageData(qto, BbbH5ShopVO.ListVO.class, page);
    }

    @Override
    public BbbH5ShopVO.DetailVO detailShop(BbbH5ShopDTO.IdDTO dto) {
        Shop shop = repository.getById(dto.getId());
        BbbH5ShopVO.DetailVO detailVo = new BbbH5ShopVO.DetailVO();
        if(ObjectUtils.isEmpty(shop)){
            throw new BusinessException("ID无效");
        }
        BeanCopyUtils.copyProperties(shop, detailVo);
        return detailVo;
    }

    @Override
    public BbbH5ShopVO.ComplexVO shopComplex(BbbH5ShopDTO.IdDTO dto) {
        QueryWrapper<Shop> shopQueryWrapper = MybatisPlusUtil.query();
        shopQueryWrapper.eq("id",dto.getId());
        Shop shop =  repository.getOne(shopQueryWrapper);
        if(null == shop){
            throw new BusinessException("店铺不存在");
        }
        BbbH5ShopVO.ComplexVO complexVO = new BbbH5ShopVO.ComplexVO();
        BeanCopyUtils.copyProperties(shop,complexVO);
        if(StringUtils.isNotBlank(dto.getJwtUserId())){
            Integer favoriteState  = bbcUserFavoritesShopRpc.innerFavoritesState(dto,dto.getId(),dto.getJwtUserId());
            if(null != favoriteState){
                complexVO.setFavoriteState(favoriteState);
            }
        }
        //店铺轮播图
        QueryWrapper<ShopBanner> bannerQueryWrapper = MybatisPlusUtil.query();
        bannerQueryWrapper.eq("shop_id",dto.getId());
        bannerQueryWrapper.eq("terminal", TerminalEnum.BBB.getCode());
        List<ShopBanner> shopBannerList = shopBannerRepository.list(bannerQueryWrapper);
        if(ObjectUtils.isNotEmpty(shopBannerList)){
            List<BbbH5ShopBannerVO.ListVO> bannerList = ListUtil.listCover(BbbH5ShopBannerVO.ListVO.class,shopBannerList);
            complexVO.setBannerList(bannerList);
        }
        //店铺导航
        QueryWrapper<ShopNavigationMenu> menuQueryWrapper = MybatisPlusUtil.query();
        menuQueryWrapper.eq("shop_id",dto.getId());
        menuQueryWrapper.eq("terminal", TerminalEnum.BBB.getCode());
        List<ShopNavigationMenu> menuList=  shopNavigationMenuRepository.list(menuQueryWrapper);
        if(ObjectUtils.isNotEmpty(menuList)){
            for(ShopNavigationMenu menuItem:menuList){
                if(ShopNavigationMenuTypeEnum.自定义文本.getCode().equals(menuItem.getMenuType())){
                    BbbH5ShopNavigationMenuVO.TextMenuVO textMenu  = new BbbH5ShopNavigationMenuVO.TextMenuVO();
                    BeanCopyUtils.copyProperties(menuItem,textMenu);
                    complexVO.getTextMenuList().add(textMenu);
                }else if(ShopNavigationMenuTypeEnum.自定义图文.getCode().equals(menuItem.getMenuType())){
                    BbbH5ShopNavigationMenuVO.MenuListVO imageMenu  = new BbbH5ShopNavigationMenuVO.MenuListVO();
                    BeanCopyUtils.copyProperties(menuItem,imageMenu);
                    complexVO.getMenuList().add(imageMenu);
                }
            }
        }
        //店铺楼层
        QueryWrapper<ShopFloor> floorQueryWrapper = MybatisPlusUtil.query();
        floorQueryWrapper.eq("shop_id",dto.getId());
        floorQueryWrapper.eq("pc_show", SitePCShowEnum.不显示.getCode());
        floorQueryWrapper.eq("terminal", TerminalEnum.BBB.getCode());
        List<ShopFloor> shopFloorList = shopFloorRepository.list(floorQueryWrapper);
        if(ObjectUtils.isNotEmpty(shopFloorList)){
            complexVO.setFloorList( ListUtil.listCover(BbbH5ShopFloorVO.ListVO.class,shopFloorList));
        }
        return complexVO;
    }

    @Override
    public  PageData<BbbH5GoodsInfoVO.HomeInnerServiceVO> shopFloorGoods(BbbH5ShopQTO.FloorGoodsQTO qto) {
        QueryWrapper<ShopFloorGoods> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("shop_floor_id",qto.getFloorId());
        IPage<ShopFloorGoods> pager = MybatisPlusUtil.pager(qto);
        shopFloorGoodsRepository.page(pager,queryWrapper);
        List<String> goodsIdList = ListUtil.getIdList(ShopFloorGoods.class,pager.getRecords(),"goodsId");
        if(ObjectUtil.isEmpty(goodsIdList)){
            PageData<BbbH5GoodsInfoVO.HomeInnerServiceVO> pageData = MybatisPlusUtil.toPageData(new ArrayList<>(),qto.getPageNum(),qto.getPageSize(),pager.getTotal());
            return pageData;
        }
        List<BbbH5GoodsInfoVO.HomeInnerServiceVO> innerGoodsList = bbbGoodsInfoRpc.getHomeGoodsInnerServiceVO(goodsIdList,new BaseDTO());
        PageData<BbbH5GoodsInfoVO.HomeInnerServiceVO> pageData = MybatisPlusUtil.toPageData(innerGoodsList,qto.getPageNum(),qto.getPageSize(),pager.getTotal());
        return pageData;
    }

    @Override
    public BbbH5ShopVO.InnerDetailVO innerDetailShop(BbbH5ShopQTO.InnerShopQTO qto) {
        if(StringUtils.isBlank(qto.getShopId())){
            return null;
        }
        QueryWrapper<Shop> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("id",qto.getShopId());
        Shop shop = repository.getOne(queryWrapper);
        if(ObjectUtils.isEmpty(shop)){
            return null;
        }
        BbbH5ShopVO.InnerDetailVO detailVo = new BbbH5ShopVO.InnerDetailVO();
        BeanCopyUtils.copyProperties(shop, detailVo);
        return detailVo;
    }

    @Override
    public List<BbbH5ShopVO.InnerDetailVO> innerListDetailShop(BbbH5ShopQTO.InnerListShopQTO qto) {
        if(ObjectUtils.isEmpty(qto.getShopIdList())){
            return new ArrayList<>();
        }
        List<Shop> shopList = repository.listByIds(qto.getShopIdList());
        if(ObjectUtils.isEmpty(shopList)){
            return new ArrayList<>();
        }
        return  ListUtil.listCover(BbbH5ShopVO.InnerDetailVO.class,shopList);
    }

    @Override
    public List<BbbH5ShopVO.ShopNavigationIdName> innerListShopNavigation(ShopDTO.IdDTO dto) {
        QueryWrapper<ShopNavigation> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("shop_id",dto.getId());
        queryWrapper.eq("terminal",TerminalEnum.BBB.getCode());
        List<ShopNavigation> shopNavigationList = shopNavigationRepository.list(queryWrapper);
        Map<String,BbbH5ShopVO.ShopNavigationIdName> utilMap = new HashMap<>();
        for(ShopNavigation shopNavigation:shopNavigationList){
            if(shopNavigation.getLeve().equals(MerchShopNavigationEnum.一级分类.getCode())){
                BbbH5ShopVO.ShopNavigationIdName shopNavigationIdName =  utilMap.get(shopNavigation.getId());
                if(null == shopNavigationIdName){
                    shopNavigationIdName = new BbbH5ShopVO.ShopNavigationIdName();
                    shopNavigationIdName.setId(shopNavigation.getId());
                    shopNavigationIdName.setNavName(shopNavigation.getNavName());
                    utilMap.put(shopNavigation.getId(),shopNavigationIdName);
                }
            }
        }
        for(ShopNavigation item:shopNavigationList){
            if(MerchShopNavigationEnum.二级分类.getCode().equals(item.getLeve())){
                BbbH5ShopVO.ShopNavigationIdName shopNavigationIdName =  utilMap.get(item.getParentId());
                if(ObjectUtils.isNull(shopNavigationIdName)){
                    throw new BusinessException("二级分类的父ID无效");
                }
                BbbH5ShopVO.ShopNavigationChildIdName childIdName = new BbbH5ShopVO.ShopNavigationChildIdName();
                childIdName.setId(item.getId());
                childIdName.setNavName(item.getNavName());
                shopNavigationIdName.getNavigationList().add(childIdName);
            }
        }
        return new ArrayList<>(utilMap.values());
    }

    @Override
    public BbcShopVO.isCity SisCity(BbbH5ShopDTO.isCity dto) {
        Shop shop = repository.getById(dto.getId());
        BbcShopVO.isCity isCity = new BbcShopVO.isCity();
        if (ObjectUtils.isNotEmpty(shop)){
            if (ObjectUtils.isNotEmpty(shop.getShopLatitude())&&ObjectUtils.isNotEmpty(shop.getShopLongitude())){
                String city = QQMapUtil.getLatAndLng(dto.getLat(), dto.getLng()).getString("city");
                String shopCity=QQMapUtil.getLatAndLng(shop.getShopLatitude(), shop.getShopLongitude()).getString("city");
                if (city.equals(shopCity)){
                    isCity.setIsCity(10);
                }else {
                    isCity.setIsCity(20);
                }
                if (ObjectUtils.isNotEmpty(dto.getLat())&& ObjectUtils.isNotEmpty(dto.getLng())){
                    double distance = getDistance(dto.getLat().doubleValue(), dto.getLng().doubleValue(), shop.getShopLatitude().doubleValue(), shop.getShopLongitude().doubleValue());
                    isCity.setDistance(distance);
                }
            }else {
                    throw new BusinessException("商家没有提供经度或纬度");
                }
        }
        return isCity;
    }

    @Override
    public List<String> innerGetShopIdList(String shopName) {
        QueryWrapper<Shop> wrapper = MybatisPlusUtil.query();
        if (StringUtils.isNotBlank(shopName)){
            wrapper.like("shop_name",shopName);
        }
        wrapper.select("id","shop_name");
        List<Shop> shops = repository.list(wrapper);
        if (ObjectUtils.isNotEmpty(shops)){
            List<String>  shopIdList = ListUtil.getIdList(Shop.class,shops);
            return shopIdList;
        }
        return new ArrayList<>();
    }

    @Override
    public List<BbbH5ShopNavigationVO.NavigationListVO> listNavigationListVO(BbbH5ShopDTO.IdDTO dto) {
        if (null == dto || StringUtils.isBlank(dto.getId())){
            throw new BusinessException("店铺id不能为空！");
        }
        QueryWrapper<ShopNavigation> wrapper = MybatisPlusUtil.query();
        wrapper.eq("terminal",TerminalEnum.BBC.getCode());
        wrapper.eq("shop_id",dto.getId());
        wrapper.orderByAsc("idx","id");
        List<ShopNavigation> dataList =  shopNavigationRepository.list(wrapper);
        Map<String, BbbH5ShopNavigationVO.NavigationListVO> utilMap = new HashMap<>();
        for(ShopNavigation item:dataList){
            if(MerchShopNavigationEnum.一级分类.getCode().equals(item.getLeve())){
                BbbH5ShopNavigationVO.NavigationListVO parentItem =  utilMap.get(item.getId());
                if(ObjectUtils.isNull(parentItem)){
                    parentItem = new BbbH5ShopNavigationVO.NavigationListVO();
                    parentItem.setChildList(new ArrayList<>());
                    utilMap.put(item.getId(),parentItem);
                }
                BeanUtils.copyProperties(item,parentItem);
            }
        }

        for(ShopNavigation item:dataList){
            if(MerchShopNavigationEnum.二级分类.getCode().equals(item.getLeve())){
                BbbH5ShopNavigationVO.NavigationListVO parentItem =  utilMap.get(item.getParentId());
                if(ObjectUtils.isNull(parentItem)){
                    Console.log("二级分类:{},{},{}",item.getId(),item.getNavName(),item.getParentId());
                    throw new BusinessException("二级分类的父ID无效");
                }
                BbbH5ShopNavigationVO.NavigationChildVO childItem = new BbbH5ShopNavigationVO.NavigationChildVO();
                BeanUtils.copyProperties(item,childItem);
                parentItem.getChildList().add(childItem);
            }
        }
        List<BbbH5ShopNavigationVO.NavigationListVO> voList = new ArrayList<>(utilMap.values());
        voList.sort((a, b) -> a.getIdx() - b.getIdx());
        for(BbbH5ShopNavigationVO.NavigationListVO listItem:voList){
            listItem.getChildList().sort((a,b)-> a.getIdx() - b.getIdx());
        }
        return voList;
    }

    @Override
    public List<String> innerGetNavigationList(String shopNavigationId) {
        QueryWrapper<ShopNavigation> wrapper = MybatisPlusUtil.query();
        wrapper.eq("id",shopNavigationId);
        ShopNavigation shopNavigation = shopNavigationRepository.getOne(wrapper);
        if (ObjectUtils.isEmpty(shopNavigation)){
            throw new BusinessException("店铺类目不存在！");
        }
        List<String> navigationIdList = new ArrayList<>();
        if (shopNavigation.getLeve().equals(MerchShopNavigationEnum.一级分类.getCode())){
            QueryWrapper<ShopNavigation> navigationQueryWrapper = MybatisPlusUtil.query();
            navigationQueryWrapper.eq("parent_id",shopNavigationId);
            List<ShopNavigation> shopNavigations = shopNavigationRepository.list(navigationQueryWrapper);
            if (ObjectUtils.isNotEmpty(shopNavigations)){
                List<String> list = ListUtil.getIdList(ShopNavigation.class,shopNavigations);
                navigationIdList.addAll(list);
            }
        }
        if (shopNavigation.getLeve().equals(MerchShopNavigationEnum.二级分类.getCode())){
            navigationIdList.add(shopNavigationId);
        }
        return navigationIdList;
    }

    @Override
    public BbbH5ShopVO.ShopIdName getShopIdName(BbbH5ShopDTO.ShopNavigationIdDTO dto) {
        if (null == dto || StringUtils.isBlank(dto.getShopNavigationId())){
            throw new BusinessException("参数为空，异常！！");
        }
        BbbH5ShopVO.ShopIdName shopIdName = new BbbH5ShopVO.ShopIdName();
        ShopNavigation shopNavigation = shopNavigationRepository.getById(dto.getShopNavigationId());
        if (null != shopNavigation){
            shopIdName.setId(StringUtils.isBlank(shopNavigation.getShopId())?"":shopNavigation.getShopId());
        }
        return shopIdName;
    }


    @Override
    public List<BbbH5ShopVO.ShopIdName> innerListShopIdName(BbbH5ShopDTO.MerchantIdDTO dto) {
        QueryWrapper<Shop> wrapper = MybatisPlusUtil.query();
        wrapper.eq("merchant_id",dto.getId());
        List<Shop> dataList = repository.list(wrapper);
        return ListUtil.listCover( BbbH5ShopVO.ShopIdName.class,dataList);
    }

    private static double EARTH_RADIUS = 6378.137;// 单位千米

    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = getRadian(lat1);
        double radLat2 = getRadian(lat2);
        double a = radLat1 - radLat2;// 两点纬度差
        double b = getRadian(lng1) - getRadian(lng2);// 两点的经度差
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1)
                * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        return s * 1000;
    }
    private static double getRadian(double degree) {
        return degree * Math.PI / 180.0;
    }
}
