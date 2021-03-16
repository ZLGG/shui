package com.gs.lshly.biz.support.merchant.service.bbb.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.merchant.complex.ShopFloorComplexQuery;
import com.gs.lshly.biz.support.merchant.entity.*;
import com.gs.lshly.biz.support.merchant.enums.MerchShopNavigationEnum;
import com.gs.lshly.biz.support.merchant.enums.ShopSearchSortEnum;
import com.gs.lshly.biz.support.merchant.mapper.ShopMapper;
import com.gs.lshly.biz.support.merchant.mapper.views.ShopSearchView;
import com.gs.lshly.biz.support.merchant.repository.*;
import com.gs.lshly.biz.support.merchant.service.bbb.pc.IBbbShopService;
import com.gs.lshly.common.enums.*;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsInfoVO;
import com.gs.lshly.common.struct.bbb.pc.merchant.dto.BbbShopDTO;
import com.gs.lshly.common.struct.bbb.pc.merchant.qto.BbbShopQTO;
import com.gs.lshly.common.struct.bbb.pc.merchant.vo.BbbShopVO;
import com.gs.lshly.common.struct.bbb.pc.pages.qto.PCBbbHomeQTO;
import com.gs.lshly.common.struct.bbb.pc.pages.vo.PCBbbHomeVO;
import com.gs.lshly.common.struct.bbb.pc.pages.vo.PCBbbShopHomeVO;
import com.gs.lshly.common.struct.bbb.pc.pages.vo.PCBbbShopListVO;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopDTO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbb.pc.commodity.IPCBbbGoodsCategoryRpc;
import com.gs.lshly.rpc.api.bbb.pc.commodity.IPCBbbGoodsInfoRpc;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserFavoritesShopRpc;
import com.gs.lshly.rpc.api.bbb.pc.user.IPCBbbUserPrivateUserRpc;
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
public class BbbShopServiceImpl implements IBbbShopService {

    @Autowired
    private IShopRepository repository;
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private IShopNavigationRepository shopNavigationRepository;
    @Autowired
    private IShopNavigationMenuRepository shopNavigationMenuRepository;
    @Autowired
    private IShopSignboardRepository  shopSignboardRepository;
    @Autowired
    private IShopAdvertRepository  shopAdvertRepository;
    @Autowired
    private IShopCustomAreaRepository shopCustomAreaRepository;
    @Autowired
    private IShopFeatureRepository  shopFeatureRepository;
    @Autowired
    private IShopFloorGoodsRepository shopFloorGoodsRepository;
    @Autowired
    private IShopFloorRepository shopFloorRepository;
    @Autowired
    private IShopServiceRepository shopServiceRepository;

    @DubboReference
    private IPCBbbGoodsInfoRpc ipcBbbGoodsInfoRpc;
    @DubboReference
    private IBbbUserFavoritesShopRpc bbbUserFavoritesShopRpc;
    @DubboReference
    private IPCBbbGoodsCategoryRpc ipcBbbGoodsCategoryRpc;
    @DubboReference
    private IPCBbbUserPrivateUserRpc bbbUserPrivateUserRpc;

