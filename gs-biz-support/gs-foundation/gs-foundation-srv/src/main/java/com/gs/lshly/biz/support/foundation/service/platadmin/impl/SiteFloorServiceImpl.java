package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import cn.hutool.core.lang.Console;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.foundation.complex.PcSiteFloorComlexEditor;
import com.gs.lshly.biz.support.foundation.complex.SiteFloorComlexEditor;
import com.gs.lshly.biz.support.foundation.complex.SiteFloorComplexQuery;
import com.gs.lshly.biz.support.foundation.entity.SiteFloor;
import com.gs.lshly.biz.support.foundation.entity.SiteFloorGoods;
import com.gs.lshly.biz.support.foundation.entity.SiteFloorMenu;
import com.gs.lshly.biz.support.foundation.entity.SiteFloorMenuGoods;
import com.gs.lshly.biz.support.foundation.enums.SiteFloorMenuTypeEnum;
import com.gs.lshly.biz.support.foundation.mapper.SiteFloorGoodsMapper;
import com.gs.lshly.biz.support.foundation.repository.ISiteFloorGoodsRepository;
import com.gs.lshly.biz.support.foundation.repository.ISiteFloorMenuGoodsRepository;
import com.gs.lshly.biz.support.foundation.repository.ISiteFloorMenuRepository;
import com.gs.lshly.biz.support.foundation.repository.ISiteFloorRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteFloorService;
import com.gs.lshly.common.enums.GoodsQueryTypeEnum;
import com.gs.lshly.common.enums.PcH5Enum;
import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteFloorDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteFloorQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteFloorVO;
import com.gs.lshly.common.utils.EnumUtil;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsInfoRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 陈奇
 * @since 2020-09-30
 */
@Component
public class SiteFloorServiceImpl implements ISiteFloorService {

    @Autowired
    private ISiteFloorRepository repository;

    @Autowired
    private ISiteFloorGoodsRepository siteFloorGoodsRepository;

    @Autowired
    private SiteFloorGoodsMapper siteFloorGoodsMapper;

    @Autowired
    private ISiteFloorMenuGoodsRepository siteFloorMenuGoodsRepository;


    @Autowired
    private ISiteFloorMenuRepository siteFloorMenuRepository;

    @DubboReference
    private IGoodsInfoRpc goodsInfoRpc;


