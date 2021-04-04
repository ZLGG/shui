package com.gs.lshly.biz.support.commodity.service.bbb.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.CategoryBrand;
import com.gs.lshly.biz.support.commodity.entity.GoodsBrand;
import com.gs.lshly.biz.support.commodity.entity.GoodsCategory;
import com.gs.lshly.biz.support.commodity.repository.ICategoryBrandRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsBrandRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsCategoryRepository;
import com.gs.lshly.biz.support.commodity.service.bbb.pc.IPCBbbGoodsCategoryService;
import com.gs.lshly.common.enums.GoodsCategoryLevelEnum;
import com.gs.lshly.common.enums.GoodsUsePlatformEnums;
import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.bbb.pc.commodity.qto.PCBbbGoodsCategoryQTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsBrandVO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsCategoryVO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteNavigationVO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteNavigationVO.TopLinkVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbb.pc.foundation.IBbbSiteNavigationRpc;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-10-23
*/
@Component
@Slf4j
public class PCBbbGoodsCategoryServiceImpl implements IPCBbbGoodsCategoryService {

    @Autowired
    private IGoodsCategoryRepository repository;
    @Autowired
    private IGoodsBrandRepository brandRepository;

    @Autowired
    private ICategoryBrandRepository categoryBrandRepository;

    @DubboReference
    private IBbbSiteNavigationRpc bbbSiteNavigationRpc;

    @Override
    public PCBbbGoodsCategoryVO.CategoryMenuVO getCategoryMenuVO(PCBbbGoodsCategoryQTO.QTO qto) {
        //查询所有的三级分类
        QueryWrapper<GoodsCategory> boost = new QueryWrapper<>();
        List<GoodsCategory> listLevel3 = repository.list(boost);
        //声明一个存储容器
        if(ObjectUtils.isEmpty(listLevel3)) {
            return null;
        }
        List<PCBbbGoodsCategoryVO.CategoryTreeVO> categoryTreeVOS = new ArrayList<>();
        Map<String, PCBbbGoodsCategoryVO.CategoryTreeVO> mapLevel2 = new HashMap<>();
        Map<String, PCBbbGoodsCategoryVO.CategoryTreeVO> mapLevel1 = new HashMap<>();
        for (GoodsCategory level3 : listLevel3) {
            if (level3.getUseFiled() == null || !level3.getGsCategoryLevel().equals(GoodsCategoryLevelEnum.THREE.getCode())) {
                continue;
            }
            if (!level3.getUseFiled().equals(GoodsUsePlatformEnums.B商城和C商城.getCode()) && !level3.getUseFiled().equals(GoodsUsePlatformEnums.B商城.getCode())) {
                continue;
            }
            PCBbbGoodsCategoryVO.CategoryTreeVO levelVO3 = new PCBbbGoodsCategoryVO.CategoryTreeVO();
            BeanUtils.copyProperties(level3, levelVO3);
            //根据获取的二级类目查询商品的二级类目
            PCBbbGoodsCategoryVO.CategoryTreeVO level2 = mapLevel2.get(level3.getParentId());
            if (level2 == null) {
                level2 = getParentCategory(levelVO3, listLevel3);
                if (level2 == null) {
                    log.error("三级类目id：{}；name：{}无上级类目{}", level3.getId(), level3.getGsCategoryName(), level3.getParentId());
                }
                mapLevel2.put(level3.getParentId(), level2);
                //根据获取的二级类目查询商品的一级类目
                PCBbbGoodsCategoryVO.CategoryTreeVO level1 = mapLevel1.get(level2.getParentId());
                if (level1 == null) {
                    level1 = getParentCategory(level2, listLevel3);
                    if (level1 == null) {
                        log.error("二级类目id：{}；name：{}无上级类目{}", level2.getId(), level2.getGsCategoryName(), level3.getParentId());
                    }
                    categoryTreeVOS.add(level1);
                    mapLevel1.put(level2.getParentId(), level1);
                    level1.getList().add(level2);
                } else {
                    level1.getList().add(level2);
                }
                level2.getList().add(levelVO3);
            } else {
                level2.getList().add(levelVO3);
            }
        }
        for(PCBbbGoodsCategoryVO.CategoryTreeVO levelVO1 : mapLevel1.values()){
            //二级排序(包装类型去掉，可能会报nep)
            levelVO1.getList().sort((o1, o2) -> ((Integer)o1.getIdx()) != null && ((Integer)o2.getIdx()) != null ? (o1.getIdx() > o2.getIdx() ? 1 : -1) : -1);
            //三级排序
            for (PCBbbGoodsCategoryVO.CategoryTreeVO levelVO2 : levelVO1.getList()) {
                levelVO2.getList().sort((o1, o2) -> ((Integer)o1.getIdx()) != null && ((Integer)o2.getIdx()) != null ? (o1.getIdx() > o2.getIdx() ? 1 : -1) : -1);
            }
        }
        //一级排序
        categoryTreeVOS.sort((o1, o2) -> ((Integer)o1.getIdx()) != null && ((Integer)o2.getIdx()) != null ? (o1.getIdx() > o2.getIdx() ? 1 : -1) : -1);

        //填充商品分类数据
        PCBbbGoodsCategoryVO.CategoryMenuVO categoryMenuVO = new PCBbbGoodsCategoryVO.CategoryMenuVO();
        categoryMenuVO.setCategoryTreeVOS(categoryTreeVOS);
        BbbSiteNavigationVO.HomeVO homeVO = bbbSiteNavigationRpc.homeDetail(qto);
        
        if(qto.getTerminal().equals(TerminalEnum.BBC.getCode())&&qto.getSubject().equals(SubjectEnum.默认.getCode())){
        	//获取父分类
        	QueryWrapper<GoodsCategory> parentQueryWrapper = new QueryWrapper<>();
        	parentQueryWrapper.eq("gs_category_level", 1);
        	parentQueryWrapper.orderByAsc("idx");
        	List<GoodsCategory> parents = repository.list(parentQueryWrapper);
        	List<TopLinkVO> topLinkList = new ArrayList<>();
        	TopLinkVO topLinkVO = null;
        	if(CollectionUtils.isNotEmpty(parents)){
        		for(GoodsCategory goodsCategory:parents){
        			topLinkVO = new TopLinkVO();
        			topLinkVO.setId(goodsCategory.getId());
        			topLinkVO.setIdx(goodsCategory.getIdx());
        			topLinkVO.setName(goodsCategory.getGsCategoryName());
        			topLinkList.add(topLinkVO);
        		}
        		homeVO.setTopLinkList(topLinkList);
        	}
        }
        
        categoryMenuVO.setHomeSettins(homeVO);
        return categoryMenuVO;
    }