    @Override
    public PageData<PCBbbShopListVO.ShopInfoVo> pageData(BbbShopQTO.SearchQTO qto) {
        QueryWrapper<ShopSearchView> shopQueryWrapper = MybatisPlusUtil.query();
        shopQueryWrapper.and(wrapper -> {
            if(ObjectUtils.isNotEmpty(qto.getLevel3CategoryId())){
                wrapper.or().in("gory.category_id",qto.getLevel3CategoryId());
            }
        });
        if(ObjectUtils.isNotEmpty(qto.getBrandId())){
            shopQueryWrapper.and(wrapper -> wrapper.in("shop.brand_id", qto.getBrandId()));
        }
        if(ShopSearchSortEnum.销量.getCode().equals(qto.getOrderByProperties())){
            shopQueryWrapper.orderByAsc("feature.sales");
        }
        if(ShopSearchSortEnum.星级.getCode().equals(qto.getOrderByProperties())){
            shopQueryWrapper.orderByAsc("feature.score");
        }
        IPage<ShopSearchView> pager = MybatisPlusUtil.pager(qto);
        shopMapper.bbbPcShopSearch(pager,shopQueryWrapper);
        //登录用户,取店铺收藏状态,没有登录全部返回 收藏状态否
        if(StringUtils.isNotBlank(qto.getJwtUserId())){
            List<PCBbbShopListVO.ShopInfoVo> voList = new ArrayList<>();
            if(ObjectUtils.isNotEmpty(pager.getRecords())){
                List<String> checkShopIdList = new ArrayList<>();
                for(ShopSearchView shop:pager.getRecords()){
                    checkShopIdList.add(shop.getId());
                    PCBbbShopListVO.ShopInfoVo shopInfoVo = new PCBbbShopListVO.ShopInfoVo();
                    BeanUtils.copyProperties(shop,shopInfoVo);
                    voList.add(shopInfoVo);
                }
                List<String> favoritesShopIdList =  bbbUserFavoritesShopRpc.innerFavoritesListState(qto,checkShopIdList,qto.getJwtUserId());
                for(PCBbbShopListVO.ShopInfoVo shopInfoVo:voList){
                    if(favoritesShopIdList.contains(shopInfoVo.getId())){
                        shopInfoVo.setFavoriteState(TrueFalseEnum.是.getCode());
                    }
                }
            }
            return new PageData<>(voList,qto.getPageNum(), qto.getPageSize(), pager.getTotal());
        }else{
            return  MybatisPlusUtil.toPageData(qto, PCBbbShopListVO.ShopInfoVo.class, pager);
        }
    }

