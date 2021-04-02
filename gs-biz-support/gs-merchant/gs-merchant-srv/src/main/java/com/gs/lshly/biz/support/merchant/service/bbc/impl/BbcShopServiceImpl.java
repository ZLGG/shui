package com.gs.lshly.biz.support.merchant.service.bbc.impl;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.NumberUtil;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.merchant.complex.ShopFloorComplexQuery;
import com.gs.lshly.biz.support.merchant.entity.*;
import com.gs.lshly.biz.support.merchant.enums.MerchShopNavigationEnum;
import com.gs.lshly.common.enums.*;
import com.gs.lshly.common.enums.merchant.ShopStateEnum;
import com.gs.lshly.biz.support.merchant.mapper.ShopMapper;
import com.gs.lshly.biz.support.merchant.repository.*;
import com.gs.lshly.biz.support.merchant.service.bbc.IBbcShopService;
import com.gs.lshly.common.enums.stock.StockDeliveryCostCalcWayEnum;
import com.gs.lshly.common.enums.stock.StockShopBillTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.IObjConvert;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsInfoQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.merchant.dto.BbcShopDTO;
import com.gs.lshly.common.struct.bbc.merchant.qto.BbcShopQTO;
import com.gs.lshly.common.struct.bbc.merchant.vo.*;
import com.gs.lshly.common.struct.bbc.stock.dto.BbcStockDeliveryDTO;
import com.gs.lshly.common.struct.bbc.stock.vo.BbcStockDeliveryVO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.common.stock.CommonDeliveryCostCalcParam;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopDTO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.common.utils.MeterUtil;
import com.gs.lshly.common.utils.QQMapUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsInfoRpc;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserFavoritesShopRpc;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-13
*/
@Component
@Slf4j
public class BbcShopServiceImpl implements IBbcShopService {

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
    private IBbcGoodsInfoRpc bbcGoodsInfoRpc;

    @DubboReference
    private IBbcUserFavoritesShopRpc bbcUserFavoritesShopRpc;


    @Override
    public PageData<BbcShopVO.ListVO> pageData(BbcShopQTO.QTO qto) {
        QueryWrapper<Shop> queryWrapper = MybatisPlusUtil.query();
        IPage<Shop> page = MybatisPlusUtil.pager(qto);
        if(StringUtils.isNotBlank(qto.getShopName())) {
            queryWrapper.like("shop_name", qto.getShopName());
        }
        queryWrapper.eq("shop_state", ShopStateEnum.开启状态.getCode());
        queryWrapper.orderByDesc("cdate");
        repository.page(page, queryWrapper);
        return MybatisPlusUtil.toPageData(qto, BbcShopVO.ListVO.class, page);
    }

    @Override
    public PageData<BbcShopVO.ScopeListVO> scopePageData(BbcShopQTO.ScopeQTO qto) {
        QueryWrapper<Shop> wq = MybatisPlusUtil.query();
        IPage<Shop> page = MybatisPlusUtil.pager(qto);
        //位置如果不是数字，或者为空，则设置默认位置为上海
        if (StringUtils.isBlank(qto.getUserLongitude()) || StringUtils.isBlank(qto.getUserLatitude())
                || !NumberUtil.isNumber(qto.getUserLongitude()) || !NumberUtil.isNumber(qto.getUserLongitude())) {
            qto.setUserLongitude("121.244473").setUserLatitude("31.016902");
        }
        //wq.apply("ifnull(o.shop_ranges, 5)*1000>=o.meters");
        if(StringUtils.isNotBlank(qto.getShopName())) {
            wq.like("o.shop_name", qto.getShopName());
        }
        wq.eq("shop_state", ShopStateEnum.开启状态.getCode());
        ((ShopMapper) repository.getBaseMapper()).selectPageContainDistance(page, wq,
                new BigDecimal(qto.getUserLongitude()), new BigDecimal(qto.getUserLatitude()));
        return MybatisPlusUtil.toPageData(qto, BbcShopVO.ScopeListVO.class, page, (IObjConvert<BbcShopVO.ScopeListVO, Shop>) (vo, source) -> {
            if (source != null && source.getMeters() != null) {
                vo.setDistance(MeterUtil.treat(source.getMeters()));
            }
        });
    }

