package com.gs.lshly.biz.support.merchant.service.common.impl;

import cn.hutool.core.lang.Console;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.merchant.entity.*;
import com.gs.lshly.biz.support.merchant.enums.MerchShopNavigationEnum;
import com.gs.lshly.biz.support.merchant.repository.*;
import com.gs.lshly.biz.support.merchant.service.common.ICommonShopService;
import com.gs.lshly.common.enums.EdAbleStateEnum;
import com.gs.lshly.common.enums.GoodsCategoryLevelEnum;
import com.gs.lshly.common.enums.ShopTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.CommonShopDTO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsCategoryVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.IpUtil;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsCategoryRpc;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-13
*/
@Component
public class CommonShopServiceImpl implements ICommonShopService {

    @Autowired
    private IShopRepository repository;
    @Autowired
    private IMerchantRepository merchantRepository;
    @Autowired
    private IShopNavigationRepository shopNavigationRepository;
    @Autowired
    private IShopGoodsCategoryRepository shopGoodsCategoryRepository;
    @Autowired
    private IShopServiceRepository shopServiceRepository;
    @Autowired
    private IShopVisitsRepository iShopVisitsRepository;

    @DubboReference
    private IPCMerchAdminGoodsCategoryRpc categoryRpc;

    @Override
    public CommonShopVO.SimpleVO shopDetails(String shopId) {
        if(StringUtils.isBlank(shopId)){
            return null;
        }
        QueryWrapper<Shop> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("id",shopId);
        Shop shop = repository.getOne(queryWrapper);
        if(ObjectUtils.isEmpty(shop)){
            return null;
        }
        CommonShopVO.SimpleVO detailVo = new CommonShopVO.SimpleVO();
        BeanCopyUtils.copyProperties(shop, detailVo);
        return detailVo;
    }

    @Override
    public List<CommonShopVO.ShopIdNameVO> moreDetailShop(List<String> shopIdList) {
        if(ObjectUtils.isEmpty(shopIdList)){
            return new ArrayList<>();
        }
        List<Shop> shopList = repository.listByIds(shopIdList);
        if(null == shopList){
            return new ArrayList<>();
        }
        return  ListUtil.listCover(CommonShopVO.ShopIdNameVO.class,shopList);
    }

    @Override
    public CommonShopVO.ShopIdNameVO oneDetailShop(String shopId) {
        Shop shop =  repository.getById(shopId);
        if(null == shop){
           return null;
        }
        CommonShopVO.ShopIdNameVO shopIdNameVO = new CommonShopVO.ShopIdNameVO();
        BeanUtils.copyProperties(shop,shopIdNameVO);
        return shopIdNameVO;
    }

    @Override
    public List<CommonShopVO.SimpleVO> searchDetailShop(String shopFeatures) {
        if(StringUtils.isBlank(shopFeatures)){
            return new ArrayList<>();
        }
        QueryWrapper<Shop> queryWrapper  = new QueryWrapper<>();
        queryWrapper.like("shop_name",shopFeatures);
        queryWrapper.or();
        queryWrapper.like("shop_type_name",shopFeatures);
        List<Shop> shopList = repository.list(queryWrapper);
        return ListUtil.listCover(CommonShopVO.SimpleVO.class,shopList);
    }

    @Override
    public CommonShopVO.NavigationVO shopNavigation(String id2) {
        ShopNavigation shopNavigation2 =  shopNavigationRepository.getById(id2);
        if(null != shopNavigation2){
            ShopNavigation shopNavigation =  shopNavigationRepository.getById(shopNavigation2.getParentId());
            CommonShopVO.NavigationVO navigationVO  = new CommonShopVO.NavigationVO();
            if (null != shopNavigation){
                navigationVO.setNavigationId(StringUtils.isBlank(shopNavigation.getId())?"":shopNavigation.getId());
                navigationVO.setNavigationName(StringUtils.isBlank(shopNavigation.getNavName())?"":shopNavigation.getNavName());

            }
            navigationVO.setNavigationTowId(shopNavigation2.getId());
            navigationVO.setNavigationTowName(shopNavigation2.getNavName());
            return  navigationVO;
        }
        return null;
    }

    @Override
    public CommonShopVO.NavigationVO getNavigationVO(CommonShopDTO.NavigationByDTO dto) {
        QueryWrapper<ShopNavigation> wrapper = MybatisPlusUtil.query();
        wrapper.eq("nav_name",dto.getNavName());
        wrapper.eq("leve",dto.getLevel());
        wrapper.eq("shop_id",dto.getShopId());
        if (ObjectUtils.isNotEmpty(dto.getTerminal())){
            wrapper.eq("terminal",dto.getTerminal());
        }
        wrapper.last("limit 0,1");
        ShopNavigation navigation = shopNavigationRepository.getOne(wrapper);
        if(null != navigation){
            CommonShopVO.NavigationVO navigationVO  = new CommonShopVO.NavigationVO();
            navigationVO.setNavigationTowId(navigation.getId());
            navigationVO.setNavigationTowName(navigation.getNavName());
            return  navigationVO;
        }
        return null;
    }