    @Override
    public  List<SiteFloorVO.H5ListVO> h5List(SiteFloorQTO.H5QTO qto) {
        QueryWrapper<SiteFloor> wrapper =  MybatisPlusUtil.query();
        wrapper.eq("terminal",qto.getTerminal());
        wrapper.eq("subject",qto.getSubject());
        wrapper.eq("pc_show", PcH5Enum.NO.getCode());
        wrapper.orderByAsc("idx");
        List<SiteFloor> floorList = repository.list(wrapper);
        List<String> floorIdList = new ArrayList<>();
        Map<String, SiteFloorComplexQuery.ComplexPlatformH5> voMap = new HashMap<>();
        for(SiteFloor floorItem:floorList){
            floorIdList.add(floorItem.getId());
            SiteFloorComplexQuery.ComplexPlatformH5 complex = new SiteFloorComplexQuery.ComplexPlatformH5();
            complex.setFloorGoodsList(new ArrayList<>());
            SiteFloorVO.H5ListVO listVO = new  SiteFloorVO.H5ListVO();
            listVO.setGoodsItemList(new ArrayList<>());
            BeanUtils.copyProperties(floorItem,listVO);
            complex.setFloorVO(listVO);
            voMap.put(floorItem.getId(),complex);
        }
        if(ObjectUtils.isNotEmpty(floorIdList)){
            QueryWrapper<SiteFloorGoods> shopFloorGoodsQueryWrapper = MybatisPlusUtil.query();
            shopFloorGoodsQueryWrapper.in("floor_id",floorIdList);
            List<SiteFloorGoods> floorGoodsList = siteFloorGoodsRepository.list(shopFloorGoodsQueryWrapper);
            List<String> goodsIdList = new ArrayList<>();
            for(SiteFloorGoods siteFloorGoodsItem:floorGoodsList){
                //一个商品在多个楼层,商品服务只查一个就可以,先去重
                if(!goodsIdList.contains(siteFloorGoodsItem.getGoodsId())){
                    goodsIdList.add(siteFloorGoodsItem.getGoodsId());
                }
                SiteFloorComplexQuery.ComplexPlatformH5 complex = voMap.get(siteFloorGoodsItem.getFloorId());
                if(null != complex){
                    complex.getFloorGoodsList().add(siteFloorGoodsItem);
                }
            }

            if(ObjectUtils.isNotEmpty(goodsIdList)){
                GoodsInfoDTO.IdsInnerServiceDTO  innerDTO = new GoodsInfoDTO.IdsInnerServiceDTO();
                innerDTO.setGoodsIdList(goodsIdList);
                innerDTO.setQueryType(GoodsQueryTypeEnum.商品id.getCode());
                List<GoodsInfoVO.InnerServiceGoodsVO> innerGoodsList = goodsInfoRpc.innerServiceGoodsVO(innerDTO);
                for(SiteFloorComplexQuery.ComplexPlatformH5 complex:voMap.values()){
                    for(SiteFloorGoods floorGoodsItem:complex.getFloorGoodsList()){
                        //商品信息
                        for(GoodsInfoVO.InnerServiceGoodsVO innerGoodsItem:innerGoodsList){
                            if(floorGoodsItem.getGoodsId().equals(innerGoodsItem.getId())){
                                SiteFloorVO.H5GoodsItem goodsItemVO = new SiteFloorVO.H5GoodsItem();
                                goodsItemVO.setId(innerGoodsItem.getId());
                                goodsItemVO.setGoodsName(innerGoodsItem.getGoodsName());
                                complex.getFloorVO().getGoodsItemList().add(goodsItemVO);
                            }
                        }
                    }
                }
            }
            List<SiteFloorVO.H5ListVO> voList = new ArrayList<>();
            for(SiteFloorComplexQuery.ComplexPlatformH5 complex:voMap.values()){
                voList.add(complex.getFloorVO());
            }
            return voList;
        }
        return new ArrayList<>();
    }


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void h5Editor(SiteFloorDTO.H5ETO h5ETO) {
        if(ObjectUtils.isNotEmpty(h5ETO.getFloorList())){
            List<SiteFloor> siteFloorListList =new ArrayList<>();
            List<SiteFloorComlexEditor> editorSiteFloorComlexes = new ArrayList<>();
            h5ETO.getFloorList().forEach(item ->{
                if(null == item.getIdx()){
                    item.setIdx(0);
                }
                SiteFloor siteFloor = new SiteFloor();
                BeanUtils.copyProperties(item, siteFloor);
                siteFloor.setPcShow(PcH5Enum.NO.getCode());
                if (TrueFalseEnum.是.getCode().equals(item.getIsNew())){
                    siteFloor.setId(null);
                }else{
                    if(ObjectUtils.isNotEmpty(h5ETO.getRemoveIdList())){
                        if(h5ETO.getRemoveIdList().contains(siteFloor.getId())){
                            throw new BusinessException("保存的数据和删除的数据冲突");
                        }
                    }
                }
                siteFloorListList.add(siteFloor);
                SiteFloorComlexEditor comlex = new SiteFloorComlexEditor();
                comlex.setFloorH5Item(item);
                comlex.setSiteFloor(siteFloor);
                editorSiteFloorComlexes.add(comlex);
            });
            repository.saveOrUpdateBatch(siteFloorListList);

            List<SiteFloorGoods> siteFloorGoodsList = new ArrayList<>();
            for(SiteFloorComlexEditor comlexItem:editorSiteFloorComlexes){
                int idx = 0;
                for(String goodsId:comlexItem.getFloorH5Item().getGoodsIdList()){
                    SiteFloorGoods floorGoodsItem = new SiteFloorGoods();
                    floorGoodsItem.setFloorId(comlexItem.getSiteFloor().getId());
                    floorGoodsItem.setGoodsId(goodsId);
                    floorGoodsItem.setIdx(idx);
                    siteFloorGoodsList.add(floorGoodsItem);
                    idx ++;
                }
            }
            List<String> clearFloorIdList = ListUtil.getIdList(SiteFloor.class,siteFloorListList);
            if(ObjectUtils.isNotEmpty(clearFloorIdList)){
                QueryWrapper<SiteFloorGoods> clearWrapper = MybatisPlusUtil.query();
                clearWrapper.in("floor_id",clearFloorIdList);
                siteFloorGoodsMapper.delete(clearWrapper);
                siteFloorGoodsRepository.saveBatch(siteFloorGoodsList);
            }
        }

        //删除提交删除的数据
        if(ObjectUtils.isNotEmpty(h5ETO.getRemoveIdList())){
            repository.removeByIds(h5ETO.getRemoveIdList());
            QueryWrapper<SiteFloorGoods> deleteWrapper = MybatisPlusUtil.query();
            deleteWrapper.in("floor_id",h5ETO.getRemoveIdList());
            siteFloorGoodsMapper.delete(deleteWrapper);
        }
    }