    @Override
    public List<BbcShopVO.ScopeListVO> nearShopList(BbcShopQTO.ScopeQTO qto) {
        QueryWrapper<Shop> wq = MybatisPlusUtil.query();
        //位置如果不是数字，或者为空，则设置默认位置为上海
        if (StringUtils.isBlank(qto.getUserLongitude()) || StringUtils.isBlank(qto.getUserLatitude())
                || !NumberUtil.isNumber(qto.getUserLongitude()) || !NumberUtil.isNumber(qto.getUserLongitude())) {
            qto.setUserLongitude("121.244473").setUserLatitude("31.016902");
        }
        wq.apply("ifnull(o.shop_ranges, 10)*1000>=o.meters");
        if(StringUtils.isNotBlank(qto.getShopName())) {
            wq.like("o.shop_name", qto.getShopName());
        }
        wq.eq("shop_state", ShopStateEnum.开启状态.getCode());
        List<Shop> shopList =  ((ShopMapper) repository.getBaseMapper()).selectListContainDistance(wq,
                new BigDecimal(qto.getUserLongitude()), new BigDecimal(qto.getUserLatitude()));
        if (ObjectUtils.isEmpty(shopList)){
            return new ArrayList<>();
        }
        List<BbcShopVO.ScopeListVO> scopeListVOS = shopList.parallelStream().map(e ->{
            BbcShopVO.ScopeListVO scopeListVO = new BbcShopVO.ScopeListVO();
            BeanUtils.copyProperties(e,scopeListVO);
            scopeListVO.setDistance(ObjectUtils.isEmpty(e.getMeters())?"":MeterUtil.treat(e.getMeters()));
            return scopeListVO;
        }).collect(Collectors.toList());
        return scopeListVOS;
    }


    @Override
    public BbcShopVO.DetailVO detailShop(BbcShopDTO.IdDTO dto) {
        Shop shop = repository.getById(dto.getId());
        BbcShopVO.DetailVO detailVo = new BbcShopVO.DetailVO();
        if(ObjectUtils.isEmpty(shop)){
            throw new BusinessException("ID无效");
        }
        BeanCopyUtils.copyProperties(shop, detailVo);
        return detailVo;
    }

