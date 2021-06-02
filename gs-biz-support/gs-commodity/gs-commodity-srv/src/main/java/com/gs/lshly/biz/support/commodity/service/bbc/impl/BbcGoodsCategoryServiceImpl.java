package com.gs.lshly.biz.support.commodity.service.bbc.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsBrand;
import com.gs.lshly.biz.support.commodity.entity.GoodsCategory;
import com.gs.lshly.biz.support.commodity.entity.GoodsInfo;
import com.gs.lshly.biz.support.commodity.repository.IGoodsBrandRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsCategoryRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsInfoRepository;
import com.gs.lshly.biz.support.commodity.service.bbc.IBbcGoodsCategoryService;
import com.gs.lshly.common.enums.GoodsCategoryLevelEnum;
import com.gs.lshly.common.enums.GoodsUsePlatformEnums;
import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsCategoryDTO.CtccDTO;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsCategoryDTO.ParentIdDTO;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsCategoryDTO.ThirdListDTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsCategoryQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsCategoryVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsCategoryVO.CtccCategoryVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsCategoryVO.CtccGoodsInfoAdvertVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsCategoryVO.CtccHomeVO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteAdvertQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteAdvertVO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsBrandQTO.IdQTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsInfoQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsBrandVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO.ListVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSiteAdvertRpc;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Starry
 * @since 2020-10-23
 */
@Component
@Slf4j
@SuppressWarnings({"unchecked","rawtypes"})
public class BbcGoodsCategoryServiceImpl implements IBbcGoodsCategoryService {

    @Autowired
    private IGoodsCategoryRepository repository;

    @Autowired
    private IGoodsInfoRepository iGoodsInfoRepository;

    @Autowired
    private IGoodsBrandRepository iGoodsBrandRepository;

    @DubboReference
    private IBbcSiteAdvertRpc siteAdvertRpc;