    @Override
    public PCBbbShopHomeVO.ShopHomeVO index(BbbShopDTO.IdDTO dto) {
        Shop shop = repository.getById(dto.getId());
        if(null == shop){
            throw new BusinessException("店铺不存在");
        }
        PCBbbShopHomeVO.ShopHomeVO shopHomeVO = new PCBbbShopHomeVO.ShopHomeVO();
        //店铺信息
        PCBbbShopHomeVO.ShopInfoVo shopInfoVo = new PCBbbShopHomeVO.ShopInfoVo();
        BeanCopyUtils.copyProperties(shop, shopInfoVo);
        shopHomeVO.setShopInfo(shopInfoVo);

        //店铺收藏状态
        if(StringUtils.isNotBlank(dto.getJwtUserId())){
            Integer favoritesState  = bbbUserFavoritesShopRpc.innerFavoritesState(dto,shop.getId(),dto.getJwtUserId());
            if(null != favoritesState){
                shopInfoVo.setFavoriteState(favoritesState);
            }
        }

        //店铺特征信息
        QueryWrapper<ShopFeature> shopFeatureQueryWrapper = MybatisPlusUtil.query();
        shopFeatureQueryWrapper.eq("shop_id",shop.getId());
        ShopFeature shopFeature =  shopFeatureRepository.getOne(shopFeatureQueryWrapper);
        if(null != shopFeature){
            shopHomeVO.getShopInfo().setScore(shopFeature.getScore());
            shopHomeVO.getShopInfo().setSales(shopFeature.getSales());
        }
        //店招信息
        QueryWrapper<ShopSignboard> signboardQueryWrapper = MybatisPlusUtil.query();
        signboardQueryWrapper.eq("shop_id",shop.getId());
        ShopSignboard shopSignboard = shopSignboardRepository.getOne(signboardQueryWrapper);
        if(null != shopSignboard){
            PCBbbShopHomeVO.ShopSignboardVo signboardVo = new PCBbbShopHomeVO.ShopSignboardVo();
            BeanUtils.copyProperties(shopSignboard,signboardVo);
            shopHomeVO.setSignboardVo(signboardVo);
        }
        //通栏广告图
        QueryWrapper<ShopAdvert> shopAdvertQueryWrapper = MybatisPlusUtil.query();
        shopAdvertQueryWrapper.eq("terminal",TerminalEnum.BBB.getCode());
        shopAdvertQueryWrapper.eq("advert_type",AdvertTypeEnum.通栏广告.getCode());
        shopAdvertQueryWrapper.eq("shop_id",shop.getId());
        ShopAdvert shopAdvert =  shopAdvertRepository.getOne(shopAdvertQueryWrapper);
        if(null != shopAdvert){
            PCBbbShopHomeVO.ShopAdvertVo advertVo = new PCBbbShopHomeVO.ShopAdvertVo();
            BeanUtils.copyProperties(shopAdvert,advertVo);
            shopHomeVO.setShopAdvertVo(advertVo);
        }
        //店铺自定义区域
        QueryWrapper<ShopCustomArea> customAreaQueryWrapper = MybatisPlusUtil.query();
        customAreaQueryWrapper.eq("shop_id",shop.getId());
        ShopCustomArea shopCustomArea =  shopCustomAreaRepository.getOne(customAreaQueryWrapper);
        if(null != shopCustomArea){
            PCBbbShopHomeVO.CustomAreaVo customAreaVo = new PCBbbShopHomeVO.CustomAreaVo();
            BeanUtils.copyProperties(shopCustomArea,customAreaVo);
            shopHomeVO.setCustomAreaVo(customAreaVo);
        }
        // 店铺分类
        QueryWrapper<ShopNavigation> navigationQueryWrapper = MybatisPlusUtil.query();
        navigationQueryWrapper.eq("terminal", TerminalEnum.BBB.getCode());
        navigationQueryWrapper.eq("shop_id",shop.getId());
        List<ShopNavigation> navigationList = shopNavigationRepository.list(navigationQueryWrapper);
        if(ObjectUtils.isNotEmpty(navigationList)){
            for(ShopNavigation navigation:navigationList){
                if(MerchShopNavigationEnum.一级分类.getCode().equals(navigation.getLeve())){
                    PCBbbShopHomeVO.ShopNavigationVo navigationVo = new PCBbbShopHomeVO.ShopNavigationVo();
                    BeanUtils.copyProperties(navigation,navigationVo);
                    shopHomeVO.getNavigationList().add(navigationVo);
                    if(TrueFalseEnum.是.getCode().equals(navigation.getIsMenu())){
                        PCBbbShopHomeVO.ShopMenuVo shopMenuVo = new PCBbbShopHomeVO.ShopMenuVo();
                        shopMenuVo.setId(navigation.getId());
                        shopMenuVo.setMenuName(navigation.getNavName());
                        shopMenuVo.setMenuType(ShopNavigationMenuTypeEnum.店铺分类.getCode());
                        shopHomeVO.getMenuList().add(shopMenuVo);
                    }
                }
            }
            for(ShopNavigation navigation:navigationList){
                if(MerchShopNavigationEnum.二级分类.getCode().equals(navigation.getLeve())){
                    PCBbbShopHomeVO.ShopChildNavigationVo childNavigationVo = new PCBbbShopHomeVO.ShopChildNavigationVo();
                    BeanUtils.copyProperties(navigation,childNavigationVo);
                    for(PCBbbShopHomeVO.ShopNavigationVo shopNavigationVo:shopHomeVO.getNavigationList()){
                        if(shopNavigationVo.getId().equals(navigation.getParentId())){
                            shopNavigationVo.getChild().add(childNavigationVo);
                            break;
                        }
                    }
                    for(PCBbbShopHomeVO.ShopMenuVo menuVo:shopHomeVO.getMenuList()){
                        if(menuVo.getId().equals(navigation.getParentId())){
                            if(TrueFalseEnum.是.getCode().equals(navigation.getIsMenu())){
                                PCBbbShopHomeVO.ShopChildMenuVo childMenuVo = new PCBbbShopHomeVO.ShopChildMenuVo();
                                childMenuVo.setId(navigation.getId());
                                childMenuVo.setMenuName(navigation.getNavName());
                                childMenuVo.setMenuType(ShopNavigationMenuTypeEnum.店铺分类.getCode());
                            }
                            break;
                        }
                    }
                }
            }
        }
        //店铺菜单 = 店铺自定义分类菜单 + 自定义链接
        QueryWrapper<ShopNavigationMenu> navigationMenuQueryWrapper = MybatisPlusUtil.query();
        navigationMenuQueryWrapper.eq("terminal", TerminalEnum.BBB.getCode());
        navigationMenuQueryWrapper.eq("pc_show", PcH5Enum.YES.getCode());
        navigationMenuQueryWrapper.eq("shop_id",shop.getId());
        List<ShopNavigationMenu> menuList = shopNavigationMenuRepository.list(navigationMenuQueryWrapper);
        if(ObjectUtils.isNotEmpty(menuList)){
            for(ShopNavigationMenu navigationMenu:menuList){
                PCBbbShopHomeVO.ShopMenuVo shopMenuVo = new PCBbbShopHomeVO.ShopMenuVo();
                shopMenuVo.setId(navigationMenu.getId());
                shopMenuVo.setMenuName(navigationMenu.getMenuName());
                shopMenuVo.setJumpUrl(navigationMenu.getJumpUrl());
                shopMenuVo.setMenuType(ShopNavigationMenuTypeEnum.自定义文本.getCode());
                shopHomeVO.getMenuList().add(shopMenuVo);
            }
        }
        return shopHomeVO;
    }

