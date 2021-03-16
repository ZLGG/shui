package com.gs.lshly.biz.support.merchant.service.merchadmin.pc.impl;

import cn.hutool.core.lang.Console;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gs.lshly.biz.support.merchant.entity.ShopNavigation;
import com.gs.lshly.biz.support.merchant.entity.ShopNavigationMenu;
import com.gs.lshly.biz.support.merchant.enums.MerchShopNavigationEnum;
import com.gs.lshly.biz.support.merchant.repository.IShopNavigationMenuRepository;
import com.gs.lshly.biz.support.merchant.repository.IShopNavigationRepository;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchShopNavigationMenuService;
import com.gs.lshly.common.enums.PcH5Enum;
import com.gs.lshly.common.enums.ShopNavigationMenuTypeEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopNavigationMenuDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopNavigationMenuQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopNavigationQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopNavigationMenuVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopNavigationVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-11-04
*/
@Component
public class PCMerchShopNavigationMenuServiceImpl implements IPCMerchShopNavigationMenuService {

    @Autowired
    private IShopNavigationMenuRepository repository;

    @Autowired
    private IShopNavigationRepository shopNavigationRepository;


    @Override
    public List<PCMerchShopNavigationMenuVO.H5ListVO> h5List(PCMerchShopNavigationMenuQTO.H5QTO qto) {
        QueryWrapper<ShopNavigationMenu> queryWrapper  = MybatisPlusUtil.query();
        queryWrapper.eq("menu_type", ShopNavigationMenuTypeEnum.自定义图文.getCode());
        queryWrapper.eq("terminal",qto.getTerminal());
        queryWrapper.eq("pc_show", PcH5Enum.NO.getCode());
        queryWrapper.eq("shop_id",qto.getJwtShopId());
        List<ShopNavigationMenu> shopNavigationMenuList = repository.list(queryWrapper);
        return ListUtil.listCover(PCMerchShopNavigationMenuVO.H5ListVO.class,shopNavigationMenuList);
    }

    @Override
    public void h5Editor(PCMerchShopNavigationMenuDTO.H5ETO eto) {
        if(ObjectUtils.isNotEmpty(eto.getItemList())){
            List<ShopNavigationMenu> batchList = new ArrayList<>();
            for(PCMerchShopNavigationMenuDTO.H5ItemETO h5ItemETO:eto.getItemList()){
                ShopNavigationMenu shopNavigationMenu = new ShopNavigationMenu();
                BeanCopyUtils.copyProperties(h5ItemETO,shopNavigationMenu);
                shopNavigationMenu.setMenuType(ShopNavigationMenuTypeEnum.自定义图文.getCode());
                shopNavigationMenu.setPcShow(PcH5Enum.NO.getCode());
                shopNavigationMenu.setTerminal(eto.getTerminal());
                shopNavigationMenu.setShopId(eto.getJwtShopId());
                shopNavigationMenu.setMerchantId(eto.getJwtMerchantId());
                if(TrueFalseEnum.是.getCode().equals(h5ItemETO.getIsNew())){
                    shopNavigationMenu.setId(null);
                }else{
                    if(ObjectUtils.isNotEmpty(eto.getRemoveList())){
                       if(eto.getRemoveList().contains(h5ItemETO.getId())){
                           throw new BusinessException("保存的数据与删除的数据冲突");
                       }
                    }
                }
                batchList.add(shopNavigationMenu);
            }
            repository.saveOrUpdateBatch(batchList);
        }
        if(ObjectUtils.isNotEmpty(eto.getRemoveList())){
            QueryWrapper<ShopNavigationMenu> queryWrapper = MybatisPlusUtil.query();
            queryWrapper.in("id",eto.getRemoveList());
            repository.remove(queryWrapper);
        }
    }

    @Override
    public List<PCMerchShopNavigationMenuVO.H5TextMenuListVO> h5TextMenuList(PCMerchShopNavigationMenuQTO.H5TextMenuQTO qto) {
        QueryWrapper<ShopNavigationMenu> queryWrapper  = MybatisPlusUtil.query();
        queryWrapper.eq("menu_type", ShopNavigationMenuTypeEnum.自定义文本.getCode());
        queryWrapper.eq("terminal",qto.getTerminal());
        queryWrapper.eq("pc_show", PcH5Enum.NO.getCode());
        queryWrapper.eq("shop_id",qto.getJwtShopId());
        List<ShopNavigationMenu> shopNavigationMenuList = repository.list(queryWrapper);
        return ListUtil.listCover(PCMerchShopNavigationMenuVO.H5TextMenuListVO.class,shopNavigationMenuList);
    }

