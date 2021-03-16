package com.gs.lshly.biz.support.merchant.service.merchadmin.pc.impl;

import cn.hutool.core.lang.Console;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.merchant.complex.ShopNavigationComplexEditor;
import com.gs.lshly.biz.support.merchant.entity.ShopNavigation;
import com.gs.lshly.biz.support.merchant.enums.MerchShopNavigationEnum;
import com.gs.lshly.biz.support.merchant.repository.IShopNavigationRepository;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchShopNavigationService;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopNavigationDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopNavigationQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopNavigationVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteFloorVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsInfoRpc;
import org.apache.dubbo.config.annotation.DubboReference;
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
* @since 2020-10-18
*/
@Component
public class PCMerchShopNavigationServiceImpl implements IPCMerchShopNavigationService {

    private static final String DEFAULT_PARENT_ID = "0";

    @Autowired
    private IShopNavigationRepository repository;

    @DubboReference
    private IPCMerchAdminGoodsInfoRpc  pCMerchAdminGoodsInfoRpc;

    @Override
    public List<PCMerchShopNavigationVO.ListVO> list(PCMerchShopNavigationQTO.QTO qto) {
        QueryWrapper<ShopNavigation> wrapper = MybatisPlusUtil.query();
        wrapper.eq("terminal",qto.getTerminal());
        wrapper.eq("shop_id",qto.getJwtShopId());
        List<ShopNavigation> dataList =  repository.list(wrapper);
        Map<String,PCMerchShopNavigationVO.ListVO> utilMap = new HashMap<>();
        for(ShopNavigation item:dataList){
            if(MerchShopNavigationEnum.一级分类.getCode().equals(item.getLeve())){
                PCMerchShopNavigationVO.ListVO parentItem =  utilMap.get(item.getId());
                if(ObjectUtils.isNull(parentItem)){
                    parentItem = new PCMerchShopNavigationVO.ListVO();
                    parentItem.setChildList(new ArrayList<>());
                    utilMap.put(item.getId(),parentItem);
                }
                BeanUtils.copyProperties(item,parentItem);
            }
        }

        for(ShopNavigation item:dataList){
            if(MerchShopNavigationEnum.二级分类.getCode().equals(item.getLeve())){
                PCMerchShopNavigationVO.ListVO parentItem =  utilMap.get(item.getParentId());
                if(ObjectUtils.isNull(parentItem)){
                    Console.log("二级分类:{},{},{}",item.getId(),item.getNavName(),item.getParentId());
                   throw new BusinessException("二级分类的父ID无效");
                }
                PCMerchShopNavigationVO.ChildVO childItem = new PCMerchShopNavigationVO.ChildVO();
                BeanUtils.copyProperties(item,childItem);
                parentItem.getChildList().add(childItem);
            }
        }
        List<PCMerchShopNavigationVO.ListVO> voList = new ArrayList<>(utilMap.values());
        voList.sort((a, b) -> a.getIdx() - b.getIdx());
        for(PCMerchShopNavigationVO.ListVO listItem:voList){
            listItem.getChildList().sort((a,b)-> a.getIdx() - b.getIdx());
        }
        return voList;
    }

    @Override
    public List<PCMerchShopNavigationVO.NavigationVO> listLevel001(BaseDTO dto) {
        QueryWrapper<ShopNavigation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("shop_id",dto.getJwtShopId());
        queryWrapper.eq("leve",MerchShopNavigationEnum.一级分类.getCode());
        List<ShopNavigation> shopNavigationList =  repository.list(queryWrapper);
        if(ObjectUtils.isEmpty(shopNavigationList)){
            return new ArrayList<>();
        }
        return ListUtil.listCover(PCMerchShopNavigationVO.NavigationVO.class,shopNavigationList);
    }

