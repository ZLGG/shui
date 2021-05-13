package com.gs.lshly.biz.support.commodity.service.platadmin.impl;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import com.gs.lshly.biz.support.commodity.mapper.GoodsInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gs.lshly.biz.support.commodity.entity.CategoryBrand;
import com.gs.lshly.biz.support.commodity.entity.GoodsCategory;
import com.gs.lshly.biz.support.commodity.entity.GoodsCategoryAttribute;
import com.gs.lshly.biz.support.commodity.entity.GoodsCategorySpec;
import com.gs.lshly.biz.support.commodity.entity.GoodsInfo;
import com.gs.lshly.biz.support.commodity.entity.GoodsMaterialLibrary;
import com.gs.lshly.biz.support.commodity.entity.GoodsParams;
import com.gs.lshly.biz.support.commodity.mapper.GoodsCategoryMapper;
import com.gs.lshly.biz.support.commodity.repository.ICategoryBrandRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsCategoryAttributeRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsCategoryRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsCategorySpecRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsInfoRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsMaterialLibraryRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsParamsRepository;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsCategoryService;
import com.gs.lshly.common.enums.GoodsCategoryLevelEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsAttributeDictionaryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsBrandDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsSpecDictionaryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsCategoryQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsCategoryVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.platadmin.merchant.IMerchantShopCategoryApplyRpc;

import cn.hutool.core.collection.ListUtil;


/**
 * @Author Starry
 * @Date 15:52 2020/9/27
 */
@Service
public class GoodsCategoryServiceImpl implements IGoodsCategoryService {

    @Autowired
    private IGoodsCategoryRepository categoryRepository;
    @Autowired
    private GoodsCategoryMapper categoryMapper;
    @Autowired
    private IGoodsParamsRepository paramsRepository;
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;
    @Autowired
    private ICategoryBrandRepository categoryBrandRepository;
    @Autowired
    private IGoodsCategoryAttributeRepository categoryAttributeRepository;
    @Autowired
    private IGoodsCategorySpecRepository categorySpecRepository;
    @Autowired
    private IGoodsInfoRepository goodsInfoRepository;
    @Autowired
    private IGoodsMaterialLibraryRepository goodsMaterialLibraryRepository;

    @DubboReference
    private IMerchantShopCategoryApplyRpc categoryApplyRpc;

    @Override
    public void addCategory(GoodsCategoryDTO.ETO eto) {
        //校验数据
        checkData(eto);

        GoodsCategory category = new GoodsCategory();
        BeanUtils.copyProperties(eto, category);
        if (eto.getGsCategoryLevel().intValue() == GoodsCategoryLevelEnum.THREE.getCode().intValue()) {
            category.setUseFiled(eto.getUsefiled());
        }
        categoryRepository.save(category);
    }

    @Override
    public GoodsCategoryVO.ParentCategoryVO findParentCategoryVO(GoodsCategoryDTO.IdDTO dto) {
        if (dto == null) {
            throw new BusinessException("类目参数不能为空！");
        }
        GoodsCategoryVO.ParentCategoryVO parentCategoryVO = categoryMapper.selectParentCategoryVO(dto.getId());
        return parentCategoryVO;
    }

    @Override
    public GoodsCategoryVO.Level1VO getLevel1Detail(GoodsCategoryDTO.IdDTO dto) {
        GoodsCategoryVO.Level1VO level1VO = new GoodsCategoryVO.Level1VO();
        BeanUtils.copyProperties(this.getCategory(dto), level1VO);
        return level1VO;
    }

    @Override
    public GoodsCategoryVO.Level2VO getLevel2Detail(GoodsCategoryDTO.IdDTO dto) {
        GoodsCategoryVO.Level2VO level2VO = new GoodsCategoryVO.Level2VO();
        BeanUtils.copyProperties(this.getCategory(dto), level2VO);
        return level2VO;
    }

    @Override
    public GoodsCategoryVO.Level3VO getLevel3Detail(GoodsCategoryDTO.IdDTO dto) {
        GoodsCategoryVO.Level3VO level3VO = new GoodsCategoryVO.Level3VO();
        BeanUtils.copyProperties(this.getCategory(dto), level3VO);
        return level3VO;
    }