    @Override
    public void h5TextMenuEditor(PCMerchShopNavigationMenuDTO.H5TextMenuETO eto) {
        if(ObjectUtils.isNotEmpty(eto.getItemList())){
            List<ShopNavigationMenu> batchList = new ArrayList<>();
            for(PCMerchShopNavigationMenuDTO.H5TextMenuItemETO h5ItemETO:eto.getItemList()){
                ShopNavigationMenu shopNavigationMenu = new ShopNavigationMenu();
                BeanCopyUtils.copyProperties(h5ItemETO,shopNavigationMenu);
                shopNavigationMenu.setMenuType(ShopNavigationMenuTypeEnum.自定义文本.getCode());
                shopNavigationMenu.setPcShow(PcH5Enum.NO.getCode());
                shopNavigationMenu.setTerminal(eto.getTerminal());
                shopNavigationMenu.setShopId(eto.getJwtShopId());
                shopNavigationMenu.setMerchantId(eto.getJwtMerchantId());
                if(TrueFalseEnum.是.getCode().equals(h5ItemETO.getIsNew())){
                    shopNavigationMenu.setId(null);
                }else{
                    if(ObjectUtils.isNotEmpty(eto.getRemoveList())){
                        if(eto.getRemoveList().contains(h5ItemETO.getId())){
                            throw new BusinessException("保存的数据与删除的数据冲突");
                        }
                    }
                }
                batchList.add(shopNavigationMenu);
            }
            repository.saveOrUpdateBatch(batchList);
        }
        if(ObjectUtils.isNotEmpty(eto.getRemoveList())){
            QueryWrapper<ShopNavigationMenu> queryWrapper = MybatisPlusUtil.query();
            queryWrapper.in("id",eto.getRemoveList());
            repository.remove(queryWrapper);
        }
    }

    @Override
    public PCMerchShopNavigationMenuVO.PcShopMenuVO pcShopMenuList(PCMerchShopNavigationMenuQTO.PCShopMenuQTO qto) {

        PCMerchShopNavigationMenuVO.PcShopMenuVO pcShopMenuVO = new PCMerchShopNavigationMenuVO.PcShopMenuVO();
        QueryWrapper<ShopNavigationMenu> queryWrapper  = MybatisPlusUtil.query();
        queryWrapper.eq("menu_type", ShopNavigationMenuTypeEnum.自定义文本.getCode());
        queryWrapper.eq("pc_show", PcH5Enum.YES.getCode());
        queryWrapper.eq("terminal",qto.getTerminal());
        queryWrapper.eq("shop_id",qto.getJwtShopId());
        List<ShopNavigationMenu> textMenuList = repository.list(queryWrapper);
        pcShopMenuVO.setTextMenuList(ListUtil.listCover( PCMerchShopNavigationMenuVO.PcTextMenuVO.class,textMenuList));
        //查店铺的自定认分类
        pcShopMenuVO.setNavMenuList(this.getShopNavigation(qto));
        return pcShopMenuVO;
    }