    @Override
    public BbcShopVO.ComplexVO shopComplex(BbcShopDTO.IdDTO dto) {

        QueryWrapper<Shop> shopQueryWrapper = MybatisPlusUtil.query();
        shopQueryWrapper.eq("id",dto.getId());
        Shop shop =  repository.getOne(shopQueryWrapper);
        if(null == shop){
            throw new BusinessException("店铺不存在");
        }
        BbcShopVO.ComplexVO complexVO = new BbcShopVO.ComplexVO();
        BeanCopyUtils.copyProperties(shop,complexVO);
        complexVO.setBannerList(new ArrayList<>());
        complexVO.setTextMenuList(new ArrayList<>());
        complexVO.setMenuList(new ArrayList<>());
        complexVO.setFloorList(new ArrayList<>());
        Integer favoriteState  = bbcUserFavoritesShopRpc.innerFavoritesState(dto,dto.getId(),dto.getJwtUserId());
        if(null == favoriteState){
            complexVO.setFavoriteState(TrueFalseEnum.否.getCode());
        }else{
            complexVO.setFavoriteState(favoriteState);
        }

        //店铺轮播图
        QueryWrapper<ShopBanner> bannerQueryWrapper = MybatisPlusUtil.query();
        bannerQueryWrapper.eq("shop_id",dto.getId());
        bannerQueryWrapper.eq("terminal", TerminalEnum.BBC.getCode());
        List<ShopBanner> shopBannerList = shopBannerRepository.list(bannerQueryWrapper);
        if(ObjectUtils.isNotEmpty(shopBannerList)){
            List<BbcShopBannerVO.ListVO> bannerList = ListUtil.listCover(BbcShopBannerVO.ListVO.class,shopBannerList);
            complexVO.setBannerList(bannerList);
        }
        //店铺导航
        QueryWrapper<ShopNavigationMenu> menuQueryWrapper = MybatisPlusUtil.query();
        menuQueryWrapper.eq("shop_id",dto.getId());
        menuQueryWrapper.eq("terminal", TerminalEnum.BBC.getCode());
        List<ShopNavigationMenu> menuList=  shopNavigationMenuRepository.list(menuQueryWrapper);
        if(ObjectUtils.isNotEmpty(menuList)){
            for(ShopNavigationMenu menuItem:menuList){
                if(ShopNavigationMenuTypeEnum.自定义文本.getCode().equals(menuItem.getMenuType())){
                    BbcShopNavigationMenuVO.TextMenuVO textMenu  = new BbcShopNavigationMenuVO.TextMenuVO();
                    BeanCopyUtils.copyProperties(menuItem,textMenu);
                    complexVO.getTextMenuList().add(textMenu);
                }else if(ShopNavigationMenuTypeEnum.自定义图文.getCode().equals(menuItem.getMenuType())){
                    BbcShopNavigationMenuVO.MenuListVO imageMenu  = new BbcShopNavigationMenuVO.MenuListVO();
                    BeanCopyUtils.copyProperties(menuItem,imageMenu);
                    complexVO.getMenuList().add(imageMenu);
                }
            }
        }
        //店铺楼层
        QueryWrapper<ShopFloor> floorQueryWrapper = MybatisPlusUtil.query();
        floorQueryWrapper.eq("shop_id",dto.getId());
        floorQueryWrapper.eq("terminal", TerminalEnum.BBC.getCode());
        floorQueryWrapper.eq("pc_show", SitePCShowEnum.不显示.getCode());
        List<ShopFloor> shopFloorList = shopFloorRepository.list(floorQueryWrapper);
        if(ObjectUtils.isNotEmpty(shopFloorList)){
            List<String> floorIdList = new ArrayList<>();
            List<ShopFloorComplexQuery.ComplexBbcH5> queryShopFloorH5ComplexList = new ArrayList<>();
            for(ShopFloor shopFloorItem:shopFloorList){
                floorIdList.add(shopFloorItem.getId());
                BbcShopFloorVO.ListVO floorVO = new BbcShopFloorVO.ListVO();
                floorVO.setId(shopFloorItem.getId());
                floorVO.setName(shopFloorItem.getName());
                floorVO.setGoodsList(new ArrayList<>());
                complexVO.getFloorList().add(floorVO);
                ShopFloorComplexQuery.ComplexBbcH5 queryShopFloorH5Complex = new ShopFloorComplexQuery.ComplexBbcH5();
                queryShopFloorH5Complex.setFloorVO(floorVO);
                queryShopFloorH5Complex.setFloorGoodsItemList(new ArrayList<>());
                queryShopFloorH5ComplexList.add(queryShopFloorH5Complex);
            }
            //楼层商品
            QueryWrapper<ShopFloorGoods> floorGoodsQueryWrapper = MybatisPlusUtil.query();
            floorGoodsQueryWrapper.in("shop_floor_id",floorIdList);
            floorGoodsQueryWrapper.eq("shop_id",dto.getId());
            List<ShopFloorGoods> shopFloorGoodsList = shopFloorGoodsRepository.list(floorGoodsQueryWrapper);
            if(ObjectUtils.isNotEmpty(shopFloorGoodsList)){
                List<String> goodsIdList = new ArrayList<>();
                for(ShopFloorGoods floorGoodsItem:shopFloorGoodsList){
                    if(!goodsIdList.contains(floorGoodsItem.getGoodsId())){
                        goodsIdList.add(floorGoodsItem.getGoodsId());
                    }
                    for(ShopFloorComplexQuery.ComplexBbcH5 complexBbcH5Item:queryShopFloorH5ComplexList){
                        if(complexBbcH5Item.getFloorVO().getId().equals(floorGoodsItem.getShopFloorId())){
                            complexBbcH5Item.getFloorGoodsItemList().add(floorGoodsItem);
                        }
                    }
                }
                // 商品服务查询商品信息
                if(ObjectUtils.isNotEmpty(goodsIdList)){
                    BbcGoodsInfoQTO.GoodsIdListQTO qto = new BbcGoodsInfoQTO.GoodsIdListQTO();
                    qto.setIdList(goodsIdList);
                    List<BbcGoodsInfoVO.HomeAndShopInnerServiceVO> innerGoodsList =   bbcGoodsInfoRpc.getInnerServiceVO(qto);
                    for(BbcGoodsInfoVO.HomeAndShopInnerServiceVO innerGoodsVoItem: innerGoodsList){
                        for(ShopFloorComplexQuery.ComplexBbcH5 complexBbcH5Item:queryShopFloorH5ComplexList){
                            for(ShopFloorGoods floorGoodsItem: complexBbcH5Item.getFloorGoodsItemList()){
                                if(floorGoodsItem.getGoodsId().equals(innerGoodsVoItem.getId())){
                                    complexBbcH5Item.getFloorVO().getGoodsList().add(innerGoodsVoItem);
                                }
                            }
                        }
                    }
                }
            }
        }
        return complexVO;
    }

