package com.gs.lshly.biz.support.merchant.service.merchadmin.h5.impl;

import cn.hutool.core.lang.Console;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.merchant.entity.Shop;
import com.gs.lshly.biz.support.merchant.entity.ShopNavigation;
import com.gs.lshly.biz.support.merchant.enums.MerchShopNavigationEnum;
import com.gs.lshly.biz.support.merchant.repository.IShopNavigationRepository;
import com.gs.lshly.biz.support.merchant.repository.IShopRepository;
import com.gs.lshly.biz.support.merchant.service.merchadmin.h5.IH5MerchShopNavigationService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.qto.H5MerchShopNavigationQTO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.qto.H5MerchShopQTO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.vo.H5MerchMerchantAccountVO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.vo.H5MerchShopNavigationVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
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
* @author zst
* @since 2021-1-27
*/
@Component
public class H5MerchShopNavigationServiceImpl implements IH5MerchShopNavigationService {

    private static final String DEFAULT_PARENT_ID = "0";
    @Autowired
    private IShopNavigationRepository repository;
    @Autowired
    private IShopRepository iShopRepository;


    @Override
    public List<H5MerchShopNavigationVO.ListVO> list(H5MerchShopNavigationQTO.QTO qto) {
        QueryWrapper<ShopNavigation> wrapper = MybatisPlusUtil.query();
        wrapper.eq("terminal",qto.getTerminal());
        wrapper.eq("shop_id",qto.getJwtShopId());
        List<ShopNavigation> dataList =  repository.list(wrapper);
        Map<String,H5MerchShopNavigationVO.ListVO> utilMap = new HashMap<>();
        for(ShopNavigation item:dataList){
            if(MerchShopNavigationEnum.一级分类.getCode().equals(item.getLeve())){
                H5MerchShopNavigationVO.ListVO parentItem =  utilMap.get(item.getId());
                if(ObjectUtils.isNull(parentItem)){
                    parentItem = new H5MerchShopNavigationVO.ListVO();
                    parentItem.setChildList(new ArrayList<>());
                    utilMap.put(item.getId(),parentItem);
                }
                BeanUtils.copyProperties(item,parentItem);
            }
        }

        for(ShopNavigation item:dataList){
            if(MerchShopNavigationEnum.二级分类.getCode().equals(item.getLeve())){
                H5MerchShopNavigationVO.ListVO parentItem =  utilMap.get(item.getParentId());
                if(ObjectUtils.isNull(parentItem)){
                    Console.log("二级分类:{},{},{}",item.getId(),item.getNavName(),item.getParentId());
                   throw new BusinessException("二级分类的父ID无效");
                }
                H5MerchShopNavigationVO.ChildVO childItem = new H5MerchShopNavigationVO.ChildVO();
                BeanUtils.copyProperties(item,childItem);
                parentItem.getChildList().add(childItem);
            }
        }
        List<H5MerchShopNavigationVO.ListVO> voList = new ArrayList<>(utilMap.values());
        voList.sort((a, b) -> a.getIdx() - b.getIdx());
        for(H5MerchShopNavigationVO.ListVO listItem:voList){
            listItem.getChildList().sort((a,b)-> a.getIdx() - b.getIdx());
        }
        return voList;
    }

    @Override
    public List<H5MerchShopNavigationVO.NavigationVO> listLevel001(BaseDTO dto) {
        QueryWrapper<ShopNavigation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("shop_id",dto.getJwtShopId());
        queryWrapper.eq("leve",MerchShopNavigationEnum.一级分类.getCode());
        List<ShopNavigation> shopNavigationList =  repository.list(queryWrapper);
        if(ObjectUtils.isEmpty(shopNavigationList)){
            return new ArrayList<>();
        }
        return ListUtil.listCover(H5MerchShopNavigationVO.NavigationVO.class,shopNavigationList);
    }

    @Override
    public H5MerchMerchantAccountVO.checkShopByShopId checkShopByShopId(H5MerchShopQTO.CutShopQTO qto) {
        H5MerchMerchantAccountVO.checkShopByShopId checkShopVO = new H5MerchMerchantAccountVO.checkShopByShopId();

        if(ObjectUtils.isEmpty(qto.getShopId())){
          throw new BusinessException("店铺ID为空");
        }
        QueryWrapper<Shop> wrapper = new QueryWrapper<>();
        Shop shop = iShopRepository.getOne(wrapper.eq("id",qto.getShopId()));

        checkShopVO.setShopId(shop.getId())
                .setJwtMerchantId(shop.getMerchantId())
                .setShopSimpleVO(packShopDate(shop));

        return checkShopVO;
    }

    @Override
    public H5MerchMerchantAccountVO.CheckShopVO checkShop(BaseDTO dto) {
        H5MerchMerchantAccountVO.CheckShopVO checkShopVO = new H5MerchMerchantAccountVO.CheckShopVO();
        checkShopVO.setShopId(dto.getJwtShopId());
        return checkShopVO;
    }

    private H5MerchMerchantAccountVO.ShopSimpleVO packShopDate(Shop shop){
        H5MerchMerchantAccountVO.ShopSimpleVO shopSimpleVO = new H5MerchMerchantAccountVO.ShopSimpleVO();

        shopSimpleVO.setShopName(shop.getShopName());
        shopSimpleVO.setShareCode(shop.getShareCode());
        shopSimpleVO.setShopLogo(shop.getShopLogo());
        shopSimpleVO.setShopType(shop.getShopType());
        shopSimpleVO.setPosShopId(shop.getPosShopId());
        shopSimpleVO.setShopDesc(shop.getShopDesc());

        return shopSimpleVO;
    }


}