    private List<PCMerchShopNavigationMenuVO.PcNavMenuVO> getShopNavigation(PCMerchShopNavigationMenuQTO.PCShopMenuQTO qto){
        QueryWrapper<ShopNavigation> wrapper = MybatisPlusUtil.query();
        wrapper.eq("terminal",qto.getTerminal());
        wrapper.eq("shop_id",qto.getJwtShopId());
        List<ShopNavigation> dataList =  shopNavigationRepository.list(wrapper);
        Map<String,PCMerchShopNavigationMenuVO.PcNavMenuVO> utilMap = new HashMap<>();
        for(ShopNavigation item:dataList){
            if(MerchShopNavigationEnum.一级分类.getCode().equals(item.getLeve())){
                PCMerchShopNavigationMenuVO.PcNavMenuVO parentItem =  utilMap.get(item.getId());
                if(ObjectUtils.isNull(parentItem)){
                    parentItem = new PCMerchShopNavigationMenuVO.PcNavMenuVO();
                    parentItem.setChildList(new ArrayList<>());
                    utilMap.put(item.getId(),parentItem);
                }
                BeanUtils.copyProperties(item,parentItem);
            }
        }
        for(ShopNavigation item:dataList){
            if(MerchShopNavigationEnum.二级分类.getCode().equals(item.getLeve())){
                PCMerchShopNavigationMenuVO.PcNavMenuVO parentItem =  utilMap.get(item.getParentId());
                if(ObjectUtils.isNull(parentItem)){
                    Console.log("二级分类:{},{},{}",item.getId(),item.getNavName(),item.getParentId());
                    throw new BusinessException("二级分类的父ID无效");
                }
                PCMerchShopNavigationMenuVO.PcNavChildMenuVO childItem = new PCMerchShopNavigationMenuVO.PcNavChildMenuVO();
                BeanUtils.copyProperties(item,childItem);
                parentItem.getChildList().add(childItem);
            }
        }
        List<PCMerchShopNavigationMenuVO.PcNavMenuVO> voList = new ArrayList<>(utilMap.values());
        voList.sort(Comparator.comparing(PCMerchShopNavigationMenuVO.PcNavMenuVO::getIdx));
        for(PCMerchShopNavigationMenuVO.PcNavMenuVO listItem:voList){
            listItem.getChildList().sort(Comparator.comparing(PCMerchShopNavigationMenuVO.PcNavChildMenuVO::getIdx));
        }
        return voList;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void pcShopMenuEditor(PCMerchShopNavigationMenuDTO.PCShopMenuETO dto) {
        this.shopTextMenuEditor(dto);
        this.shopNavigationToMenu(dto);
    }

    private void shopTextMenuEditor(PCMerchShopNavigationMenuDTO.PCShopMenuETO dto){
        QueryWrapper<ShopNavigationMenu> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("menu_type", ShopNavigationMenuTypeEnum.自定义文本.getCode());
        queryWrapper.eq("pc_show",PcH5Enum.YES.getCode());
        queryWrapper.eq("terminal",dto.getTerminal());
        queryWrapper.eq("shop_id",dto.getJwtShopId());
        repository.remove(queryWrapper);
        if(ObjectUtils.isNotEmpty(dto.getTextMenuList())){
            List<ShopNavigationMenu> batchList = new ArrayList<>();
            for(PCMerchShopNavigationMenuDTO.PCTextMenuETO itemETO:dto.getTextMenuList()){
                ShopNavigationMenu shopNavigationMenu = new ShopNavigationMenu();
                BeanCopyUtils.copyProperties(itemETO,shopNavigationMenu);
                shopNavigationMenu.setMenuType(ShopNavigationMenuTypeEnum.自定义文本.getCode());
                shopNavigationMenu.setPcShow(PcH5Enum.YES.getCode());
                shopNavigationMenu.setTerminal(dto.getTerminal());
                shopNavigationMenu.setShopId(dto.getJwtShopId());
                shopNavigationMenu.setMerchantId(dto.getJwtMerchantId());
                batchList.add(shopNavigationMenu);
            }
            repository.saveBatch(batchList);
        }
    }

    private void shopNavigationToMenu(PCMerchShopNavigationMenuDTO.PCShopMenuETO dto){
        //取消旧数据
        UpdateWrapper<ShopNavigation> updateResetWrapper = MybatisPlusUtil.update();
        updateResetWrapper.set("is_menu",TrueFalseEnum.否.getCode());
        updateResetWrapper.eq("terminal",dto.getTerminal());
        updateResetWrapper.eq("shop_id",dto.getJwtShopId());
        shopNavigationRepository.update(updateResetWrapper);
        if(ObjectUtils.isNotEmpty(dto.getNavMenuList())){
            QueryWrapper<ShopNavigation> queryWrapper = MybatisPlusUtil.query();
            queryWrapper.eq("terminal",dto.getTerminal());
            queryWrapper.eq("leve", MerchShopNavigationEnum.二级分类.getCode());
            queryWrapper.eq("shop_id",dto.getJwtShopId());
            queryWrapper.in("id",dto.getNavMenuList());
            List<ShopNavigation> navigationDataList = shopNavigationRepository.list(queryWrapper);
            if(ObjectUtils.isEmpty(navigationDataList) || dto.getNavMenuList().size() != navigationDataList.size()){
                throw new BusinessException("店铺二级分类ID错误");
            }
            List<String> idList = new ArrayList<>();
            for(ShopNavigation shopNavigation:navigationDataList){
                idList.add(shopNavigation.getId());
                if(!idList.contains(shopNavigation.getParentId())){
                    idList.add(shopNavigation.getParentId()) ;
                }
            }
            UpdateWrapper<ShopNavigation> updateWrapper = MybatisPlusUtil.update();
            updateWrapper.set("is_menu",TrueFalseEnum.是.getCode());
            updateWrapper.in("id",idList);
            shopNavigationRepository.update(updateWrapper);
        }
    }
}