    @Override
    public List<PCBbbShopHomeVO.FloorVO> floorFirstList(BbbShopDTO.IdDTO dto) {
        QueryWrapper<ShopFloor> wrapper = new QueryWrapper<>();
        wrapper.eq("terminal",TerminalEnum.BBB.getCode());
        wrapper.eq("pc_show", PcH5Enum.YES.getCode());
        wrapper.eq("shop_id",dto.getId());
        wrapper.orderByAsc("idx");
        List<ShopFloor> floorList = shopFloorRepository.list(wrapper);
        if(ObjectUtils.isEmpty(floorList)){
            return new ArrayList<>();
        }
        List<String> floorIdList = new ArrayList<>();
        Map<String, ShopFloorComplexQuery.ComplexB2bPc> voMap = new HashMap<>();
        for(ShopFloor floorItem:floorList){
            floorIdList.add(floorItem.getId());
            ShopFloorComplexQuery.ComplexB2bPc complex = new ShopFloorComplexQuery.ComplexB2bPc();
            complex.setFloorGoodsList(new ArrayList<>());
            PCBbbShopHomeVO.FloorVO listVO = new  PCBbbShopHomeVO.FloorVO();
            listVO.setGoodsList(new ArrayList<>());
            BeanCopyUtils.copyProperties(floorItem,listVO);
            complex.setFloorVO(listVO);
            voMap.put(floorItem.getId(),complex);
        }
        if(ObjectUtils.isEmpty(floorIdList)){
            return new ArrayList<>();
        }
        QueryWrapper<ShopFloorGoods> shopFloorGoodsQueryWrapper = MybatisPlusUtil.query();
        shopFloorGoodsQueryWrapper.in("shop_floor_id",floorIdList);
        List<ShopFloorGoods> floorGoodsList = shopFloorGoodsRepository.list(shopFloorGoodsQueryWrapper);
        List<String> goodsIdList = new ArrayList<>();
        for(ShopFloorGoods shopFloorGoodsItem:floorGoodsList){
            //一个商品在多个楼层,商品服务只查一个就可以,先去重
            if(!goodsIdList.contains(shopFloorGoodsItem.getGoodsId())){
                goodsIdList.add(shopFloorGoodsItem.getGoodsId());
            }
            ShopFloorComplexQuery.ComplexB2bPc complex = voMap.get(shopFloorGoodsItem.getShopFloorId());
            if(null != complex){
                complex.getFloorGoodsList().add(shopFloorGoodsItem);
            }
        }
        if(ObjectUtils.isNotEmpty(goodsIdList)){
            List<PCBbbGoodsInfoVO.ShopInnerServiceVO> innerGoodsList = ipcBbbGoodsInfoRpc.getShopGoodsInnerServiceVO(goodsIdList,dto);
            for(ShopFloorComplexQuery.ComplexB2bPc complex:voMap.values()){
                for(ShopFloorGoods floorGoodsItem:complex.getFloorGoodsList()){
                    for(PCBbbGoodsInfoVO.ShopInnerServiceVO innerGoodsItem:innerGoodsList){
                        if(floorGoodsItem.getGoodsId().equals(innerGoodsItem.getId())){
                            complex.getFloorVO().getGoodsList().add(innerGoodsItem);
                        }
                    }
                }
            }
        }
        List<PCBbbShopHomeVO.FloorVO> voList = new ArrayList<>();
        for(ShopFloorComplexQuery.ComplexB2bPc complex:voMap.values()){
            voList.add(complex.getFloorVO());
        }
        return voList;
    }