    @Override
    public List<BbcGoodsCategoryVO.CategoryTreeVO> listGoodsCategory() {
        //查询所有的三级分类
        QueryWrapper<GoodsCategory> boost = new QueryWrapper<>();
        List<GoodsCategory> listLevel3 = repository.list(boost);
        //声明一个存储容器
        if (ObjectUtils.isEmpty(listLevel3)) {
            return null;
        }
        List<BbcGoodsCategoryVO.CategoryTreeVO> categoryTreeVOS = new ArrayList<>();
        Map<String, BbcGoodsCategoryVO.CategoryTreeVO> mapLevel2 = new HashMap<>();
        Map<String, BbcGoodsCategoryVO.CategoryTreeVO> mapLevel1 = new HashMap<>();
        for (GoodsCategory level3 : listLevel3) {
            if (level3.getUseFiled() == null || !level3.getGsCategoryLevel().equals(GoodsCategoryLevelEnum.THREE.getCode())) {
                continue;
            }
            if (!level3.getUseFiled().equals(GoodsUsePlatformEnums.B商城和C商城.getCode()) && !level3.getUseFiled().equals(GoodsUsePlatformEnums.C商城.getCode())) {
                continue;
            }
            BbcGoodsCategoryVO.CategoryTreeVO levelVO3 = new BbcGoodsCategoryVO.CategoryTreeVO();
            BeanUtils.copyProperties(level3, levelVO3);
            //根据获取的二级类目查询商品的二级类目
            BbcGoodsCategoryVO.CategoryTreeVO level2 = mapLevel2.get(level3.getParentId());
            if (level2 == null) {
                level2 = getParentCategory(levelVO3, listLevel3);
                if (level2 == null) {
                    log.error("三级类目id：{}；name：{}无上级类目{}", level3.getId(), level3.getGsCategoryName(), level3.getParentId());
                }
                mapLevel2.put(level3.getParentId(), level2);
                //根据获取的二级类目查询商品的一级类目
                BbcGoodsCategoryVO.CategoryTreeVO level1 = mapLevel1.get(level2.getParentId());
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
        //组装每个一级分类下的分页广告图
        List<BbcSiteAdvertVO.InnerCategoryAdvertListVO> innerCategoryAdvertListVOS = siteAdvertRpc.innerCategoryAdvertList(new BaseDTO());
        for (BbcGoodsCategoryVO.CategoryTreeVO levelVO1 : mapLevel1.values()) {
            //一级类目才有 广告图
            for (BbcSiteAdvertVO.InnerCategoryAdvertListVO advertListVO : innerCategoryAdvertListVOS) {
                if (levelVO1.getId().equals(advertListVO.getCategoryId())) {
                    levelVO1.getCategoryAdvertListVO().add(advertListVO);
                }
            }
            //二级排序(包装类型去掉，可能会报nep)
            levelVO1.getList().sort((o1, o2) -> ((Integer) o1.getIdx()) != null && ((Integer) o2.getIdx()) != null ? (o1.getIdx() > o2.getIdx() ? 1 : -1) : -1);
            //三级排序
            for (BbcGoodsCategoryVO.CategoryTreeVO levelVO2 : levelVO1.getList()) {
                levelVO2.getList().sort((o1, o2) -> ((Integer) o1.getIdx()) != null && ((Integer) o2.getIdx()) != null ? (o1.getIdx() > o2.getIdx() ? 1 : -1) : -1);
            }
        }
        //一级排序
        categoryTreeVOS.sort((o1, o2) -> ((Integer) o1.getIdx()) != null && ((Integer) o2.getIdx()) != null ? (o1.getIdx() > o2.getIdx() ? 1 : -1) : -1);

        return categoryTreeVOS;
    }

    @Override
    public List<BbcGoodsCategoryVO.CategoryTreeVO> goodsCategoryTree(BbcGoodsCategoryQTO.ListQTO listQTO) {
        QueryWrapper<GoodsCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(q -> q.eq("use_filed", listQTO.getUseFiled())
                .or()
                .eq("use_filed", GoodsUsePlatformEnums.B商城和C商城.getCode())
                .or()
                .isNull("use_filed")
        );
        queryWrapper.orderByDesc("idx");
        List<GoodsCategory> list = repository.list(queryWrapper);
        Map<String, List<GoodsCategory>> map = allGoodsCategoryMap(list);
        List<BbcGoodsCategoryVO.CategoryTreeVO> categoryTreeVOS = getCategoryTree(listQTO, map);
        if (categoryTreeVOS == null) {
            return null;
        }
        return categoryTreeVOS;
    }

	@Override
    public PageData<GoodsInfoVO.ListVO> goodsList(GoodsInfoQTO.CategoryIdQTO categoryIdQTO) {

        List<String> cIds = getNewSubCategoryIds(categoryIdQTO);
        //通过列表对产品表进行查询 返回品牌信息

        QueryWrapper<GoodsInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("category_id", cIds);
        IPage<GoodsInfo> page = MybatisPlusUtil.pager(categoryIdQTO);
        IPage<GoodsInfo> pageData = iGoodsInfoRepository.page(page, queryWrapper);
        return new PageData(pageData.getRecords(), categoryIdQTO.getPageNum(), categoryIdQTO.getPageSize(), pageData.getTotal());
    }


    @Override
    public PageData<GoodsBrandVO.ListVO> brandList(GoodsInfoQTO.CategoryIdQTO categoryIdQTO) {
        List<String> cIds = getSubCategoryIds(categoryIdQTO);
        IPage<GoodsBrand> page = MybatisPlusUtil.pager(categoryIdQTO);
        QueryWrapper<GoodsBrand> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("category_id", cIds);
        IPage<GoodsBrand> goodsBrandIPage = iGoodsBrandRepository.listByCategory(page, queryWrapper);
        return new PageData(goodsBrandIPage.getRecords(), categoryIdQTO.getPageNum(), categoryIdQTO.getPageSize(), goodsBrandIPage.getTotal());
    }

    private List<String> getSubCategoryIds(GoodsInfoQTO.CategoryIdQTO categoryIdQTO) {
        BbcGoodsCategoryQTO.ListQTO listQTO = new BbcGoodsCategoryQTO.ListQTO();
        listQTO.setShowAll(true);
        listQTO.setParentId(categoryIdQTO.getCategoryId());
        listQTO.setUseFiled(categoryIdQTO.getUseFiled());

        //获取树结构
        List<BbcGoodsCategoryVO.CategoryTreeVO> categoryTreeVOS = goodsCategoryTree(listQTO);
        //将树转换成列表
        List<BbcGoodsCategoryVO.CategoryTreeVO> treeVOList = new ArrayList<>();
        goodsCategoryList(categoryTreeVOS, treeVOList);
        return treeVOList.stream().map(v -> v.getId()).collect(Collectors.toList());
    }

    /**
     * 跟据类目查询对应的分类
     */
    private List<String> getNewSubCategoryIds(GoodsInfoQTO.CategoryIdQTO categoryIdQTO) {
        List<String> ret = new ArrayList<String>();
        ret.add(categoryIdQTO.getCategoryId());
        GoodsCategory goodsCategory = repository.getById(categoryIdQTO.getCategoryId());
        if (StringUtils.isNotEmpty(goodsCategory.getParentId())) {
            goodsCategory = repository.getById(goodsCategory.getParentId());

            if (goodsCategory != null) {
                ret.add(goodsCategory.getId());
                if (StringUtils.isNotEmpty(goodsCategory.getParentId())) {
                    goodsCategory = repository.getById(goodsCategory.getParentId());

                    if (goodsCategory != null) {
                        ret.add(goodsCategory.getId());
                    }
                }
            }
        }
        return ret;
    }

    private List<BbcGoodsCategoryVO.CategoryTreeVO> goodsCategoryList(List<BbcGoodsCategoryVO.CategoryTreeVO> list,
                                                                      List<BbcGoodsCategoryVO.CategoryTreeVO> resultVO) {
        for (BbcGoodsCategoryVO.CategoryTreeVO treeVO : list) {
            resultVO.add(treeVO);
            if (treeVO.getList() != null) {
                goodsCategoryList(treeVO.getList(), resultVO);
            }
        }
        return resultVO;
    }

    /**
     * 将分类数据转换成map parentId为key
     *
     * @param list
     * @return
     */
    private Map<String, List<GoodsCategory>> allGoodsCategoryMap(List<GoodsCategory> list) {
        Map<String, List<GoodsCategory>> categoryMap = list.stream().collect(Collectors.toMap(v -> StringUtils.isBlank(v.getParentId()) ? "0" : v.getParentId(), v -> {
            ArrayList<GoodsCategory> categories = new ArrayList<>();
            categories.add(v);
            return categories;
        }, (v1, v2) -> {
            v1.addAll(v2);
            return v1;
        }));
        return categoryMap;
    }

    /**
     * 将分类数据转换成树
     *
     * @param listQTO 树条件
     * @param map     allGoodsCategoryMap() 方法获取的数据
     * @return
     */
    private List<BbcGoodsCategoryVO.CategoryTreeVO> getCategoryTree(BbcGoodsCategoryQTO.ListQTO listQTO, Map<String, List<GoodsCategory>> map) {
        List<BbcGoodsCategoryVO.CategoryTreeVO> categoryTreeVOS = new ArrayList<>();
        String parentId = listQTO.getParentId();
        List<GoodsCategory> list = map.get(parentId);
        if (list == null) {
            return null;
        }
        for (GoodsCategory goodsCategory : list) {
            BbcGoodsCategoryVO.CategoryTreeVO treeVO = convertToCategoryTreeVO(goodsCategory);
            if (listQTO.getShowAll()) {
                BbcGoodsCategoryQTO.ListQTO newQto = new BbcGoodsCategoryQTO.ListQTO();
                newQto.setParentId(goodsCategory.getId());
                newQto.setShowAll(listQTO.getShowAll());
                treeVO.setList(getCategoryTree(newQto, map));
            }

            categoryTreeVOS.add(treeVO);
        }
        return categoryTreeVOS;
    }

    private List<GoodsCategory> getGoodsCategories(BbcGoodsCategoryQTO.ListQTO listQTO) {
        QueryWrapper<GoodsCategory> boost = new QueryWrapper<>();
        //查询的是否是一级节点
        if (StringUtils.isNotBlank(listQTO.getParentId())) {
            boost.eq("parent_id", listQTO.getParentId());
        } else {
            boost.eq("gs_category_level", 1);
        }
        boost.orderByAsc("idx");
        List<GoodsCategory> list = repository.list(boost);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list;
    }

    private BbcGoodsCategoryVO.CategoryTreeVO convertToCategoryTreeVO(GoodsCategory goodsCategory) {
        BbcGoodsCategoryVO.CategoryTreeVO treeVO = new BbcGoodsCategoryVO.CategoryTreeVO();
        BeanUtils.copyProperties(goodsCategory, treeVO);
        return treeVO;
    }

    private BbcGoodsCategoryVO.CategoryTreeVO getParentCategory(BbcGoodsCategoryVO.CategoryTreeVO subVO, List<GoodsCategory> categories) {
        if (subVO == null || StringUtils.isEmpty(subVO.getParentId())) {
            throw new BusinessException("数据异常");
        }
        for (GoodsCategory category : categories) {
            if (subVO.getParentId().equals(category.getId())) {
                BbcGoodsCategoryVO.CategoryTreeVO levelVO1 = new BbcGoodsCategoryVO.CategoryTreeVO();
                levelVO1.setList(new ArrayList<>());
                BeanUtils.copyProperties(category, levelVO1);
                return levelVO1;
            }
        }
        throw new BusinessException(String.format("无法为Id：%s；name：%s的类目找到上级类目", subVO.getId(), subVO.getGsCategoryName()));
    }

	@Override
	public PageData<ListVO> goodsListByBrand(IdQTO idQTO) {
        QueryWrapper<GoodsInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("brand_id", idQTO.getId());
        IPage<GoodsInfo> page = MybatisPlusUtil.pager(idQTO);
        IPage<GoodsInfo> pageData = iGoodsInfoRepository.page(page, queryWrapper);
        return new PageData(pageData.getRecords(), idQTO.getPageNum(), idQTO.getPageSize(), pageData.getTotal());
	}

	@Override
	public CtccHomeVO ctcchome(CtccDTO ctccDTO) {
		BbcGoodsCategoryQTO.ListQTO listQTO = new BbcGoodsCategoryQTO.ListQTO();
		listQTO.setParentId("ctcc");
		List<GoodsCategory> categorysList = this.getGoodsCategories(listQTO);
		List<CtccCategoryVO> categorys = new ArrayList<CtccCategoryVO>();
		CtccCategoryVO ctccCategoryVO = null;
		if(CollectionUtils.isNotEmpty(categorysList)){
			for(GoodsCategory goodsCategory:categorysList){
				ctccCategoryVO = new CtccCategoryVO();
				ctccCategoryVO.setName(goodsCategory.getGsCategoryName());
				ctccCategoryVO.setId(goodsCategory.getId());
				ctccCategoryVO.setIdx(goodsCategory.getIdx());
				categorys.add(ctccCategoryVO);
			}
		}
		
		BbcSiteAdvertQTO.SubjectQTO subjectQTO = new BbcSiteAdvertQTO.SubjectQTO();
		subjectQTO.setSubject(SubjectEnum.电信产品.getCode());
		List<BbcSiteAdvertVO.AdvertDetailVO> advertVO = siteAdvertRpc.listBySubject(subjectQTO);
		CtccHomeVO ctccHomeVO = new CtccHomeVO();
    	
    	List<CtccGoodsInfoAdvertVO> adverts = com.gs.lshly.common.utils.BeanUtils.copyList(CtccGoodsInfoAdvertVO.class, advertVO);
    	
    	ctccHomeVO.setAdverts(adverts);//广告位
    	ctccHomeVO.setCategorys(categorys);
		return ctccHomeVO;
	}

	@Override
	public List<BbcGoodsCategoryVO.ListVO> listThirdGoodsCategory(ThirdListDTO dto) {
		List<BbcGoodsCategoryVO.ListVO> retList = new ArrayList<BbcGoodsCategoryVO.ListVO>();
        
        QueryWrapper<GoodsCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("parent_id", dto.getParentId());
//        queryWrapper.eq("use_filed", dto.getUseFiled());
        //获取树结构
        List<GoodsCategory> goodsCategorys = repository.list(queryWrapper);
        if(CollectionUtils.isNotEmpty(goodsCategorys)){
        	for(GoodsCategory goodsCategory:goodsCategorys){
        		queryWrapper = new QueryWrapper<>();
                queryWrapper.in("parent_id", goodsCategory.getId());
                queryWrapper.ne("use_filed", GoodsUsePlatformEnums.B商城.getCode());
                //获取树结构
                List<GoodsCategory> thirds = repository.list(queryWrapper);
                if(CollectionUtils.isNotEmpty(thirds)){
                	BbcGoodsCategoryVO.ListVO listVO = null;
                	for(GoodsCategory third:thirds){
                		listVO = new BbcGoodsCategoryVO.ListVO();
                		
                		BeanCopyUtils.copyProperties(third, listVO);
                		retList.add(listVO);
                	}
                }
        	}
        }
        
        if(retList!=null&&retList.size()>14){
        	retList = retList.subList(0, 14);
        }
		return retList;
	}

	@Override
	public List<String> listGoodsCategoryByParentId(ParentIdDTO dto) {
		
		List<String> retList = new ArrayList<String>();
        
		if(dto==null||StringUtils.isEmpty(dto.getParentId())){
			return retList;
		}
		retList.add(dto.getParentId());
		
        QueryWrapper<GoodsCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("parent_id", dto.getParentId());
        //获取树结构
        List<GoodsCategory> goodsCategorys = repository.list(queryWrapper);
        if(CollectionUtils.isNotEmpty(goodsCategorys)){
        	for(GoodsCategory goodsCategory:goodsCategorys){
        		retList.add(goodsCategory.getId());
        		queryWrapper = new QueryWrapper<>();
                queryWrapper.in("parent_id", goodsCategory.getId());
//                queryWrapper.ne("use_filed", GoodsUsePlatformEnums.B商城.getCode());
                //获取树结构
                List<GoodsCategory> thirds = repository.list(queryWrapper);
                if(CollectionUtils.isNotEmpty(thirds)){
                	for(GoodsCategory third:thirds){
                		retList.add(third.getId());
                	}
                }
        	}
        }
        
		return retList;
	}

}
