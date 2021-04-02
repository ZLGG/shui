package com.gs.lshly.biz.support.commodity.service.platadmin.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.*;
import com.gs.lshly.biz.support.commodity.mapper.GoodsCategoryMapper;
import com.gs.lshly.biz.support.commodity.repository.*;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsBrandService;
import com.gs.lshly.common.enums.GoodsCategoryLevelEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsBrandDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsBrandVO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsBrandDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsLabelDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsBrandQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsBrandVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * <p>
 * 商品品牌 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-09-17
 */
@Service
public class GoodsBrandServiceImpl implements IGoodsBrandService {

    @Autowired
    private IGoodsBrandRepository repository;

    @Autowired
    private ICategoryBrandRepository categoryBrandRepository;

    @Autowired
    private IGoodsInfoRepository goodsInfoRepository;

    @Autowired
    private IGoodsCategoryRepository iGoodsCategoryRepository;

    @Autowired
    private GoodsCategoryMapper categoryMapper;

    @Autowired
    private IGoodsMaterialLibraryRepository goodsMaterialLibraryRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(GoodsBrandDTO.ETO dto) {
        //数据校验
        checkBrandData(dto);
        GoodsBrand goodsBrand = new GoodsBrand();
        BeanUtils.copyProperties(dto, goodsBrand);
        repository.save(goodsBrand);

        bindCategory(dto.getCategoryIds(),goodsBrand.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(GoodsBrandDTO.IdDTO dto) {
        QueryWrapper<CategoryBrand> wrapperBoost = MybatisPlusUtil.query();
        wrapperBoost.eq("brand_id",dto.getId());
        int count = categoryBrandRepository.count(wrapperBoost);
        if (count>0){
            throw new  BusinessException("该品牌关联了类目不可以删除！");
        }
        repository.removeById(dto.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(GoodsBrandDTO.ETO dto) {
        //数据校验
        checkBrandData(dto);
        if (countBindGoods(dto.getId(),dto.getCategoryIds()) >0){
            throw new BusinessException("该品牌下已经关联了商品不可以直接取消类目与品牌的关联");
        }

        GoodsBrand goodsBrand = new GoodsBrand();
        BeanUtils.copyProperties(dto, goodsBrand);
        repository.updateById(goodsBrand);

        bindCategory(dto.getCategoryIds(),goodsBrand.getId());
    }

    @Override
    public PageData<GoodsBrandVO.ListVO> list(GoodsBrandQTO.QTO qoDTO) {
        QueryWrapper<GoodsBrand> goodsBrandQueryWrapper = new QueryWrapper<>();
        MybatisPlusUtil.like(qoDTO, goodsBrandQueryWrapper, "brand_name");
        MybatisPlusUtil.like(qoDTO, goodsBrandQueryWrapper,"brand_alias");
        goodsBrandQueryWrapper.orderByAsc("idx","id");
        IPage<GoodsBrand> page = MybatisPlusUtil.pager(qoDTO);
        IPage<GoodsBrand> brandIPage = repository.page(page, goodsBrandQueryWrapper);
        if (ObjectUtils.isEmpty(brandIPage) || ObjectUtils.isEmpty(brandIPage.getRecords())){
            return new PageData<>();
        }
        List<GoodsBrandVO.ListVO> listVOS = brandIPage.getRecords().parallelStream().map(e ->{
            GoodsBrandVO.ListVO listVO = new GoodsBrandVO.ListVO();
            BeanUtils.copyProperties(e,listVO);
            QueryWrapper<CategoryBrand> wrapper = MybatisPlusUtil.query();
            wrapper.eq("brand_id",e.getId());
            List<CategoryBrand> categoryBrands = categoryBrandRepository.list(wrapper);
            if (ObjectUtils.isNotEmpty(categoryBrands)){
                List<String> categoryIds = ListUtil.getIdList(CategoryBrand.class,categoryBrands,"categoryId");
                listVO.setCategoryIds(categoryIds);
            }
            return listVO;
        }).collect(Collectors.toList());
        return new PageData<>(listVOS,qoDTO.getPageNum(),qoDTO.getPageSize(),brandIPage.getTotal());
    }

    @Override
    public GoodsBrandVO.DetailVO select(GoodsBrandDTO.IdDTO dto) {
        QueryWrapper<GoodsBrand> brandQueryWrapper = MybatisPlusUtil.query();
        brandQueryWrapper.eq("id", dto.getId());
        GoodsBrand brand = repository.getOne(brandQueryWrapper);
        if (ObjectUtils.isEmpty(brand)){
            throw new BusinessException("数据查询异常或者品牌id不存在！！");
        }
        GoodsBrandVO.DetailVO goodsBrandVO = new GoodsBrandVO.DetailVO();
        BeanUtil.copyProperties(brand,goodsBrandVO);
        return goodsBrandVO;
    }

    @Override
    public List<GoodsBrandVO.ListVO> listGoodsBrand(GoodsCategoryDTO.IdDTO dto) {
        QueryWrapper<CategoryBrand> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id",dto.getId());
        List<CategoryBrand> list = categoryBrandRepository.list(queryWrapper);
        List<GoodsBrandVO.ListVO> listVOS = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(list)){
            //获取关联表中品牌的信息
            for (CategoryBrand str:list) {
                GoodsBrandVO.ListVO listVO = new GoodsBrandVO.ListVO();
                GoodsBrand brand = repository.getById(str.getBrandId());
                BeanUtils.copyProperties(brand,listVO);
                listVOS.add(listVO);
            }
        }
        return listVOS;
    }

    @Override
    public GoodsBrandVO.DetailVO selectByName(GoodsBrandDTO.BrandNameDTO dto) {
        QueryWrapper<GoodsBrand> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("brand_name",dto.getBrandName());
        GoodsBrand goodsBrand = repository.getOne(queryWrapper);
        if (goodsBrand == null){
            throw new BusinessException("数据异常！");
        }
        GoodsBrandVO.DetailVO detailVO = new GoodsBrandVO.DetailVO();
        BeanUtils.copyProperties(goodsBrand,detailVO);
        return detailVO;
    }

    @Override
    public GoodsBrandVO.ListVO getBrandVOById(GoodsBrandDTO.IdDTO dto) {
        if (dto == null){
            throw new BusinessException("参数不能我空！");
        }
        QueryWrapper<GoodsBrand> queryWrapperBoost = MybatisPlusUtil.query();
        queryWrapperBoost.eq("id",dto.getId());
        GoodsBrand brand = repository.getOne(queryWrapperBoost);
        if (brand == null){
            throw new BusinessException("数据异常！");
        }
        GoodsBrandVO.ListVO listVO = new GoodsBrandVO.ListVO();
        BeanUtils.copyProperties(brand,listVO);
        return listVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public  GoodsBrandVO.BrandIdVO saveBrand(GoodsBrandDTO.ETO eto) {
        checkBrandData(eto);
        GoodsBrand brand = new GoodsBrand();
        BeanUtils.copyProperties(eto,brand);
        boolean flag = repository.save(brand);
        if (flag){
            GoodsBrandVO.BrandIdVO brandIdVO = new GoodsBrandVO.BrandIdVO();
            brandIdVO.setId(brand.getId());
            return brandIdVO;
        }
        return null;
    }

    @Override
    public List<GoodsBrandVO.ListVO> innerCheckGoodsBrand(String brandName) {
        QueryWrapper<GoodsBrand> queryWrapper =  MybatisPlusUtil.query();
        queryWrapper.eq("brand_name",brandName);
        List<GoodsBrand> brandList = repository.list(queryWrapper);
        return  ListUtil.listCover(GoodsBrandVO.ListVO.class,brandList);
    }

    @Override
    public String innerAddGoodsBrand(GoodsBrandDTO.ETO dto) {
        QueryWrapper<GoodsBrand> queryWrapper =  MybatisPlusUtil.query();
        queryWrapper.eq("brand_name",dto.getBrandName());
        GoodsBrand goodsBrand =  repository.getOne(queryWrapper);
        if(null == goodsBrand){
            goodsBrand = new GoodsBrand();
            BeanUtils.copyProperties(dto, goodsBrand);
            goodsBrand.setId(null);
            repository.save(goodsBrand);
        }
        return goodsBrand.getId();
    }

    private void checkBrandData(GoodsBrandDTO.ETO dto){
        if (ObjectUtils.isEmpty(dto)){
            throw new BusinessException("参数异常");
        }
        if (StringUtils.isBlank(dto.getBrandName())){
            throw new BusinessException("品牌名称不能为空");
        }
        if (filterSameName(dto) > 0){
            throw new BusinessException(dto.getBrandName()+"名称已存在！");
        }
        if (ObjectUtils.isEmpty(dto.getCategoryIds())){
            throw new BusinessException("请选择要关联的类目！！");
        }
    }
    private int countBindGoods(String brandId,List<String> categoryIds){
        int totalCount = 0;
        QueryWrapper<CategoryBrand> categoryBrandQueryWrapper = MybatisPlusUtil.query();
        categoryBrandQueryWrapper.eq("brand_id",brandId);
        List<CategoryBrand> categoryBrands = categoryBrandRepository.list(categoryBrandQueryWrapper);
        if (ObjectUtils.isEmpty(categoryBrands)){
            return totalCount;
        }
        List<String> categoryIdList = ListUtil.getIdList(CategoryBrand.class,categoryBrands,"categoryId");
        List<String> reduceIds = categoryIdList.stream().filter(item -> !categoryIds.contains(item)).collect(toList());
        if (ObjectUtils.isEmpty(reduceIds)){
            return totalCount;
        }
        QueryWrapper<GoodsInfo> wrapper = MybatisPlusUtil.query();
        wrapper.eq("brand_id",brandId);
        wrapper.in("category_id",reduceIds);
        int count =  goodsInfoRepository.count(wrapper);

        QueryWrapper<GoodsMaterialLibrary> wrapper1 = MybatisPlusUtil.query();
        wrapper1.eq("brand_id",brandId);
        wrapper1.in("category_id",reduceIds);
        int count2 =  goodsMaterialLibraryRepository.count(wrapper1);

        totalCount = count+ count2;
        return totalCount;
    }

    private int filterSameName(GoodsBrandDTO.ETO dto){
        QueryWrapper<GoodsBrand> wrapper = MybatisPlusUtil.query();
        wrapper.eq("brand_name",dto.getBrandName());
        int count = repository.count(wrapper);

        if (StringUtils.isNotEmpty(dto.getId())){
            QueryWrapper<GoodsBrand> boost = MybatisPlusUtil.query();
            boost.eq("id",dto.getId());
            GoodsBrand brand = repository.getOne(boost);
            if (brand.getBrandName().equals(dto.getBrandName())){
                return 0;
            }else {
                return count;
            }
        }
        return count;
    }

    private void bindCategory(List<String> categoryIds,String brandId){
        //先清除绑定关系
        QueryWrapper<CategoryBrand> wrapper = MybatisPlusUtil.query();
        wrapper.eq("brand_id",brandId);
        categoryBrandRepository.remove(wrapper);
        List<CategoryBrand> categoryBrands = new ArrayList<>();
        for (String categoryId : categoryIds){
            CategoryBrand categoryBrand = new CategoryBrand();
            categoryBrand.setCategoryId(categoryId);
            categoryBrand.setBrandId(brandId);
            categoryBrands.add(categoryBrand);
        }
        categoryBrandRepository.saveBatch(categoryBrands);
    }
}