    @Override
    public PCBbbGoodsCategoryVO.CategoryNavigationVO getCategoryNavigationVO(PCBbbGoodsCategoryQTO.CategoryNavigationQTO qto) {
        QueryWrapper<GoodsCategory> wrapper = MybatisPlusUtil.query();
        if (ObjectUtils.isNotEmpty(qto.getCategoryLevel2Id())){
            wrapper.eq("parent_id",qto.getCategoryLevel2Id());
        }
        if (ObjectUtils.isNotEmpty(qto.getCategoryLevel3Id())
                && ObjectUtils.isEmpty(qto.getCategoryLevel2Id())
        && StringUtils.isBlank(qto.getFromAddress())){
            wrapper.in("id",qto.getCategoryLevel3Id());
        }
        if (ObjectUtils.isNotEmpty(qto.getCategoryLevel2Id()) && ObjectUtils.isNotEmpty(qto.getCategoryLevel3Id())){
            wrapper.eq("parent_id",qto.getCategoryLevel2Id());
        }
        wrapper.ne("use_filed",GoodsUsePlatformEnums.C商城.getCode());
        wrapper.eq("gs_category_level",GoodsCategoryLevelEnum.THREE.getCode());
        List<GoodsCategory> categories = repository.list(wrapper);
        if (ObjectUtils.isEmpty(categories)){
            return null;
        }
        PCBbbGoodsCategoryVO.CategoryNavigationVO categoryNavigationVO = new PCBbbGoodsCategoryVO.CategoryNavigationVO();
        List<PCBbbGoodsCategoryVO.ListVO> voList = ListUtil.listCover(PCBbbGoodsCategoryVO.ListVO.class,categories);
        categoryNavigationVO.setCategoryLevel3List(voList);
        categoryNavigationVO.setAllBrandVOs(getBrandVOs(qto.getCategoryLevel2Id(),qto.getCategoryLevel3Id()));
        return categoryNavigationVO;
    }