    @Autowired
    private IShopDeliveryStyleRepository deliveryStyleRepository;

    @Override
    public BbcStockDeliveryVO.SupportDeliveryTypeVO supportDeliveryStyle(BbcStockDeliveryDTO.SupportDeliveryTypeDTO dto) {
        ShopDeliveryStyle style = deliveryStyleRepository.getOne(new QueryWrapper<ShopDeliveryStyle>().eq("shop_id", dto.getShopId()));
        if (style == null) {
            throw new BusinessException("店铺暂未配置物流方式");
        }
        BbcStockDeliveryVO.SupportDeliveryTypeVO vo = new BbcStockDeliveryVO.SupportDeliveryTypeVO();
        if (style.getDeliveryTypes().contains(StockDeliveryTypeEnum.快递配送.getCode()+"")) {
            vo.setSupportExpressDelivery(true);
        }
        if (style.getDeliveryTypes().contains(StockDeliveryTypeEnum.门店自提.getCode()+"")) {
            vo.setSupportUserPickup(true);
        }
        if (style.getDeliveryTypes().contains(StockDeliveryTypeEnum.门店配送.getCode()+"")) {
            vo.setShopRanges(style.getShopRanges());
            vo.setSupportShopDelivery(true);
        }
        return vo;
    }

    @Override
    public List<BbcShopVO.ShopNavVO> navigationTree(BbcShopDTO.IdDTO dto) {
        List<BbcShopVO.ShopNavVO> shopNavVoList  = new ArrayList<>();
        if(StringUtils.isBlank(dto.getId())){
            return shopNavVoList;
        }
        QueryWrapper<ShopNavigation> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("shop_id",dto.getId());
        List<ShopNavigation> shopNavigationList = shopNavigationRepository.list(queryWrapper);
        if(ObjectUtils.isNotEmpty(shopNavigationList)){
            for(ShopNavigation shopNavigation:shopNavigationList){
                if(shopNavigation.getLeve().equals(MerchShopNavigationEnum.一级分类.getCode())){
                    BbcShopVO.ShopNavVO shopNavVO = new BbcShopVO.ShopNavVO();
                    BeanUtils.copyProperties(shopNavigation,shopNavVO);
                    shopNavVoList.add(shopNavVO);
                }
            }
            for(ShopNavigation shopNavigation:shopNavigationList){
                if(MerchShopNavigationEnum.二级分类.getCode().equals(shopNavigation.getLeve())){
                    BbcShopVO.ShopChildNavVO shopChildNavVO = new BbcShopVO.ShopChildNavVO();
                    BeanUtils.copyProperties(shopNavigation,shopChildNavVO);
                    for(BbcShopVO.ShopNavVO shopNavVO:shopNavVoList){
                        if(shopNavVO.getId().equals(shopNavigation.getParentId())){
                            shopNavVO.getChildNavList().add(shopChildNavVO);
                            break;
                        }
                    }
                }
            }
        }
        return shopNavVoList;
    }