    @Override
    public List<PCBbbShopHomeVO.FloorListVO> floorList(BbbShopDTO.IdDTO dto) {
        QueryWrapper<ShopFloor> wrapper = new QueryWrapper<>();
        wrapper.eq("terminal",TerminalEnum.BBB.getCode());
        wrapper.eq("pc_show", PcH5Enum.YES.getCode());
        wrapper.eq("shop_id",dto.getId());
        wrapper.orderByAsc("idx");
        List<ShopFloor> floorList = shopFloorRepository.list(wrapper);
        if(ObjectUtils.isEmpty(floorList)){
            return new ArrayList<>();
        }
        return ListUtil.listCover(PCBbbShopHomeVO.FloorListVO.class,floorList);
    }

    @Override
    public PCBbbShopHomeVO.FloorGoodsListVO floorGoodsList(BbbShopDTO.FloorIdDTO dto) {
        PCBbbShopHomeVO.FloorGoodsListVO floorGoodsListVO = new PCBbbShopHomeVO.FloorGoodsListVO();
        floorGoodsListVO.setFloorId(dto.getFloorId());
        QueryWrapper<ShopFloorGoods> shopFloorGoodsQueryWrapper = MybatisPlusUtil.query();
        shopFloorGoodsQueryWrapper.eq("shop_floor_id",dto.getFloorId());
        List<ShopFloorGoods> floorGoodsList = shopFloorGoodsRepository.list(shopFloorGoodsQueryWrapper);
        List<String> goodsIdList = ListUtil.getIdList(ShopFloorGoods.class,floorGoodsList,"goodsId");
        if(ObjectUtils.isNotEmpty(goodsIdList)){
            List<PCBbbGoodsInfoVO.ShopInnerServiceVO> innerGoodsList = ipcBbbGoodsInfoRpc.getShopGoodsInnerServiceVO(goodsIdList,dto);
            if(ObjectUtils.isNotEmpty(innerGoodsList)){
                //装配排序号
                for(PCBbbGoodsInfoVO.ShopInnerServiceVO shopInnerServiceVO:innerGoodsList){
                    for(ShopFloorGoods floorGoods:floorGoodsList){
                        if(floorGoods.getGoodsId().equals(shopInnerServiceVO.getId())){
                            shopInnerServiceVO.setIdx(floorGoods.getIdx());
                            break;
                        }
                    }
                }
                innerGoodsList.sort((o1, o2) ->
                        ((Integer)o1.getIdx()) != null && ((Integer)o2.getIdx()) != null ? (o1.getIdx() > o2.getIdx() ? 1 : -1) : -1);
                floorGoodsListVO.setGoodsList(innerGoodsList);
            }
        }

        return floorGoodsListVO;
    }