    @Override
    public List<PCMerchShopNavigationVO.SimpleVO> listSimple(BaseDTO qto) {
        if(StringUtils.isBlank(qto.getJwtShopId())){
            throw new BusinessException("没有登录");
        }
        QueryWrapper<ShopNavigation> wrapper = MybatisPlusUtil.query();
        wrapper.eq("shop_id",qto.getJwtShopId());
        List<ShopNavigation> dataList =  repository.list(wrapper);
        Map<String,PCMerchShopNavigationVO.SimpleVO> utilMap = new HashMap<>();
        for(ShopNavigation item:dataList){
            if(MerchShopNavigationEnum.一级分类.getCode().equals(item.getLeve())){
                PCMerchShopNavigationVO.SimpleVO parentItem =  utilMap.get(item.getId());
                if(ObjectUtils.isNull(parentItem)){
                    parentItem = new PCMerchShopNavigationVO.SimpleVO();
                    parentItem.setChildList(new ArrayList<>());
                    utilMap.put(item.getId(),parentItem);
                }
                parentItem.setId(item.getId());
                parentItem.setIdx(item.getIdx());
                parentItem.setNavName(item.getNavName());
            }
        }
        for(ShopNavigation item:dataList){
            if(MerchShopNavigationEnum.二级分类.getCode().equals(item.getLeve())){
                PCMerchShopNavigationVO.SimpleVO parentItem =  utilMap.get(item.getParentId());
                if(ObjectUtils.isNull(parentItem)){
                    throw new BusinessException("二级分类的父ID无效");
                }
                PCMerchShopNavigationVO.SimpleChildVO childItem = new PCMerchShopNavigationVO.SimpleChildVO();
                childItem.setId(item.getId());
                childItem.setIdx(item.getIdx());
                childItem.setNavName(item.getNavName());
                parentItem.getChildList().add(childItem);
            }
        }
        List<PCMerchShopNavigationVO.SimpleVO> voList = new ArrayList<>(utilMap.values());
        //排序数据
        voList.sort((a, b) -> a.getIdx() - b.getIdx());
        for(PCMerchShopNavigationVO.SimpleVO listItem:voList){
            listItem.getChildList().sort((a,b)-> a.getIdx() - b.getIdx());
        }
        return voList;
    }

    @Override
    public void deleteShopNavigation(PCMerchShopNavigationDTO.DeleteDTO dto) {
        if(ObjectUtils.isNull(dto.getIdList())){
            throw new BusinessException("删除的ID数组不能这空");
        }
        QueryWrapper<ShopNavigation> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id",dto.getIdList());
        queryWrapper.eq("shop_id",dto.getJwtShopId());
        queryWrapper.eq("terminal",dto.getTerminal());
        List<ShopNavigation> shopNavigationList = repository.list(queryWrapper);
        List<String> oneLevelList = new ArrayList<>();
        List<String> twoLevelList = new ArrayList<>();
        if(ObjectUtils.isNotEmpty(shopNavigationList)){
             for(ShopNavigation shopNavigation:shopNavigationList){
                 if(shopNavigation.getLeve().equals(MerchShopNavigationEnum.一级分类.getCode())){
                     oneLevelList.add(shopNavigation.getId());
                 }else  if(shopNavigation.getLeve().equals(MerchShopNavigationEnum.二级分类.getCode())){
                     twoLevelList.add(shopNavigation.getId());
                 }
             }
        }
        if (ObjectUtils.isNotEmpty(oneLevelList)) {
            //删除的是一级,先查出所有的二级
            QueryWrapper<ShopNavigation> queryChildWrapper = new QueryWrapper<>();
            queryChildWrapper.in("parent_id",oneLevelList);
            List<ShopNavigation> childList = repository.list(queryChildWrapper);
            List<String> childIdList = ListUtil.getIdList(ShopNavigation.class,childList);
            //检查一级下面的二级是不是有商品
            boolean bool = pCMerchAdminGoodsInfoRpc.innerServiceShopNavigation(childIdList);
            if(bool == true){
                throw new BusinessException("店铺自定义分类下有商品");
            }else{
                QueryWrapper<ShopNavigation> removeQueryWrapper = new QueryWrapper<>();
                removeQueryWrapper.in("id",oneLevelList);
                removeQueryWrapper.or();
                removeQueryWrapper.in("parent_id",oneLevelList);
                removeQueryWrapper.eq("shop_id",dto.getJwtShopId());
                repository.remove(removeQueryWrapper);
            }
        }
        //删除的是二级检查是不是有商品
        if (ObjectUtils.isNotEmpty(twoLevelList)) {
            boolean bool = pCMerchAdminGoodsInfoRpc.innerServiceShopNavigation(twoLevelList);
            if(bool == true){
                throw new BusinessException("店铺自定义分类下有商品");
            }else{
                QueryWrapper<ShopNavigation> removeQueryWrapper = new QueryWrapper<>();
                removeQueryWrapper.in("id",twoLevelList);
                removeQueryWrapper.eq("shop_id",dto.getJwtShopId());
                repository.remove(removeQueryWrapper);
            }
        }
    }