    @Override
    public CommonDeliveryCostCalcParam getPickupDeliveryCostCalcParam(String shopId) {
        ShopDeliveryStyle style = deliveryStyleRepository.getOne(new QueryWrapper<ShopDeliveryStyle>().eq("shop_id", shopId));
        if (style == null) {
            throw new BusinessException("店铺暂未配置物流方式");
        }
        if (!style.getDeliveryTypes().contains(StockDeliveryTypeEnum.门店配送.getCode()+"")) {
            throw new BusinessException("店铺不支持“门店配送”的物流方式");
        }

        CommonDeliveryCostCalcParam param = new CommonDeliveryCostCalcParam();
        if (StockShopBillTypeEnum.自定义费用.getCode().equals(style.getShopBillType())) {
            log.info("【自定义费用】,{}", style.getShopCustomizePrice() != null ? style.getShopCustomizePrice() : BigDecimal.ZERO);
            return param.setCalcWay(StockDeliveryCostCalcWayEnum.固定运费).setCustomizeCost(style.getShopCustomizePrice()!=null ? style.getShopCustomizePrice() : BigDecimal.ZERO);
        }

        if (StockShopBillTypeEnum.按重量.getCode().equals(style.getShopBillType())) {
            CommonDeliveryCostCalcParam.QuantityParam quantityParam = new CommonDeliveryCostCalcParam.QuantityParam();
            //首重、首价
            quantityParam.setFirst(new BigDecimal(style.getShopFirstWeight()+"")).setFirstPrice(style.getShopFirstWeightPrice());
            //续重、续价
            quantityParam.setIncrease(new BigDecimal(style.getShopIncreaseWeight()+"")).setIncreasePrice(style.getShopIncreaseWeightPrice());
            log.info("【按重量】,首重[{}],首费[{}],续重[{}],续费[{}]", quantityParam.getFirst(), quantityParam.getFirstPrice(), quantityParam.getIncrease(), quantityParam.getIncreasePrice());
            return param.setCalcWay(StockDeliveryCostCalcWayEnum.按重量).setQuantityParam(quantityParam);
        }

        if (StockShopBillTypeEnum.按件数.getCode().equals(style.getShopBillType())) {
            CommonDeliveryCostCalcParam.QuantityParam quantityParam = new CommonDeliveryCostCalcParam.QuantityParam();
            //首件、首价
            quantityParam.setFirst(new BigDecimal(style.getShopFirstQuantity()+"")).setFirstPrice(style.getShopFirstQuantityPrice());
            //续件、续价
            quantityParam.setIncrease(new BigDecimal(style.getShopIncreaseQuantity()+"")).setIncreasePrice(style.getShopIncreaseQuantityPrice());
            log.info("【按件数】,首件[{}],首费[{}],续件[{}],续费[{}]", quantityParam.getFirst(), quantityParam.getFirstPrice(), quantityParam.getIncrease(), quantityParam.getIncreasePrice());
            return param.setCalcWay(StockDeliveryCostCalcWayEnum.按件数).setQuantityParam(quantityParam);
        }

        throw new BusinessException("店铺配送方式配置错误");
    }