    @Override
    public List<CommonShopVO.NavigationListVO> shopNavigation(CommonShopDTO.NavigationDTO dto) {
        QueryWrapper<ShopNavigation> wrapper = MybatisPlusUtil.query();
        wrapper.eq("terminal",dto.getTerminal());
        if(StringUtils.isNotBlank(dto.getJwtShopId())){
            wrapper.eq("shop_id",dto.getJwtShopId());
        }
        List<ShopNavigation> dataList =  shopNavigationRepository.list(wrapper);
        Map<String,CommonShopVO.NavigationListVO> utilMap = new HashMap<>();
        for(ShopNavigation item:dataList){
            if(MerchShopNavigationEnum.一级分类.getCode().equals(item.getLeve())){
                CommonShopVO.NavigationListVO parentItem =  utilMap.get(item.getId());
                if(ObjectUtils.isNull(parentItem)){
                    parentItem = new CommonShopVO.NavigationListVO();
                    parentItem.setChildList(new ArrayList<>());
                    utilMap.put(item.getId(),parentItem);
                }
                BeanUtils.copyProperties(item,parentItem);
            }
        }

        for(ShopNavigation item:dataList){
            if(MerchShopNavigationEnum.二级分类.getCode().equals(item.getLeve())){
                CommonShopVO.NavigationListVO parentItem =  utilMap.get(item.getParentId());
                if(ObjectUtils.isNull(parentItem)){
                    Console.log("二级分类:{},{},{}",item.getId(),item.getNavName(),item.getParentId());
                    throw new BusinessException("二级分类的父ID无效");
                }
                CommonShopVO.NavigationChildVO childItem = new CommonShopVO.NavigationChildVO();
                BeanUtils.copyProperties(item,childItem);
                parentItem.getChildList().add(childItem);
            }
        }
        List<CommonShopVO.NavigationListVO> voList = new ArrayList<>(utilMap.values());
        voList.sort((a, b) -> a.getIdx() - b.getIdx());
        for(CommonShopVO.NavigationListVO listItem:voList){
            listItem.getChildList().sort((a,b)-> a.getIdx() - b.getIdx());
        }
        return voList;
    }

    @Override
    public CommonShopVO.MerchantVO merchantByShopId(String shopId) {
       Shop shop =  repository.getById(shopId);
       if(null == shop){
           return null;
       }
       Merchant merchant =  merchantRepository.getById(shop.getMerchantId());
       if(null == merchant){
           return null;
       }
       CommonShopVO.MerchantVO merchantVO = new CommonShopVO.MerchantVO();
       merchantVO.setShopId(shop.getId());
       merchantVO.setShopName(shop.getShopName());
       merchantVO.setShopType(shop.getShopType());
       merchantVO.setMerchantId(merchant.getId());
       merchantVO.setMerchantName(merchant.getMerchantName());
       merchantVO.setIsPlatform(merchant.getIsPlatform());
       return merchantVO;
    }

    @Override
    public List<CommonShopVO.SimpleDetailVO> listSimpleByShopIdList(List<String> shopIdList) {
        List<CommonShopVO.SimpleDetailVO> voList = new ArrayList<>();
        if(ObjectUtils.isEmpty(shopIdList)){
            return voList;
        }
        List<Shop> shopList = repository.listByIds(shopIdList);
        if(null == shopList){
            return voList;
        }
        for(Shop shop:shopList){
            CommonShopVO.SimpleDetailVO simpleDetailVO = new CommonShopVO.SimpleDetailVO();
            BeanUtils.copyProperties(shop,simpleDetailVO);
            voList.add(simpleDetailVO);
        }
        return voList;
    }