    @Override
    public PageData<PCBbbHomeVO.ShopSearchInfo> shopSearchList(PCBbbHomeQTO.ShopSearchQTO qto) {

        QueryWrapper<ShopSearchView> shopQueryWrapper = MybatisPlusUtil.query();
        IPage<ShopSearchView> pager = MybatisPlusUtil.pager(qto);
        if(StringUtils.isNotBlank(qto.getContent())){
            shopQueryWrapper.like("shop.shop_name",qto.getContent());
            shopQueryWrapper.or();
            shopQueryWrapper.like("shop.shop_desc",qto.getContent());
        }
        IPage<ShopSearchView> shopSearchViewIPage = shopMapper.bbbPcShopKeyWordSearch(pager,shopQueryWrapper);
        if (ObjectUtils.isEmpty(shopSearchViewIPage) || ObjectUtils.isEmpty(shopSearchViewIPage.getRecords())){
            return new PageData<>();
        }
        //登录用户,取店铺收藏状态,没有登录全部返回 收藏状态否
        List<PCBbbHomeVO.ShopSearchInfo> voList = new ArrayList<>();
        List<String> checkShopIdList = new ArrayList<>();
        for(ShopSearchView shop:pager.getRecords()){
            checkShopIdList.add(shop.getId());
            PCBbbHomeVO.ShopSearchInfo shopInfoVo = new PCBbbHomeVO.ShopSearchInfo();
            BeanUtils.copyProperties(shop,shopInfoVo);
            voList.add(shopInfoVo);
        }

        if (ObjectUtils.isNotEmpty(qto.getJwtUserId())){
            List<String> favoritesShopIdList =  bbbUserFavoritesShopRpc.innerFavoritesListState(qto,checkShopIdList,qto.getJwtUserId());
            for(PCBbbHomeVO.ShopSearchInfo shopInfoVo:voList){
                if(favoritesShopIdList.contains(shopInfoVo.getId())){
                    shopInfoVo.setFavoriteState(TrueFalseEnum.是.getCode());
                    break;
                }
            }
        }
        return MybatisPlusUtil.toPageData(qto,PCBbbHomeVO.ShopSearchInfo.class,pager);
    }

    @Override
    public PCBbbShopHomeVO.ShopServiceVO shopService(BbbShopDTO.IdDTO dto) {
        QueryWrapper<ShopService> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("shop_id",dto.getId());
        ShopService shopService = shopServiceRepository.getOne(queryWrapper);
        PCBbbShopHomeVO.ShopServiceVO shopServiceVO = new PCBbbShopHomeVO.ShopServiceVO();
        if(null != shopService){
            if(EdAbleStateEnum.启用.getCode().equals(shopService.getState())){
                shopServiceVO.setAccount(shopService.getAccount());
            }
            if(EdAbleStateEnum.启用.getCode().equals(shopService.getPhoneState())){
                shopServiceVO.setPhone(shopService.getPhone());
            }
        }
        return shopServiceVO;
    }


    @Override
    public BbbShopVO.ComplexDetailVO inneComplexDetailShop(String shopId,BaseDTO dto) {
        if(StringUtils.isBlank(shopId)){
            return null;
        }
        Shop shop = repository.getById(shopId);
        if(null == shop){
            return null;
        }
        BbbShopVO.ComplexDetailVO detailVo = new BbbShopVO.ComplexDetailVO();
        BeanCopyUtils.copyProperties(shop, detailVo);

        //店铺收藏状态
        if(StringUtils.isNotBlank(dto.getJwtUserId())){
            Integer favoritesState  = bbbUserFavoritesShopRpc.innerFavoritesState(dto,shop.getId(),dto.getJwtUserId());
            if(null != favoritesState){
                detailVo.setFavoriteState(favoritesState);
            }
        }

        return detailVo;
    }

    @Override
    public BbbShopVO.ShopScoreDetailVO innerShopScoreDetailVO(String shopId, BaseDTO dto) {
        if(StringUtils.isBlank(shopId)){
            return null;
        }
        Shop shop = repository.getById(shopId);
        if(null == shop){
            return null;
        }
        BbbShopVO.ShopScoreDetailVO detailVo = new BbbShopVO.ShopScoreDetailVO();
        BeanCopyUtils.copyProperties(shop, detailVo);
        //评分信息 + 销量信息
        QueryWrapper<ShopFeature> featureQueryWrapper = MybatisPlusUtil.query();
        featureQueryWrapper.eq("shop_id",shop.getId());
        ShopFeature shopFeature = shopFeatureRepository.getOne(featureQueryWrapper);
        if(null != shopFeature){
            detailVo.setShopScore(shopFeature.getScore());
        }
        //店铺收藏状态
        if(StringUtils.isNotBlank(dto.getJwtUserId())){
           Integer favoritesState  = bbbUserFavoritesShopRpc.innerFavoritesState(dto,shop.getId(),dto.getJwtUserId());
           if(null != favoritesState){
               detailVo.setFavoriteState(favoritesState);
           }
        }

        return detailVo;
    }