    @Override
    public BbcShopVO.isCity isCity(BbcShopDTO.isCity dto) {
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
    public BbcShopVO.ShopIdName getShopIdName(BbcShopDTO.ShopNavigationIdDTO dto) {
        if (null == dto || StringUtils.isBlank(dto.getId())){
            throw new BusinessException("参数id为空异常！");
        }
        BbcShopVO.ShopIdName shopIdName = new BbcShopVO.ShopIdName();
        ShopNavigation shopNavigation = shopNavigationRepository.getById(dto.getId());
        if (null != null){
            shopIdName.setId(StringUtils.isBlank(shopNavigation.getShopId())?"":shopNavigation.getShopId());
        }
        return shopIdName;
    }

    @Override
    public List<BbcShopNavigationVO.NavigationListVO> listNavigationListVO(BbcShopDTO.IdDTO dto) {
        if (null == dto || StringUtils.isBlank(dto.getId())){
            throw new BusinessException("店铺id不能为空！");
        }
        QueryWrapper<ShopNavigation> wrapper = MybatisPlusUtil.query();
        wrapper.eq("terminal",TerminalEnum.BBC.getCode());
        wrapper.eq("shop_id",dto.getId());
        wrapper.orderByAsc("idx","id");
        List<ShopNavigation> dataList =  shopNavigationRepository.list(wrapper);
        Map<String, BbcShopNavigationVO.NavigationListVO> utilMap = new HashMap<>();
        for(ShopNavigation item:dataList){
            if(MerchShopNavigationEnum.一级分类.getCode().equals(item.getLeve())){
                BbcShopNavigationVO.NavigationListVO parentItem =  utilMap.get(item.getId());
                if(ObjectUtils.isNull(parentItem)){
                    parentItem = new BbcShopNavigationVO.NavigationListVO();
                    parentItem.setChildList(new ArrayList<>());
                    utilMap.put(item.getId(),parentItem);
                }
                BeanUtils.copyProperties(item,parentItem);
            }
        }

        for(ShopNavigation item:dataList){
            if(MerchShopNavigationEnum.二级分类.getCode().equals(item.getLeve())){
                BbcShopNavigationVO.NavigationListVO parentItem =  utilMap.get(item.getParentId());
                if(ObjectUtils.isNull(parentItem)){
                    Console.log("二级分类:{},{},{}",item.getId(),item.getNavName(),item.getParentId());
                    throw new BusinessException("二级分类的父ID无效");
                }
                BbcShopNavigationVO.NavigationChildVO childItem = new BbcShopNavigationVO.NavigationChildVO();
                BeanUtils.copyProperties(item,childItem);
                parentItem.getChildList().add(childItem);
            }
        }
        List<BbcShopNavigationVO.NavigationListVO> voList = new ArrayList<>(utilMap.values());
        voList.sort((a, b) -> a.getIdx() - b.getIdx());
        for(BbcShopNavigationVO.NavigationListVO listItem:voList){
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
    public List<String> innerShopDelivery(String shopId) {
        if (ObjectUtils.isNotEmpty(shopId)){
            QueryWrapper<ShopDeliveryStyle> query = MybatisPlusUtil.query();
            query.and(i->i.eq("shop_id",shopId));
            query.last("limit 0,1");
            ShopDeliveryStyle one = shopDeliveryStyleRepository.getOne(query);
            if (ObjectUtils.isNotEmpty(one)){
                String[] split = one.getDeliveryTypes().split(",");
                if (ObjectUtils.isNotEmpty(split)){
                    List<String> strings = Arrays.asList(split);
                    return strings;
                }
            }
        }
        return null;
    }

    @Override
    public BbcShopVO.InnerDetailVO innerDetailShop(BbcShopQTO.InnerShopQTO qto) {
        if(StringUtils.isBlank(qto.getShopId())){
            return null;
        }
        QueryWrapper<Shop> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("id",qto.getShopId());
        Shop shop = repository.getOne(queryWrapper);
        if(ObjectUtils.isEmpty(shop)){
            return null;
        }
        BbcShopVO.InnerDetailVO detailVo = new BbcShopVO.InnerDetailVO();
        BeanCopyUtils.copyProperties(shop, detailVo);
        return detailVo;
    }

    @Override
    public List<BbcShopVO.InnerDetailVO> innerListDetailShop(BbcShopQTO.InnerListShopQTO qto) {
        if(ObjectUtils.isEmpty(qto.getShopIdList())){
            return null;
        }
        List<Shop> shopList = repository.listByIds(qto.getShopIdList());
        if(null == shopList){
            return new ArrayList<>();
        }
        return  ListUtil.listCover(BbcShopVO.InnerDetailVO.class,shopList);
    }

    @Override
    public List<BbcShopVO.ShopNavigationIdName> innerListShopNavigation(ShopDTO.IdDTO dto) {
        QueryWrapper<ShopNavigation> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("shop_id",dto.getId());
        List<ShopNavigation> shopNavigationList = shopNavigationRepository.list(queryWrapper);
        Map<String,BbcShopVO.ShopNavigationIdName> utilMap = new HashMap<>();
        for(ShopNavigation shopNavigation:shopNavigationList){
            if(shopNavigation.getLeve().equals(MerchShopNavigationEnum.一级分类.getCode())){
                BbcShopVO.ShopNavigationIdName shopNavigationIdName =  utilMap.get(shopNavigation.getId());
                if(null == shopNavigationIdName){
                    shopNavigationIdName = new BbcShopVO.ShopNavigationIdName();
                    shopNavigationIdName.setId(shopNavigation.getId());
                    shopNavigationIdName.setNavName(shopNavigation.getNavName());
                    utilMap.put(shopNavigation.getId(),shopNavigationIdName);
                }
            }
        }
        for(ShopNavigation item:shopNavigationList){
            if(MerchShopNavigationEnum.二级分类.getCode().equals(item.getLeve())){
                BbcShopVO.ShopNavigationIdName shopNavigationIdName =  utilMap.get(item.getParentId());
                if(ObjectUtils.isNull(shopNavigationIdName)){
                    throw new BusinessException("二级分类的父ID无效");
                }
                BbcShopVO.ShopNavigationChildIdName childIdName = new BbcShopVO.ShopNavigationChildIdName();
                childIdName.setId(item.getId());
                childIdName.setNavName(item.getNavName());
                shopNavigationIdName.getNavigationList().add(childIdName);
            }
        }
        return new ArrayList<>(utilMap.values());
    }

    @Override
    public List<BbcShopVO.ShopIdName> innerListShopIdName(BbcShopDTO.MerchantIdDTO dto) {
        QueryWrapper<Shop> wrapper = MybatisPlusUtil.query();
        wrapper.eq("merchant_id",dto.getId());
        List<Shop> dataList = repository.list(wrapper);
        return ListUtil.listCover( BbcShopVO.ShopIdName.class,dataList);
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