    @Override
    public void delete(PCMerchShopNavigationDTO.IdDTO dto) {
        if(StringUtils.isBlank(dto.getId())){
            throw new BusinessException("删除的ID不能为空");
        }
        QueryWrapper<ShopNavigation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",dto.getId());
        queryWrapper.eq("shop_id",dto.getJwtShopId());
        ShopNavigation shopNavigation = repository.getOne(queryWrapper);
        if(null == shopNavigation){
            throw new BusinessException("店铺自定义分类不存在");
        }
        if(MerchShopNavigationEnum.一级分类.getCode().equals(shopNavigation.getLeve())){
            QueryWrapper<ShopNavigation> childQueryWrapper = new QueryWrapper<>();
            childQueryWrapper.eq("parent_id",shopNavigation.getId());
            List<ShopNavigation> childNavigationList = repository.list(childQueryWrapper);
            if(ObjectUtils.isNotEmpty(childNavigationList)){
                List<String> idList = ListUtil.getIdList(ShopNavigation.class,childNavigationList);
                boolean bool = pCMerchAdminGoodsInfoRpc.innerServiceShopNavigation(idList);
                if(bool == true){
                    throw new BusinessException("店铺自定义分类下有商品");
                }else{
                    repository.removeById(shopNavigation.getId());
                    repository.removeByIds(idList);
                }
            }else {
                repository.removeById(shopNavigation.getId());
            }
        }else if(MerchShopNavigationEnum.二级分类.getCode().equals(shopNavigation.getLeve())){
            List<String> idList = new ArrayList<>();
            idList.add(shopNavigation.getId());
            boolean bool = pCMerchAdminGoodsInfoRpc.innerServiceShopNavigation(idList);
            if(bool == true){
                throw new BusinessException("店铺自定义分类下有商品");
            }else{
                repository.removeById(shopNavigation.getId());
            }
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void editShopNavigation(PCMerchShopNavigationDTO.ItemListDTO eto) {
        if(ObjectUtils.isEmpty(eto.getItemList())){
            throw new BusinessException("自定义分类数组不能为空");
        }
        for(PCMerchShopNavigationDTO.ItemDTO itemDTO:eto.getItemList()){
            //是新增 把前端的ID去掉
            if(TrueFalseEnum.是.getCode().equals(itemDTO.getIsNew())){
                itemDTO.setId(null);
            }
            ShopNavigation shopNavigation = new ShopNavigation();
            BeanUtils.copyProperties(itemDTO,shopNavigation);
            shopNavigation.setTerminal(eto.getTerminal());
            shopNavigation.setShopId(eto.getJwtShopId());
            shopNavigation.setMerchantId(eto.getJwtMerchantId());
            shopNavigation.setLeve(MerchShopNavigationEnum.一级分类.getCode());
            repository.saveOrUpdate(shopNavigation);
            if(ObjectUtils.isNotEmpty(itemDTO.getChildList())){
                for(PCMerchShopNavigationDTO.ChildItemDTO childItemDTO:itemDTO.getChildList()){
                    //是新增 把前端的ID去掉
                    if(TrueFalseEnum.是.getCode().equals(childItemDTO.getIsNew())){
                        childItemDTO.setId(null);
                    }
                    ShopNavigation shopNavigation002 = new ShopNavigation();
                    BeanUtils.copyProperties(childItemDTO,shopNavigation002);
                    shopNavigation002.setTerminal(eto.getTerminal());
                    shopNavigation002.setShopId(eto.getJwtShopId());
                    shopNavigation002.setMerchantId(eto.getJwtMerchantId());
                    shopNavigation002.setLeve(MerchShopNavigationEnum.二级分类.getCode());
                    shopNavigation002.setParentId(shopNavigation.getId());
                    repository.saveOrUpdate(shopNavigation002);
                }
            }
        }
    }

    @Override
    public void editShopNavigationToMenu(PCMerchShopNavigationDTO.ToMenuListDTO dto) {

        //取消旧数据
        UpdateWrapper<ShopNavigation> updateResetWrapper = MybatisPlusUtil.update();
        updateResetWrapper.set("is_menu",TrueFalseEnum.否.getCode());
        updateResetWrapper.eq("terminal",dto.getTerminal());
        updateResetWrapper.eq("shop_id",dto.getJwtShopId());
        repository.update(updateResetWrapper);

        if(ObjectUtils.isNotEmpty(dto.getItemList())){
            QueryWrapper<ShopNavigation> queryWrapper = MybatisPlusUtil.query();
            queryWrapper.eq("terminal",dto.getTerminal());
            queryWrapper.eq("leve",MerchShopNavigationEnum.二级分类.getCode());
            queryWrapper.eq("shop_id",dto.getJwtShopId());
            queryWrapper.in("id",dto.getItemList());
            List<ShopNavigation> navigationDataList = repository.list(queryWrapper);
            if(ObjectUtils.isEmpty(navigationDataList) || dto.getItemList().size() != navigationDataList.size()){
                throw new BusinessException("店铺二级分类ID有误");
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
            repository.update(updateWrapper);
        }
    }

    @Override
    public List<PCMerchShopNavigationVO.MenuListVO> menuList(PCMerchShopNavigationQTO.MenuQTO qto) {
        QueryWrapper<ShopNavigation> wrapper = MybatisPlusUtil.query();
        wrapper.eq("terminal",qto.getTerminal());
        wrapper.eq("is_menu",TrueFalseEnum.是.getCode());
        wrapper.eq("leve",MerchShopNavigationEnum.一级分类.getCode());
        wrapper.eq("shop_id",qto.getJwtShopId());
        wrapper.orderByAsc("idx");
        List<ShopNavigation> dataList =  repository.list(wrapper);
        List<PCMerchShopNavigationVO.MenuListVO> menuVoList = new ArrayList<>();
        List<String> idList = new ArrayList<>();
        for(ShopNavigation item:dataList){
            idList.add(item.getId());
            PCMerchShopNavigationVO.MenuListVO menuVo = new PCMerchShopNavigationVO.MenuListVO();
            BeanUtils.copyProperties(item,menuVo);
            menuVoList.add(menuVo);
        }
        if(ObjectUtils.isNotEmpty(idList)){
            QueryWrapper<ShopNavigation> childQueryWrapper = MybatisPlusUtil.query();
            childQueryWrapper.in("parent_id",idList);
            childQueryWrapper.orderByAsc("idx");
            List<ShopNavigation> childShopNavigationList =  repository.list(childQueryWrapper);
            if(ObjectUtils.isNotEmpty(childShopNavigationList)){
                for(ShopNavigation shopNavigation:childShopNavigationList){
                    for(PCMerchShopNavigationVO.MenuListVO menuVo:menuVoList){
                        if(shopNavigation.getParentId().equals(menuVo.getId())){
                            PCMerchShopNavigationVO.MenuChildItemVO menuChildItemVO = new PCMerchShopNavigationVO.MenuChildItemVO();
                            BeanUtils.copyProperties(shopNavigation,menuChildItemVO);
                            menuVo.getChildList().add(menuChildItemVO);
                        }
                    }
                }
            }
        }
        return menuVoList;
    }

    @Override
    public List<PCMerchShopNavigationVO.InnerListVO> innerNavigationList(PCMerchShopNavigationDTO.InnerListDTO dto) {
        QueryWrapper<ShopNavigation> wrapper = MybatisPlusUtil.query();
        wrapper.eq("terminal", TerminalEnum.BBC.getCode());
        List<ShopNavigation> navigationList =  repository.list(wrapper);
        Map<String,PCMerchShopNavigationVO.InnerListVO> utilMap = new HashMap<>();
        for(ShopNavigation item:navigationList){
            if(MerchShopNavigationEnum.一级分类.getCode().equals(item.getLeve())){
                PCMerchShopNavigationVO.InnerListVO innerList =  utilMap.get(item.getId());
                if(ObjectUtils.isNull(innerList)){
                    innerList = new PCMerchShopNavigationVO.InnerListVO();
                    innerList.setChildList(new ArrayList<>());
                    utilMap.put(item.getId(),innerList);
                }
                innerList.setId(item.getId());
                innerList.setIdx(item.getIdx());
                innerList.setNavName(item.getNavName());
                innerList.setCdate(item.getCdate());
            }
        }

        for(ShopNavigation item:navigationList){
            if(MerchShopNavigationEnum.二级分类.getCode().equals(item.getLeve())){
                PCMerchShopNavigationVO.InnerListVO parentNavigationVo =  utilMap.get(item.getParentId());
                if(null == parentNavigationVo){
                    throw new BusinessException("二级分类的父ID无效");
                }
                PCMerchShopNavigationVO.InnerChildVO childItem = new PCMerchShopNavigationVO.InnerChildVO();
                childItem.setId(item.getId());
                childItem.setNavName(item.getNavName());
                childItem.setIdx(item.getIdx());
                childItem.setCdate(item.getCdate());
                childItem.setParentId(item.getParentId());
                parentNavigationVo.getChildList().add(childItem);
            }
        }
        List<PCMerchShopNavigationVO.InnerListVO> voList = new ArrayList<>(utilMap.values());
        voList.sort((a, b) -> a.getIdx() - b.getIdx());
        for(PCMerchShopNavigationVO.InnerListVO listItem:voList){
            listItem.getChildList().sort((a,b)-> a.getIdx() - b.getIdx());
        }
        return voList;
    }

    @Override
    public List<PCMerchShopNavigationVO.NavigationVO> innerNavigationListWith001(String navigation001Id) {
        if(StringUtils.isBlank(navigation001Id)){
            return new ArrayList<>();
        }
        QueryWrapper<ShopNavigation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",navigation001Id);
        List<ShopNavigation> dataList =  repository.list(queryWrapper);
        if(ObjectUtils.isEmpty(dataList)){
            return new ArrayList<>();
        }
        return ListUtil.listCover(PCMerchShopNavigationVO.NavigationVO.class,dataList);
    }

}