    @Override
    public List<SiteFloorVO.PCListVO> pcList(SiteFloorQTO.PCQTO qto) {
        if(!EnumUtil.checkEnumCode(qto.getSubject(),SubjectEnum.class)){
            throw new BusinessException("专栏类型不存在");
        }
        QueryWrapper<SiteFloor> floorQueryWrapper =  MybatisPlusUtil.query();
        floorQueryWrapper.eq("terminal",qto.getTerminal());
        floorQueryWrapper.eq("subject",qto.getSubject());
        floorQueryWrapper.eq("pc_show",PcH5Enum.YES.getCode());
        floorQueryWrapper.orderByAsc("idx");
        List<SiteFloor> floorList = repository.list(floorQueryWrapper);
        List<String> floorIdList = new ArrayList<>();
        //楼层
        Map<String,SiteFloorVO.PCListVO> voMap = new HashMap<>();
        for(SiteFloor siteFloor:floorList){
            floorIdList.add(siteFloor.getId());
            SiteFloorVO.PCListVO listVO = new SiteFloorVO.PCListVO();
            BeanUtils.copyProperties(siteFloor,listVO);
            voMap.put(siteFloor.getId(),listVO);
        }
        if(ObjectUtils.isNotEmpty(floorIdList)){
            List<SiteFloorVO.PCListVO> voList = new ArrayList<>(voMap.values());
            QueryWrapper<SiteFloorMenu> floorMenuQueryWrapper =  MybatisPlusUtil.query();
            floorMenuQueryWrapper.in("floor_id",floorIdList);
            List<SiteFloorMenu> floorMenuList = siteFloorMenuRepository.list(floorMenuQueryWrapper);
            if(ObjectUtils.isNotEmpty(floorMenuList)){
                List<String> menuIdList = new ArrayList<>();
                //楼层菜单
                Map<String,SiteFloorVO.PCFloorMenu> menuVoMap = new HashMap<>();
                for(SiteFloorMenu floorMenu :floorMenuList){
                    if(SiteFloorMenuTypeEnum.楼层顶部.getCode().equals(floorMenu.getMenuType())){
                        SiteFloorVO.PCFloorMenu floorMenuVo = new SiteFloorVO.PCFloorMenu();
                        BeanUtils.copyProperties(floorMenu,floorMenuVo);
                        SiteFloorVO.PCListVO listVO = voMap.get(floorMenu.getFloorId());
                        if(null != listVO){
                            listVO.getFloorMenuList().add(floorMenuVo);
                        }
                        menuIdList.add(floorMenu.getId());
                        menuVoMap.put(floorMenu.getId(),floorMenuVo);
                    }else if(SiteFloorMenuTypeEnum.左侧链接.getCode().equals(floorMenu.getMenuType())){
                        SiteFloorVO.PCFloorLink floorLinkVo = new SiteFloorVO.PCFloorLink();
                        BeanUtils.copyProperties(floorMenu,floorLinkVo);
                        SiteFloorVO.PCListVO listVO = voMap.get(floorMenu.getFloorId());
                        listVO.getFloorLinkList().add(floorLinkVo);
                    }
                }
                if(ObjectUtils.isNotEmpty(menuIdList)){
                    QueryWrapper<SiteFloorMenuGoods> menuGoodsQueryWrapper =  MybatisPlusUtil.query();
                    menuGoodsQueryWrapper.in("floor_menu_id",menuIdList);
                    List<SiteFloorMenuGoods> menuGoodsList = siteFloorMenuGoodsRepository.list(menuGoodsQueryWrapper);
                    if(ObjectUtils.isNotEmpty(menuGoodsList)){
                        List<String> goodsIdList = new ArrayList<>();
                        for(SiteFloorMenuGoods menuGoods:menuGoodsList){
                            if(!goodsIdList.contains(menuGoods.getGoodsId())){
                                goodsIdList.add(menuGoods.getGoodsId());
                            }
                            SiteFloorVO.PCFloorMenuGoods menuGoodsVo = new SiteFloorVO.PCFloorMenuGoods();
                            BeanUtils.copyProperties(menuGoods,menuGoodsVo);
                            SiteFloorVO.PCFloorMenu floorMenuVo =   menuVoMap.get(menuGoods.getFloorMenuId());
                            if(null != floorMenuVo){
                                floorMenuVo.getMenuGoodsList().add(menuGoodsVo);
                            }
                        }
                        //商品信息
                        if(ObjectUtils.isNotEmpty(goodsIdList)){
                            GoodsInfoDTO.IdsInnerServiceDTO  innerDTO = new GoodsInfoDTO.IdsInnerServiceDTO();
                            innerDTO.setGoodsIdList(goodsIdList);
                            innerDTO.setQueryType(GoodsQueryTypeEnum.商品id.getCode());
                            List<GoodsInfoVO.InnerServiceGoodsVO> innerGoodsList = goodsInfoRpc.innerServiceGoodsVO(innerDTO);
                            if(ObjectUtils.isNotEmpty(innerGoodsList)){
                                for(SiteFloorVO.PCFloorMenu floorMenu:menuVoMap.values()){
                                    for(SiteFloorVO.PCFloorMenuGoods menuGoodsVO:floorMenu.getMenuGoodsList()){
                                        for(GoodsInfoVO.InnerServiceGoodsVO innerGoodsItem:innerGoodsList){
                                            if(innerGoodsItem.getId().equals(menuGoodsVO.getGoodsId())){
                                                menuGoodsVO.setGoodsName(innerGoodsItem.getGoodsName());
                                            }
                                        }
                                    }
                                    //去掉菜单上有设置,但是商品服务并没有返回商品信息的商品(比如下架)
                                    for(int i=0;i<floorMenu.getMenuGoodsList().size();i++){
                                        SiteFloorVO.PCFloorMenuGoods menuGoodsVO = floorMenu.getMenuGoodsList().get(i);
                                        if(null != menuGoodsVO){
                                            if(StringUtils.isBlank(menuGoodsVO.getGoodsName())){
                                                floorMenu.getMenuGoodsList().remove(i);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                //排序数据
                voList.sort(Comparator.comparing(SiteFloorVO.PCListVO::getIdx));
                for(SiteFloorVO.PCListVO floorVo:voList){
                    floorVo.getFloorLinkList().sort(Comparator.comparing(SiteFloorVO.PCFloorLink::getIdx));
                    floorVo.getFloorMenuList().sort(Comparator.comparing(SiteFloorVO.PCFloorMenu::getIdx));
                    for(SiteFloorVO.PCFloorMenu floorMenu:floorVo.getFloorMenuList()){
                        floorMenu.getMenuGoodsList().sort(Comparator.comparing(SiteFloorVO.PCFloorMenuGoods::getIdx));
                    }
                }
            }
            return new ArrayList<>(voList);
        }
        return new ArrayList<>();
    }

    @Override
    public void pcEditor(SiteFloorDTO.PCETO pcETO) {
        if(!EnumUtil.checkEnumCode(pcETO.getSubject(),SubjectEnum.class)){
            throw new BusinessException("专栏类型不存在");
        }
        //清理旧数据
        this.removeFloorSettings(pcETO);

        if(ObjectUtils.isNotEmpty(pcETO.getFloorList())){
            List<PcSiteFloorComlexEditor.FloorComplex> floorComplexList = new ArrayList<>();
            List<SiteFloor> siteFloorList = new ArrayList<>();
            for(SiteFloorDTO.PCSiteFloor floorDto:pcETO.getFloorList()){
                SiteFloor siteFloor  = new SiteFloor();
                BeanUtils.copyProperties(floorDto,siteFloor);
                siteFloor.setId(null);
                siteFloor.setPcShow(PcH5Enum.YES.getCode());
                siteFloor.setTerminal(pcETO.getTerminal());
                siteFloor.setSubject(pcETO.getSubject());
                siteFloorList.add(siteFloor);
                //楼层临时数据关系建立;
                PcSiteFloorComlexEditor.FloorComplex floorComplex = new PcSiteFloorComlexEditor.FloorComplex();
                floorComplex.setFloorDto(floorDto);
                floorComplex.setFloor(siteFloor);
                floorComplexList.add(floorComplex);

                if(ObjectUtils.isNotEmpty(floorDto.getFloorMenuList())){
                    for(SiteFloorDTO.PCFloorMenu menuDto:floorDto.getFloorMenuList()){
                        //楼层菜单
                        SiteFloorMenu floorMenu = new SiteFloorMenu();
                        BeanUtils.copyProperties(menuDto,floorMenu);
                        floorMenu.setMenuType(SiteFloorMenuTypeEnum.楼层顶部.getCode());
                        floorMenu.setId(null);
                        //楼层菜单临时数据关系建立;
                        PcSiteFloorComlexEditor.FloorMenuComplex floorMenuComplex = new PcSiteFloorComlexEditor.FloorMenuComplex();
                        floorMenuComplex.setFloorMenuDto(menuDto);
                        floorMenuComplex.setFloorMenu(floorMenu);
                        floorMenuComplex.setFloor(siteFloor);
                        floorComplex.getMenuComplexList().add(floorMenuComplex);
                        //菜单下的商品
                        for(String goodsId:menuDto.getMenuGoodsList()){
                            SiteFloorMenuGoods menuGoods = new SiteFloorMenuGoods();
                            menuGoods.setGoodsId(goodsId);
                            floorMenuComplex.getMenuGoodsList().add(menuGoods);
                        }
                    }
                }
                if(ObjectUtils.isNotEmpty(floorDto.getFloorLinkList())){
                    for(SiteFloorDTO.PCFloorLink linkDto:floorDto.getFloorLinkList()){
                        SiteFloorMenu floorLink = new SiteFloorMenu();
                        BeanUtils.copyProperties(linkDto,floorLink);
                        floorLink.setId(null);
                        floorLink.setMenuType(SiteFloorMenuTypeEnum.左侧链接.getCode());
                        floorComplex.getLinkList().add(floorLink);
                    }
                }
            }
            //保存楼层
            repository.saveBatch(siteFloorList);
            List<SiteFloorMenu> menuBatchList = new ArrayList<>();
            List<SiteFloorMenu> linkBatchList = new ArrayList<>();
            for(PcSiteFloorComlexEditor.FloorComplex floorComplex:floorComplexList){
                for(PcSiteFloorComlexEditor.FloorMenuComplex floorMenuComplex : floorComplex.getMenuComplexList()){
                    floorMenuComplex.getFloorMenu().setFloorId(floorComplex.getFloor().getId());
                    menuBatchList.add(floorMenuComplex.getFloorMenu());
                }
                for(SiteFloorMenu  floorLink : floorComplex.getLinkList()){
                    floorLink.setFloorId(floorComplex.getFloor().getId());
                    linkBatchList.add(floorLink);
                }
            }
            //保存楼层链接
            if(ObjectUtils.isNotEmpty(linkBatchList)){
                siteFloorMenuRepository.saveBatch(linkBatchList);
            }
            //保存楼层菜单
            if(ObjectUtils.isNotEmpty(menuBatchList)){
                siteFloorMenuRepository.saveBatch(menuBatchList);
                //保存菜单商品
                List<SiteFloorMenuGoods> menuGoodsBatchList = new ArrayList<>();
                for(PcSiteFloorComlexEditor.FloorComplex floorComplex:floorComplexList){
                    for(PcSiteFloorComlexEditor.FloorMenuComplex floorMenuComplex : floorComplex.getMenuComplexList()){
                        for(SiteFloorMenuGoods menuGoods:floorMenuComplex.getMenuGoodsList()){
                            if(StringUtils.isNotBlank(menuGoods.getGoodsId())){
                                menuGoods.setFloorMenuId(floorMenuComplex.getFloorMenu().getId());
                                menuGoodsBatchList.add(menuGoods);
                            }
                        }
                    }
                }
                if(ObjectUtils.isNotEmpty(menuGoodsBatchList)){
                    siteFloorMenuGoodsRepository.saveBatch(menuGoodsBatchList);
                }
            }
        }
    }

    private void removeFloorSettings(SiteFloorDTO.PCETO pcETO){

        QueryWrapper<SiteFloor> queryWrapper =  MybatisPlusUtil.query();
        queryWrapper.eq("terminal",pcETO.getTerminal());
        queryWrapper.eq("subject",pcETO.getSubject());
        queryWrapper.eq("pc_show",PcH5Enum.YES.getCode());
        List<SiteFloor> floorList = repository.list(queryWrapper);
        List<String> floorIdList = ListUtil.getIdList(SiteFloor.class,floorList);
        if(ObjectUtils.isNotEmpty(floorIdList)){
            QueryWrapper<SiteFloorMenu> menuQueryWrapper =  MybatisPlusUtil.query();
            menuQueryWrapper.in("floor_id",floorIdList);
            List<SiteFloorMenu> menuList = siteFloorMenuRepository.list(menuQueryWrapper);
            List<String> menuIdList = ListUtil.getIdList(SiteFloorMenu.class,menuList);

            //清理楼层
            repository.removeByIds(floorIdList);
            //清理楼层菜单（链接和菜单）
            siteFloorMenuRepository.removeByIds(menuIdList);
            //清理楼层菜单下的商品
            QueryWrapper<SiteFloorMenuGoods> removeMenuGoodsWrapper =  MybatisPlusUtil.query();
            removeMenuGoodsWrapper.in("floor_menu_id",menuIdList);
            siteFloorMenuGoodsRepository.remove(removeMenuGoodsWrapper);
        }
    }

}