    @Override
    public CommonShopVO.MerchantVO merchantWithCategoryByShopId(String shopId) {
        Shop shop =  repository.getById(shopId);
        if(null == shop){
            return null;
        }
        Merchant merchant =  merchantRepository.getById(shop.getMerchantId());
        if(null == merchant){
            return null;
        }
        CommonShopVO.MerchantVO merchantVO = new CommonShopVO.MerchantVO();
        merchantVO.setMerchantName(merchant.getMerchantName());
        merchantVO.setIsPlatform(merchant.getIsPlatform());
        merchantVO.setShopType(shop.getShopType());
        if(!ShopTypeEnum.运营商自营.getCode().equals(shop.getShopType())){
            QueryWrapper<ShopGoodsCategory> queryWrapperCategoryL1 = MybatisPlusUtil.query();
            queryWrapperCategoryL1.eq("shop_id",shop.getId());
            queryWrapperCategoryL1.eq("category_leve", GoodsCategoryLevelEnum.ONE.getCode());
            List<ShopGoodsCategory> goodsCategoryList = shopGoodsCategoryRepository.list(queryWrapperCategoryL1);
            if(ObjectUtils.isNotEmpty(goodsCategoryList)){
                for(ShopGoodsCategory goodsCategory:goodsCategoryList){
                    CommonShopVO.CategoryVO categoryVO = new CommonShopVO.CategoryVO();
                    categoryVO.setGoodsCategoryId(goodsCategory.getCategoryId());
                    categoryVO.setGoodsCategoryName(goodsCategory.getCategoryName());
                    merchantVO.getCategoryList().add(categoryVO);
                }
            }
        }
        return merchantVO;
    }

    @Override
    public String lakalaNoByShopId(String shopId) {
        Shop shop =  repository.getById(shopId);
        if(null == shop){
            return null;
        }
        Merchant merchant =  merchantRepository.getById(shop.getMerchantId());
        if(null == merchant){
            return null;
        }
        return merchant.getLakalaNo();
    }

    @Override
    public List<CommonShopVO.MerchantListVO> merchantList(BaseDTO dto) {
        List<Merchant> merchantList =  merchantRepository.list();
        return ListUtil.listCover(CommonShopVO.MerchantListVO.class,merchantList);
    }

    @Override
    public List<PCMerchGoodsInfoVO.ShopCategoryGoodsVO> listLevelCategories(BaseDTO dto) {
        QueryWrapper<ShopGoodsCategory> wrapper = MybatisPlusUtil.query();
        wrapper.eq("shop_id",dto.getJwtShopId());
        wrapper.eq("merchant_id",dto.getJwtMerchantId());
        wrapper.eq("category_leve",GoodsCategoryLevelEnum.ONE.getCode());
        List<ShopGoodsCategory> shopGoodsCategories = shopGoodsCategoryRepository.list(wrapper);
        if (ObjectUtils.isEmpty(shopGoodsCategories)){
            return new ArrayList<>();
        }
        List<PCMerchGoodsInfoVO.ShopCategoryGoodsVO> level1Categories = shopGoodsCategories.parallelStream().map(e ->{
            PCMerchGoodsInfoVO.ShopCategoryGoodsVO shopCategoryGoodsVO = new PCMerchGoodsInfoVO.ShopCategoryGoodsVO();
            shopCategoryGoodsVO.setLevelId(e.getCategoryId());
            shopCategoryGoodsVO.setLevelParentId(StringUtils.isEmpty(e.getCategoryPid())?"":e.getCategoryPid());
            shopCategoryGoodsVO.setLevelName(e.getCategoryName());
            shopCategoryGoodsVO.setLevel2CategoryGoodsVOS(new ArrayList<>());
            return shopCategoryGoodsVO;
        }).collect(Collectors.toList());
        return level1Categories;
    }

    @Override
    public CommonShopVO.ShopGoodsCategoryVO innerGetGoodsCategoryVO(String shopId, String categoryName) {
        QueryWrapper<ShopGoodsCategory> wrapper = MybatisPlusUtil.query();
        wrapper.eq("shop_id",shopId);
        wrapper.eq("category_name",categoryName);
        wrapper.select("id","category_id","category_name","category_pid");
        wrapper.last("limit 0,1");
        ShopGoodsCategory category = shopGoodsCategoryRepository.getOne(wrapper);
        if (ObjectUtils.isEmpty(category)){
            return null;
        }
        CommonShopVO.ShopGoodsCategoryVO detailVO = new CommonShopVO.ShopGoodsCategoryVO();
        BeanUtils.copyProperties(category,detailVO);
        return detailVO;
    }

    @Override
    public int checkShopNavigation(String shopId, String shopNavigationName, Integer usefiled) {
        QueryWrapper<ShopNavigation> wrapper = MybatisPlusUtil.query();
        wrapper.eq("shop_id",shopId);
        wrapper.eq("nav_name",shopNavigationName);
        wrapper.eq("terminal",usefiled);
        int count = shopNavigationRepository.count(wrapper);
        return count;
    }

    @Override
    public CommonShopVO.ShopServiceVO getShopServiceVO(BaseDTO dto) {
        QueryWrapper<ShopService> wrapper = MybatisPlusUtil.query();
        wrapper.eq("shop_id",dto.getJwtShopId());
        ShopService shopService = shopServiceRepository.getOne(wrapper);
        if (null == shopService){
            return null;
        }
        CommonShopVO.ShopServiceVO shopServiceVO = new CommonShopVO.ShopServiceVO();
        BeanUtils.copyProperties(shopService,shopServiceVO);
        return shopServiceVO;
    }