    @Override
    public List<GoodsCategoryVO.CategoryTreeVO> selectCategoryTreeWithGoods() {
        List<GoodsCategoryVO.CategoryTreeVO> list = new ArrayList<>();
        //获取所有的分类数据
        QueryWrapper<GoodsCategory> boost = MybatisPlusUtil.query();
        boost.orderByAsc("idx", "id");
        List<GoodsCategory> categories = categoryRepository.list(boost);
        if (categories == null || categories.size() == 0) {
            return new ArrayList<>();
        }
        for (GoodsCategory category : categories) {
            GoodsCategoryVO.CategoryTreeVO categoryTreeVO = new GoodsCategoryVO.CategoryTreeVO();
            List<String> goodsIds = goodsInfoMapper.getGoodsByCategory(category.getId());
            if (ObjectUtils.isNotEmpty(goodsIds)) {
                BeanUtils.copyProperties(category, categoryTreeVO);
                list.add(categoryTreeVO);
            }
        }
        //声明一个容器存放树形结构数据
        List<GoodsCategoryVO.CategoryTreeVO> listVOS = new ArrayList<>();
        //获取下面的所有一级分类
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getGsCategoryLevel().intValue() == 1 && StringUtils.isEmpty(list.get(i).getParentId())) {
                listVOS.add(list.get(i));
            }
        }
        //获取二级分类
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < listVOS.size(); j++) {
                //找到对应的父ID
                if (list.get(i).getParentId().equals(listVOS.get(j).getId())) {
                    //找到对应父ID。加入list中某一项对象底下的list
                    listVOS.get(j).getList().add(list.get(i));
                }
            }
        }
        //添加三级
        for (int i = 0; i < listVOS.size(); i++) {
            for (int j = 0; j < listVOS.get(i).getList().size(); j++) {
                //找到对应的父ID
                for (int k = 0; k < list.size(); k++) {
                    if (list.get(k).getParentId().equals(listVOS.get(i).getList().get(j).getId())) {
                        //找到对应父ID。加入list中某一项对象底下的list
                        listVOS.get(i).getList().get(j).getList().add(list.get(k));
                    }
                }
            }
        }
        return listVOS;
    }

    @Override
    public List<GoodsCategoryVO.CategoryTreeVO> listCategory() {
        List<GoodsCategoryVO.CategoryTreeVO> list = new ArrayList<>();
        //获取所有的分类数据
        QueryWrapper<GoodsCategory> boost = MybatisPlusUtil.query();
        boost.orderByAsc("idx", "id");
        List<GoodsCategory> categories = categoryRepository.list(boost);
        if (categories == null || categories.size() == 0) {
            return new ArrayList<>();
        }
        for (GoodsCategory category : categories) {
            GoodsCategoryVO.CategoryTreeVO categoryTreeVO = new GoodsCategoryVO.CategoryTreeVO();
            BeanUtils.copyProperties(category, categoryTreeVO);
            list.add(categoryTreeVO);
        }
        //声明一个容器存放树形结构数据
        List<GoodsCategoryVO.CategoryTreeVO> listVOS = new ArrayList<>();
        //获取下面的所有一级分类
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getGsCategoryLevel().intValue() == 1 && StringUtils.isEmpty(list.get(i).getParentId())) {
                listVOS.add(list.get(i));
            }
        }
        //获取二级分类
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < listVOS.size(); j++) {
                //找到对应的父ID
                if (list.get(i).getParentId().equals(listVOS.get(j).getId())) {
                    //找到对应父ID。加入list中某一项对象底下的list
                    listVOS.get(j).getList().add(list.get(i));
                }
            }
        }
        //添加三级
        for (int i = 0; i < listVOS.size(); i++) {
            for (int j = 0; j < listVOS.get(i).getList().size(); j++) {
                //找到对应的父ID
                for (int k = 0; k < list.size(); k++) {
                    if (list.get(k).getParentId().equals(listVOS.get(i).getList().get(j).getId())) {
                        //找到对应父ID。加入list中某一项对象底下的list
                        listVOS.get(i).getList().get(j).getList().add(list.get(k));
                    }
                }
            }
        }
        return listVOS;
    }

    @Override
    public void updateCategory(GoodsCategoryDTO.ETO eto) {
        //校验数据
        checkData(eto);

        GoodsCategory category = new GoodsCategory();
        BeanUtils.copyProperties(eto, category);
        if (eto.getGsCategoryLevel().intValue() == GoodsCategoryLevelEnum.THREE.getCode().intValue()) {
            category.setUseFiled(eto.getUsefiled());
        }
        categoryRepository.updateById(category);
    }

    @Override
    public void deleteCategory(GoodsCategoryDTO.IdDTO dto) {
        if (dto == null) {
            throw new BusinessException("参数不能为空！");
        }
        //查看该类目下是否有子分类
        if (findChildrenCategory(dto) > 0) {
            throw new BusinessException("删除失败,该分类下有子分类！");
        }
        //查询该类目下是否关联了品牌
        if (countBindBrand(dto) > 0) {
            throw new BusinessException("删除失败,该分类关联了品牌！");
        }
        //查询该类目下是否关联了参数
        if (countBindParams(dto) > 0) {
            throw new BusinessException("删除失败,该分类关联了参数！");
        }
        //查询该类目是否关联了属性
        if (countBindAttribute(dto) > 0) {
            throw new BusinessException("删除失败,该分类关联了属性！");
        }
        //查询该类目是否关联了规格
        if (countBindSpec(dto) > 0) {
            throw new BusinessException("删除失败,该分类关联了规格！");
        }
        categoryRepository.removeById(dto.getId());
    }

    @Override
    public int findChildrenCategory(GoodsCategoryDTO.IdDTO dto) {
        int count = categoryRepository.count(new LambdaQueryWrapper<GoodsCategory>().eq(GoodsCategory::getParentId, dto.getId()));
        return count;
    }

    @Override
    public void updateIdx(List<GoodsCategoryDTO.IdxETO> idxETO) {
        if (idxETO == null || idxETO.size() == 0) {
            throw new BusinessException("参数不能为空！");
        }
        for (GoodsCategoryDTO.IdxETO dto : idxETO) {
            GoodsCategory category = new GoodsCategory();
            BeanUtils.copyProperties(dto, category);
            categoryRepository.updateById(category);
        }
    }

    @Override
    public void updateUseFile(GoodsCategoryDTO.UseFiledETO eto) {
        if (eto == null) {
            throw new BusinessException("参数不能为空！");
        }
        GoodsCategory category = new GoodsCategory();
        BeanUtils.copyProperties(eto, category);
        categoryRepository.updateById(category);
    }

    @Override
    public PageData<GoodsCategoryVO.Level3VO> findLevel3(GoodsCategoryQTO.QTO qto) {
        LambdaQueryWrapper<GoodsCategory> queryWrapper = new LambdaQueryWrapper<GoodsCategory>()
                .like(GoodsCategory::getGsCategoryName, qto.getGsCategoryName())
                .eq(GoodsCategory::getParentId, qto.getId());

        IPage<GoodsCategory> page = categoryRepository.page(new Page(qto.getPageNum(), qto.getPageSize()), queryWrapper);
        List<GoodsCategoryVO.Level3VO> voList = page.getRecords()
                .stream()
                .map(e -> convertVO(e))
                .collect(toList());
        return new PageData(voList, (int) page.getCurrent(), (int) page.getSize(), page.getTotal());
    }

    @Override
    public void bindSpec(GoodsSpecDictionaryDTO.IdListDTO idListDTO, GoodsCategoryDTO.IdDTO dto) {
        //先判断该类目下是否已经关联了规格，若已经关联则先清除关联然后重新再次关联
        QueryWrapper<GoodsCategorySpec> boost = MybatisPlusUtil.query();
        boost.eq("category_id", dto.getId());
        List<GoodsCategorySpec> categorySpecs = categorySpecRepository.list(boost);
        if (ObjectUtils.isNotEmpty(categorySpecs)) {
            for (GoodsCategorySpec categorySpec : categorySpecs) {
                categorySpecRepository.removeById(categorySpec.getId());
            }
        }
        if (idListDTO == null) {
            return;
        }

        for (String specId : idListDTO.getIdList()) {
            GoodsCategorySpec categorySpec = new GoodsCategorySpec();
            categorySpec.setCategoryId(dto.getId());
            categorySpec.setSpecId(specId);
            categorySpecRepository.save(categorySpec);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void bindAttribute(GoodsAttributeDictionaryDTO.IdListDTO idListDTO, GoodsCategoryDTO.IdDTO dto) {
        //先判断该类目下是否已经关联了属性，若已经关联则先清除关联然后重新再次关联
        QueryWrapper<GoodsCategoryAttribute> boost = MybatisPlusUtil.query();
        boost.eq("category_id", dto.getId());
        List<GoodsCategoryAttribute> categoryAttributes = categoryAttributeRepository.list(boost);
        if (ObjectUtils.isNotEmpty(categoryAttributes)) {
            for (GoodsCategoryAttribute categoryAttribute : categoryAttributes) {
                categoryAttributeRepository.removeById(categoryAttribute.getId());
            }
        }

        if (idListDTO == null) {
            return;
        }

        for (String atrributeId : idListDTO.getIdList()) {
            GoodsCategoryAttribute categoryAttribute = new GoodsCategoryAttribute();
            categoryAttribute.setCategoryId(dto.getId());
            categoryAttribute.setAttributeId(atrributeId);
            categoryAttributeRepository.save(categoryAttribute);
        }

    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void bindBrand(GoodsBrandDTO.IdListDTO brandIdListDTO, GoodsCategoryDTO.IdDTO dto) {
        //先判断参数是否为空
        if (dto == null) {
            throw new BusinessException("参数不能为空！");
        }
        //判断品牌id是否为空
        if (ObjectUtils.isEmpty(brandIdListDTO) || ObjectUtils.isEmpty(brandIdListDTO.getIdList())) {
            throw new BusinessException("关联失败,请选择要关联的品牌");
        }

        //判断品牌是否已经关联了商品
        if (countBindGoods(brandIdListDTO.getIdList(),dto.getId()) > 0){
            throw new BusinessException("品牌已经关联商品不可以直接取消！");
        }

        int level = this.getGoodsCategory(dto).getGsCategoryLevel().intValue();
        //判断该类目的等级
        if (level == 1) {
            throw new BusinessException("该类目是一级类目，不可关联品牌！");
        }
        if (level == 2 || level == 3) {

            //若该类目是二级类并类目下关联了品牌，则清除二级类目以及它下面的子分类关联再重新关联
            bindBrand(brandIdListDTO, ListUtil.of(dto.getId()));

            // 非二级分类下，不会存在子类目
            if (level == 2) {
                // 二级类目以下要清理
                List<GoodsCategory> categories = categoryRepository.list(new LambdaQueryWrapper<GoodsCategory>().eq(GoodsCategory::getParentId, dto.getId()));
                if (ObjectUtils.isEmpty(categories)) {
                    return;
                }

                List<String> categoryIds = new ArrayList<>();
                categories.forEach(goodsCategory -> categoryIds.add(goodsCategory.getId()));

                bindBrand(brandIdListDTO, categoryIds);
            }

        }
    }

    private void bindBrand(GoodsBrandDTO.IdListDTO brandIdListDTO, List<String> categoryIds) {

        //清理原有绑定关系
        clearBindBrand(categoryIds);

        //建立绑定关系
        List<CategoryBrand> list = new ArrayList<>();
        for (String categoryId : categoryIds) {
            for (String brandId : brandIdListDTO.getIdList()) {
                CategoryBrand categoryBrand = new CategoryBrand();
                BeanCopyUtils.copyProperties(brandIdListDTO, categoryBrand);
                categoryBrand.setBrandId(brandId);
                categoryBrand.setCategoryId(categoryId);
                list.add(categoryBrand);
            }
        }
        categoryBrandRepository.saveBatch(list);
    }

    @Override
    public GoodsCategoryVO.ListVO getGoodsCategory(GoodsCategoryDTO.IdDTO dto) {
        GoodsCategory category = categoryRepository.getById(dto.getId());
        if (category == null) {
            throw new BusinessException("查询异常");
        }
        GoodsCategoryVO.ListVO listVO = new GoodsCategoryVO.ListVO();
        BeanUtils.copyProperties(category, listVO);
        return listVO;
    }

    @Override
    public int countBindBrand(GoodsCategoryDTO.IdDTO dto) {
        QueryWrapper<CategoryBrand> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", dto.getId());
        return categoryBrandRepository.count(queryWrapper);
    }

    @Override
    public int countBindParams(GoodsCategoryDTO.IdDTO dto) {
        QueryWrapper<GoodsParams> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", dto.getId());
        return paramsRepository.count(queryWrapper);
    }

    @Override
    public int countBindAttribute(GoodsCategoryDTO.IdDTO dto) {
        QueryWrapper<GoodsCategoryAttribute> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("category_id", dto.getId());
        return categoryAttributeRepository.count(queryWrapper);
    }

    @Override
    public int countBindSpec(GoodsCategoryDTO.IdDTO dto) {
        QueryWrapper<GoodsCategorySpec> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("category_id", dto.getId());
        return categorySpecRepository.count(queryWrapper);
    }

    @Override
    public List<GoodsCategoryVO.CategoryExcelVO> listExcelVO() {
        List<GoodsCategoryVO.CategoryExcelVO> list = new ArrayList<>();
        //查询一级分类
        QueryWrapper<GoodsCategory> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq("gs_category_level", GoodsCategoryLevelEnum.ONE.getCode());
        List<GoodsCategory> level1List = categoryRepository.list(categoryQueryWrapper);

        if (ObjectUtils.isNotEmpty(level1List)) {
            for (GoodsCategory level1 : level1List) {
                GoodsCategoryVO.CategoryExcelVO excelVO = new GoodsCategoryVO.CategoryExcelVO();
                excelVO.setLevel1Name(StringUtils.isEmpty(level1.getGsCategoryName()) ? "" : level1.getGsCategoryName());
                excelVO.setMoney(ObjectUtils.isEmpty(level1.getGsCategoryMoney()) ? "" : level1.getGsCategoryMoney().toString());

                QueryWrapper<GoodsCategory> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("parent_id", level1.getId());
                List<GoodsCategory> level2List = categoryRepository.list(queryWrapper);
                if (ObjectUtils.isNotEmpty(level2List)) {
                    for (GoodsCategory level2 : level2List) {

                        QueryWrapper<GoodsCategory> wrapper = new QueryWrapper<>();
                        wrapper.eq("parent_id", level2.getId());
                        List<GoodsCategory> level3List = categoryRepository.list(wrapper);

                        if (ObjectUtils.isNotEmpty(level3List)) {
                            for (GoodsCategory level3 : level3List) {
                                GoodsCategoryVO.CategoryExcelVO excelVO3 = new GoodsCategoryVO.CategoryExcelVO();
                                excelVO3.setLevel2Name(StringUtils.isEmpty(level2.getGsCategoryName()) ? "" : level2.getGsCategoryName());
                                excelVO3.setLevel1Name(StringUtils.isEmpty(level1.getGsCategoryName()) ? "" : level1.getGsCategoryName());
                                excelVO3.setMoney(ObjectUtils.isEmpty(level1.getGsCategoryMoney()) ? "" : level1.getGsCategoryMoney().toString());
                                excelVO3.setLevel3Name(StringUtils.isEmpty(level3.getGsCategoryName()) ? "" : level3.getGsCategoryName());
                                excelVO3.setFee(ObjectUtils.isNotEmpty(level3.getGsCategoryFee()) ? level3.getGsCategoryFee().toString() : "");
                                list.add(excelVO3);
                            }
                        } else {
                            GoodsCategoryVO.CategoryExcelVO excelVO2 = new GoodsCategoryVO.CategoryExcelVO();
                            excelVO2.setLevel2Name(StringUtils.isEmpty(level2.getGsCategoryName()) ? "" : level2.getGsCategoryName());
                            excelVO2.setLevel1Name(StringUtils.isEmpty(level1.getGsCategoryName()) ? "" : level1.getGsCategoryName());
                            excelVO2.setMoney(ObjectUtils.isEmpty(level1.getGsCategoryMoney()) ? "" : level1.getGsCategoryMoney().toString());
                            list.add(excelVO2);
                        }
                    }
                } else {
                    list.add(excelVO);
                }
            }
        }
        return list;
    }

    @Override
    public List<GoodsCategoryVO.CategoryJoinSearchVO> listCategoryLevel1(GoodsCategoryDTO.IdListDTO dto) {
        if (dto == null || dto.getIdList() == null || dto.getIdList().size() == 0) {
            throw new BusinessException("参数不能为空！");
        }
        List<GoodsCategoryVO.CategoryJoinSearchVO> searchVOS = new ArrayList<>();
        for (String id : dto.getIdList()) {
            QueryWrapper<GoodsCategory> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", id);
            queryWrapper.eq("gs_category_level", 1);

            if (categoryRepository.getOne(queryWrapper) == null) {
                throw new BusinessException("查询错误或者该类目不存在！");
            }
            GoodsCategory category = categoryRepository.getById(id);
            GoodsCategoryVO.CategoryJoinSearchVO searchVO = new GoodsCategoryVO.CategoryJoinSearchVO();
            BeanUtils.copyProperties(category, searchVO);

            searchVOS.add(searchVO);
        }
        return searchVOS;
    }


    @Override
    public List<GoodsCategoryVO.CategoryJoinSearchVO> listCategoryLevels(GoodsCategoryDTO.IdDTO dto) {
        if (dto == null) {
            throw new BusinessException("参数不能为空！");
        }
        QueryWrapper<GoodsCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", dto.getId());
        List<GoodsCategory> categories = categoryRepository.list(wrapper);
        if (ObjectUtils.isEmpty(categories)) {
            return new ArrayList<>();
        }
        List<GoodsCategoryVO.CategoryJoinSearchVO> searchVOS = new ArrayList<>();
        for (GoodsCategory category : categories) {
            GoodsCategoryVO.CategoryJoinSearchVO searchVO = new GoodsCategoryVO.CategoryJoinSearchVO();
            BeanUtils.copyProperties(category, searchVO);
            searchVOS.add(searchVO);
        }
        return searchVOS;
    }

    @Override
    public GoodsCategoryVO.ListVO getListVOById(GoodsCategoryDTO.IdDTO dto) {
        if (dto == null) {
            throw new BusinessException("参数不能我空！");
        }
        QueryWrapper<GoodsCategory> queryWrapperBoost = MybatisPlusUtil.query();
        queryWrapperBoost.eq("id", dto.getId());
        GoodsCategory category = categoryRepository.getOne(queryWrapperBoost);
        if (category == null) {
            throw new BusinessException("数据异常！");
        }
        GoodsCategoryVO.ListVO listVO = new GoodsCategoryVO.ListVO();
        BeanUtils.copyProperties(category, listVO);
        return listVO;
    }

    @Override
    public List<GoodsCategoryVO.ListVO> level1Categories() {
        QueryWrapper<GoodsCategory> boost = MybatisPlusUtil.query();
        boost.eq("gs_category_level", GoodsCategoryLevelEnum.ONE.getCode());
        List<GoodsCategory> categories = categoryRepository.list(boost);
        if (ObjectUtils.isEmpty(categories)) {
            return new ArrayList<>();
        }
        List<GoodsCategoryVO.ListVO> listVOS = categories.stream()
                .map(e -> {
                    GoodsCategoryVO.ListVO listVo = new GoodsCategoryVO.ListVO();
                    BeanUtils.copyProperties(e, listVo);
                    return listVo;
                }).collect(toList());
        return listVOS;
    }

    @Override
    public List<GoodsCategoryVO.ListVO> level2Categories(GoodsCategoryDTO.ApplyIdDTO dto) {
        if (null == dto || StringUtils.isBlank(dto.getApplyId())){
            throw new BusinessException("参数为空异常！！");
        }
       List<String> idList = categoryApplyRpc.innerGetApplyCategoryIdList(dto.getApplyId());
        QueryWrapper<GoodsCategory> boost = MybatisPlusUtil.query();
        boost.in("parent_id",idList);
        boost.eq("gs_category_level", GoodsCategoryLevelEnum.TWO.getCode());
        List<GoodsCategory> categories = categoryRepository.list(boost);
        if (ObjectUtils.isEmpty(categories)) {
            return new ArrayList<>();
        }
        List<GoodsCategoryVO.ListVO> listVOS = categories.stream()
                .map(e -> {
                    GoodsCategoryVO.ListVO listVo = new GoodsCategoryVO.ListVO();
                    BeanUtils.copyProperties(e, listVo);
                    return listVo;
                }).collect(toList());
        return listVOS;
    }

    @Override
    public GoodsCategoryVO.CategoryTreeVO categoryTree(GoodsCategoryDTO.IdDTO dto) {
        GoodsCategory category = getCategory(dto);
        GoodsCategoryVO.CategoryTreeVO categoryTreeVO = new GoodsCategoryVO.CategoryTreeVO();
        BeanUtils.copyProperties(category, categoryTreeVO);

        categoryTreeVO.setList(this.getCategory(category.getId()));
        for (GoodsCategoryVO.CategoryTreeVO treeVO : categoryTreeVO.getList()) {
            treeVO.setList(this.getCategory(treeVO.getId()));
        }
        return categoryTreeVO;
    }

    @Override
    public List<GoodsCategoryVO.CategoryTreeVO> listCategoryTree(List<String> idList) {
        List<GoodsCategoryVO.CategoryTreeVO> categoryTreeVOS = new ArrayList<>();
        for (String string : idList) {
            GoodsCategoryVO.CategoryTreeVO treeVO = this.categoryTree(new GoodsCategoryDTO.IdDTO(string));
            categoryTreeVOS.add(treeVO);
        }
        return categoryTreeVOS;
    }

    @Override
    @Transactional
    public void innerRightsCategorySettings(GoodsCategoryDTO.RightsSetting dto) {
        GoodsCategory goodsCategory = new GoodsCategory();
        if (ObjectUtils.isNotEmpty(dto.getRefundDays())){
            goodsCategory.setRefundDays(dto.getRefundDays());
        }else if (ObjectUtils.isNotEmpty(dto.getReturnDays())){
            goodsCategory.setReturnDays(dto.getReturnDays());
        }
        QueryWrapper<GoodsCategory> query = MybatisPlusUtil.query();
        query.and(i->i.eq("gs_category_level",3));
        categoryRepository.update(goodsCategory,query);
    }

    @Override
    public List<GoodsCategoryVO.CategoryJoinSearchVO> innerGetIdAndName(List<String> categoryIds) {
        if (ObjectUtils.isEmpty(categoryIds)){
            return new ArrayList<>();
        }
        QueryWrapper<GoodsCategory> wrapper = MybatisPlusUtil.query();
        wrapper.in("id",categoryIds);
        wrapper.select("id","gs_category_name");
        List<GoodsCategory> categories = categoryRepository.list(wrapper);
        if (ObjectUtils.isEmpty(categories)){
            return new ArrayList<>();
        }
        List<GoodsCategoryVO.CategoryJoinSearchVO> searchVOList = com.gs.lshly.common.utils.ListUtil.listCover(GoodsCategoryVO.CategoryJoinSearchVO.class,categories);
        return searchVOList;
    }

    @Override
    public List<GoodsCategoryVO.InnerListVO> innerCategoryList(BaseDTO dto) {
        List<GoodsCategoryVO.InnerListVO> innerListVOS = new ArrayList<>();
        QueryWrapper<GoodsCategory> wrapper = MybatisPlusUtil.query();
        List<GoodsCategory> categories = categoryRepository.list(wrapper);
        if (ObjectUtils.isNotEmpty(categories)){
            innerListVOS = com.gs.lshly.common.utils.ListUtil.listCover(GoodsCategoryVO.InnerListVO.class,categories);
        }
        return innerListVOS;
    }

    private GoodsCategoryVO.Level3VO convertVO(GoodsCategory e) {
        GoodsCategoryVO.Level3VO vo = new GoodsCategoryVO.Level3VO();
        BeanUtils.copyProperties(e, vo);
        return vo;
    }

    private GoodsCategory getCategory(GoodsCategoryDTO.IdDTO dto) {
        if (dto == null) {
            throw new BusinessException("类目参数不能为空！");
        }
        QueryWrapper<GoodsCategory> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq("id", dto.getId());
        GoodsCategory category = categoryRepository.getOne(categoryQueryWrapper);
        if (category == null) {
            throw new BusinessException("该类目不存在或系统异常！");
        }
        return category;
    }

    private void clearBindBrand(List<String> categoryIds) {
        QueryWrapper<CategoryBrand> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("category_id", categoryIds);
        categoryBrandRepository.remove(queryWrapper);
    }

    private List<GoodsCategoryVO.CategoryTreeVO> getCategory(String parentId) {
        QueryWrapper<GoodsCategory> wrapper = MybatisPlusUtil.query();
        wrapper.eq("parent_id", parentId);
        List<GoodsCategory> categories = categoryRepository.list(wrapper);
        if (ObjectUtils.isEmpty(categories)) {
            return new ArrayList<>();
        }
        List<GoodsCategoryVO.CategoryTreeVO> categoryTreeVOS = com.gs.lshly.common.utils.ListUtil.listCover(GoodsCategoryVO.CategoryTreeVO.class, categories);
        return categoryTreeVOS;
    }

    private void checkData(GoodsCategoryDTO.ETO eto) {
        if (ObjectUtils.isEmpty(eto)){
            throw new BusinessException("参数为空！，异常！！");
        }
        if (StringUtils.isBlank(eto.getGsCategoryName())) {
            throw new BusinessException("请填写分类名称");
        }
        if (filterSameName(eto) > 0) {
            throw new BusinessException(eto.getGsCategoryName() + "类目名称已存在！！");
        }

    }

    private int filterSameName(GoodsCategoryDTO.ETO dto) {
        QueryWrapper<GoodsCategory> wrapper = MybatisPlusUtil.query();
        wrapper.eq("gs_category_name", dto.getGsCategoryName());
        int count = categoryRepository.count(wrapper);

        if (StringUtils.isNotEmpty(dto.getId())) {
            QueryWrapper<GoodsCategory> boost = MybatisPlusUtil.query();
            boost.eq("id", dto.getId());
            GoodsCategory category = categoryRepository.getOne(boost);
            if (category.getGsCategoryName().equals(dto.getGsCategoryName())) {
                return 0;
            } else {
                return count;
            }
        }
        return count;
    }

    private int countBindGoods(List<String> brandIdList,String categoryId){
        int totalCount = 0;
        QueryWrapper<CategoryBrand> categoryBrandQueryWrapper = MybatisPlusUtil.query();
        categoryBrandQueryWrapper.eq("category_id",categoryId);
        List<CategoryBrand> categoryBrands = categoryBrandRepository.list(categoryBrandQueryWrapper);
        if (ObjectUtils.isEmpty(categoryBrands)){
            return totalCount;
        }
        List<String> brandIds = com.gs.lshly.common.utils.ListUtil.getIdList(CategoryBrand.class,categoryBrands,"brandId");
        List<String> reduceIds = brandIds.stream().filter(item -> !brandIdList.contains(item)).collect(toList());
        if (ObjectUtils.isEmpty(reduceIds)){
            return totalCount;
        }
        QueryWrapper<GoodsInfo> wrapper = MybatisPlusUtil.query();
        wrapper.in("brand_id",reduceIds);
        wrapper.eq("category_id",categoryId);
        int count =  goodsInfoRepository.count(wrapper);

        QueryWrapper<GoodsMaterialLibrary> wrapper1 = MybatisPlusUtil.query();
        wrapper1.in("brand_id",reduceIds);
        wrapper1.eq("category_id",categoryId);
        int count2 =  goodsMaterialLibraryRepository.count(wrapper1);

        totalCount = count+ count2;
        return totalCount;
    }
}