    @Override
    public  List<PCBbbGoodsCategoryVO.ListVO> getCategoryForBrandId(PCBbbGoodsCategoryQTO.CategoryForBrandQTO qto) {
        if(ObjectUtils.isEmpty(qto.getBrandIdList())){
            return new ArrayList<>();
        }
        QueryWrapper<GoodsCategory> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.in("b.brand_id",qto.getBrandIdList());
        List<GoodsCategory> categoryList =  repository.getMapper().listCategoryForBrandId(queryWrapper);
        List<PCBbbGoodsCategoryVO.ListVO> categoryLevel3List = ListUtil.listCover(PCBbbGoodsCategoryVO.ListVO.class,categoryList);
        return categoryLevel3List;
    }

    @Override
    public List<String> innerServiceLevel1IdList(List<String> level3List) {
        if (ObjectUtils.isEmpty(level3List)){
            throw new BusinessException("参数不能为空！");
        }
        QueryWrapper<GoodsCategory> wrapper = MybatisPlusUtil.query();
        wrapper.in("id",level3List);
        List<GoodsCategory> categories = repository.list(wrapper);
        if (ObjectUtils.isEmpty(categories)){
            throw new BusinessException("查询异常！");
        }
        List<String> level2IdList = categories.stream().map(GoodsCategory::getParentId).distinct().collect(Collectors.toList());

        QueryWrapper<GoodsCategory> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.in("id",level2IdList);
        List<GoodsCategory> categoryList = repository.list(queryWrapper);
        if (ObjectUtils.isEmpty(categoryList)){
            throw new BusinessException("查询异常！");
        }

        List<String> level1IdList = categoryList.stream().map(GoodsCategory::getParentId).distinct().collect(Collectors.toList());

        return level1IdList;
    }

    private List<PCBbbGoodsBrandVO.ListVO> getBrandVOs(String categoryLevel2Id,List<String> categoryLevel3Id){
        //查询这个类目关联的品牌id列表
        QueryWrapper<CategoryBrand> categoryBrandQueryWrapper = MybatisPlusUtil.query();
        if (ObjectUtils.isNotEmpty(categoryLevel2Id) && ObjectUtils.isEmpty(categoryLevel3Id)){
            categoryBrandQueryWrapper.eq("category_id",categoryLevel2Id);
        }
        if (ObjectUtils.isNotEmpty(categoryLevel2Id) && ObjectUtils.isNotEmpty(categoryLevel3Id)){
            categoryBrandQueryWrapper.in("category_id",categoryLevel3Id);
        }
        if (ObjectUtils.isEmpty(categoryLevel2Id) && ObjectUtils.isNotEmpty(categoryLevel3Id) ){
            categoryBrandQueryWrapper.in("category_id",categoryLevel3Id);
        }
        List<CategoryBrand> brandList = categoryBrandRepository.list(categoryBrandQueryWrapper);
        if (ObjectUtils.isEmpty(brandList)){
            return new ArrayList<>();
        }
        List<String> brandIdList = ListUtil.getIdList(CategoryBrand.class,brandList,"brandId");

        //根据品牌id查询品牌数据
        QueryWrapper<GoodsBrand> brandQueryWrapper = MybatisPlusUtil.query();
        brandQueryWrapper.in("id",brandIdList);
        List<GoodsBrand> goodsBrands = brandRepository.list(brandQueryWrapper);
        if (ObjectUtils.isEmpty(goodsBrands)){
            throw new BusinessException("品牌数据异常");
        }
        List<PCBbbGoodsBrandVO.ListVO> listVOS = ListUtil.listCover(PCBbbGoodsBrandVO.ListVO.class,goodsBrands);
        return listVOS;
    }

    private PCBbbGoodsCategoryVO.CategoryTreeVO getParentCategory(PCBbbGoodsCategoryVO.CategoryTreeVO subVO, List<GoodsCategory> categories){
        if (subVO==null || StringUtils.isEmpty(subVO.getParentId())){
            throw new BusinessException("数据异常");
        }
        for(GoodsCategory category : categories ) {
            if (subVO.getParentId().equals(category.getId())) {
                PCBbbGoodsCategoryVO.CategoryTreeVO levelVO1 = new PCBbbGoodsCategoryVO.CategoryTreeVO();
                levelVO1.setList(new ArrayList<>());
                BeanUtils.copyProperties(category, levelVO1);
                return levelVO1;
            }
        }
        throw new BusinessException(String.format("无法为Id：%s；name：%s的类目找到上级类目", subVO.getId(), subVO.getGsCategoryName()));
    }

}