    @Override
    public List<BbbShopVO.ComplexDetailVO> innerListComplexDetailShop(List<String> shopIdList,BaseDTO dto) {
        if(ObjectUtils.isEmpty(shopIdList)){
            return null;
        }
        List<Shop> shopList = repository.listByIds(shopIdList);
        if(null == shopList){
            return new ArrayList<>();
        }
        if(StringUtils.isNotBlank(dto.getJwtUserId())){
            List<BbbShopVO.ComplexDetailVO> voList = new ArrayList<>();
            if(ObjectUtils.isNotEmpty(shopList)){
                List<String> checkShopIdList = new ArrayList<>();
                for(Shop shop:shopList){
                    checkShopIdList.add(shop.getId());
                    BbbShopVO.ComplexDetailVO shopInfoVo = new BbbShopVO.ComplexDetailVO();
                    BeanUtils.copyProperties(shop,shopInfoVo);
                    voList.add(shopInfoVo);
                }
                List<String> favoritesShopIdList =  bbbUserFavoritesShopRpc.innerFavoritesListState(dto,checkShopIdList,dto.getJwtUserId());
                for(BbbShopVO.ComplexDetailVO shopInfoVo:voList){
                    if(favoritesShopIdList.contains(shopInfoVo.getId())){
                        shopInfoVo.setFavoriteState(TrueFalseEnum.是.getCode());
                    }
                }
            }
            return voList;
        }else{
            return  ListUtil.listCover(BbbShopVO.ComplexDetailVO.class,shopList);
        }

    }

    @Override
    public List<BbbShopVO.ShopNavigationIdName> innerListShopNavigation(ShopDTO.IdDTO dto) {
        QueryWrapper<ShopNavigation> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("shop_id",dto.getId());
        List<ShopNavigation> shopNavigationList = shopNavigationRepository.list(queryWrapper);
        Map<String,BbbShopVO.ShopNavigationIdName> utilMap = new HashMap<>();
        for(ShopNavigation shopNavigation:shopNavigationList){
            if(shopNavigation.getLeve().equals(MerchShopNavigationEnum.一级分类.getCode())){
                BbbShopVO.ShopNavigationIdName shopNavigationIdName =  utilMap.get(shopNavigation.getId());
                if(null == shopNavigationIdName){
                    shopNavigationIdName = new BbbShopVO.ShopNavigationIdName();
                    shopNavigationIdName.setId(shopNavigation.getId());
                    shopNavigationIdName.setNavName(shopNavigation.getNavName());
                    utilMap.put(shopNavigation.getId(),shopNavigationIdName);
                }
            }
        }
        for(ShopNavigation item:shopNavigationList){
            if(MerchShopNavigationEnum.二级分类.getCode().equals(item.getLeve())){
                BbbShopVO.ShopNavigationIdName shopNavigationIdName =  utilMap.get(item.getParentId());
                if(ObjectUtils.isNull(shopNavigationIdName)){
                    throw new BusinessException("二级分类的父ID无效");
                }
                BbbShopVO.ShopNavigationChildIdName childIdName = new BbbShopVO.ShopNavigationChildIdName();
                childIdName.setId(item.getId());
                childIdName.setNavName(item.getNavName());
                shopNavigationIdName.getNavigationList().add(childIdName);
            }
        }
        return new ArrayList<>(utilMap.values());
    }

    @Override
    public List<BbbShopVO.ShopIdName> innerListShopIdName(BbbShopDTO.MerchantIdDTO dto) {
        QueryWrapper<Shop> wrapper = MybatisPlusUtil.query();
        wrapper.eq("merchant_id",dto.getId());
        List<Shop> dataList = repository.list(wrapper);
        return ListUtil.listCover( BbbShopVO.ShopIdName.class,dataList);
    }

}