    @Override
    public CommonShopVO.ShopServiceOutVO getShopService(String shopId) {
        QueryWrapper<ShopService> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("shop_id",shopId);
        ShopService shopService = shopServiceRepository.getOne(queryWrapper);
        CommonShopVO.ShopServiceOutVO shopServiceVO = new CommonShopVO.ShopServiceOutVO();
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
    public CommonShopVO.SimpleVO innerServiceByShareCode(String shareCode) {
        QueryWrapper<Shop> wrapper = MybatisPlusUtil.query();
        wrapper.eq("share_code",shareCode);
        Shop shop = repository.getOne(wrapper);
        if (ObjectUtils.isNotEmpty(shop)){
            CommonShopVO.SimpleVO simpleVO = new CommonShopVO.SimpleVO();
            BeanUtils.copyProperties(shop,simpleVO);
            return simpleVO;
        }
        return null;
    }

    @Override
    public List<String> shopNavigationIdList(String navigationId) {
        List<String> idList = new ArrayList<>();
        ShopNavigation shopNavigation = shopNavigationRepository.getById(navigationId);
        if (ObjectUtils.isEmpty(shopNavigation)){
            throw new BusinessException("店铺类目数据查询异常！或该店铺类目不存在！");
        }
        QueryWrapper<ShopNavigation> wrapper = MybatisPlusUtil.query();
        if (shopNavigation.getLeve().equals(10)){
            wrapper.eq("parent_id",navigationId);
            List<ShopNavigation> shopNavigations = shopNavigationRepository.list(wrapper);
            if(ObjectUtils.isEmpty(shopNavigations)){
                return new ArrayList<>();
            }
            idList = ListUtil.getIdList(ShopNavigation.class,shopNavigations);

        }else {
            idList.add(navigationId);
        }
        return idList;
    }

    @Override
    public CommonShopVO.ListVO innerShopInfo(String shopId) {
        if (StringUtils.isBlank(shopId)){
            throw new BusinessException("店铺id为空，异常");
        }
        Shop shop = repository.getById(shopId);
        if (null == shop){
            throw new BusinessException("店铺不存在，异常！");
        }
        CommonShopVO.ListVO listVO = new CommonShopVO.ListVO();
        BeanUtils.copyProperties(shop,listVO);
        return listVO;
    }

    @Override
    public CommonShopVO.ShopCategoryInfoVO innerShopCategoryInfoVO(String shopId) {
        if (StringUtils.isBlank(shopId)){
            throw new BusinessException("店铺id为空，异常");
        }
        QueryWrapper<ShopGoodsCategory> wrapper = MybatisPlusUtil.query();
        wrapper.eq("category_leve",GoodsCategoryLevelEnum.ONE.getCode());
        wrapper.eq("shop_id",shopId);
        List<ShopGoodsCategory> shopGoodsCategories = shopGoodsCategoryRepository.list(wrapper);
        if (ObjectUtils.isNotEmpty(shopGoodsCategories)){
            CommonShopVO.ShopCategoryInfoVO shopCategoryInfoVO = new CommonShopVO.ShopCategoryInfoVO();
            BigDecimal price = new BigDecimal("0");
            StringBuffer stringBuffer = new StringBuffer();

            for (ShopGoodsCategory category : shopGoodsCategories){
                stringBuffer.append(category.getCategoryName()+",");
                if (ObjectUtils.isNotEmpty(category.getSharePrice())){
                    price = price.add(category.getSharePrice());
                }
            }
            shopCategoryInfoVO.setCategoryName(stringBuffer.toString().substring(0, stringBuffer.toString().lastIndexOf(",")));
            shopCategoryInfoVO.setSharePrice(price);

            return shopCategoryInfoVO;

        }
        return null;
    }

    @Override
    public void visits(CommonShopDTO.VisitsDTO shopId) {
        if (StringUtils.isNotBlank(shopId.getShopId())){
            ShopVisits shopVisits = new ShopVisits();
            shopVisits.setShopId(shopId.getShopId());
            if (ObjectUtils.isNotEmpty(shopId.getJwtUserId())){
                shopVisits.setUserId(shopId.getJwtUserId());
            }
            if(ObjectUtils.isNotEmpty(IpUtil.getHttpServletRequest())){
                shopVisits.setIp(IpUtil.getRemoteHost(IpUtil.getHttpServletRequest()));
            }else{
                shopVisits.setIp("127.0.0.1");
            }
            iShopVisitsRepository.save(shopVisits);
        }
    }

}
