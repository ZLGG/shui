package com.gs.lshly.biz.support.commodity.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.CategoryBrand;
import com.gs.lshly.biz.support.commodity.entity.GoodsBrand;
import com.gs.lshly.biz.support.commodity.entity.GoodsCategory;
import com.gs.lshly.biz.support.commodity.repository.ICategoryBrandRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsBrandRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsCategoryRepository;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsBrandService;
import com.gs.lshly.common.enums.GoodsCategoryLevelEnum;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.common.CommonShopDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsBrandDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsCategoryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsBrandVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsCategoryVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsBrandVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Starry
 * @Date 20:04 2020/10/19
 */
@Component
public class PCMerchGoodsBrandServiceImpl implements IPCMerchGoodsBrandService {
    @Autowired
    private IGoodsBrandRepository repository;
    @Autowired
    private ICategoryBrandRepository categoryBrandRepository;
    @Autowired
    private IGoodsCategoryRepository goodsCategoryRepository;

    @Override
    public List<PCMerchGoodsBrandVO.ListVO> listGoodsBrand(PCMerchGoodsCategoryDTO.IdDTO dto) {
        QueryWrapper<CategoryBrand> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("category_id",dto.getId());
        List<CategoryBrand> list = categoryBrandRepository.list(queryWrapper);
        if (ObjectUtils.isEmpty(list)){
            throw new BusinessException("该类目还没有绑定的品牌信息，请先去绑定品牌！");
        }
        List<PCMerchGoodsBrandVO.ListVO> listVOS = new ArrayList<>();
        //获取关联表中品牌的信息
        for (CategoryBrand str:list) {
            PCMerchGoodsBrandVO.ListVO listVO = new PCMerchGoodsBrandVO.ListVO();
            GoodsBrand brand = repository.getById(str.getBrandId());
            if (brand != null){
                BeanUtils.copyProperties(brand,listVO);
                listVOS.add(listVO);
            }
        }
        return listVOS;
    }

    @Override
    public PCMerchGoodsBrandVO.ListVO getBrandVOById(PCMerchGoodsBrandDTO.IdDTO dto) {
        if (dto == null){
            throw new BusinessException("参数不能我空！");
        }
        QueryWrapper<GoodsBrand> queryWrapperBoost = MybatisPlusUtil.query();
        queryWrapperBoost.eq("id",dto.getId());
        GoodsBrand brand = repository.getOne(queryWrapperBoost);
        if (brand == null){
            throw new BusinessException("数据异常！");
        }
        PCMerchGoodsBrandVO.ListVO listVO = new PCMerchGoodsBrandVO.ListVO();
        BeanUtils.copyProperties(brand,listVO);
        return listVO;
    }

    @Override
    public List<PCMerchGoodsBrandVO.ListVO> listBrandVO(BaseQTO qto) {
        QueryWrapper<GoodsBrand> wrapper = MybatisPlusUtil.query();
        List<GoodsBrand> brandVOS = repository.list(wrapper);
        if (ObjectUtils.isEmpty(brandVOS)){
            return new ArrayList<>();
        }
        List<PCMerchGoodsBrandVO.ListVO> listVOS = ListUtil.listCover(PCMerchGoodsBrandVO.ListVO.class,brandVOS);
        return listVOS;
    }

    @Override
    public List<PCMerchGoodsBrandVO.ListVO> listBrandVOByCategoryLevel1(PCMerchGoodsCategoryDTO.IdListDTO dto) {
        if (ObjectUtils.isEmpty(dto) || ObjectUtils.isEmpty(dto.getIdList())){
            throw new BusinessException("参数不能为空");
        }
        //获取类目id列表
        List<String> categoryIdList = getChildrenCategoryIdList(dto.getIdList());
        if (ObjectUtils.isEmpty(categoryIdList)){
            return new ArrayList<>();
        }
        //获取品牌id列表
        List<String> brandIdList = getBrandIdList(categoryIdList);
        if (ObjectUtils.isEmpty(brandIdList)){
            return new ArrayList<>();
        }
        QueryWrapper<GoodsBrand> wrapper = MybatisPlusUtil.query();
        wrapper.in("id",brandIdList);
        List<GoodsBrand> brands = repository.list(wrapper);
        if (ObjectUtils.isEmpty(brands)){
            throw new BusinessException("品牌信息异常");
        }
        List<PCMerchGoodsBrandVO.ListVO> listVOS = ListUtil.listCover(PCMerchGoodsBrandVO.ListVO.class,brands);
        return listVOS;
    }

    @Override
    public ResponseData<Void> innerCheckMerchantApplyBrand(CommonShopDTO.CategoryETO categoryETO, String brandId, String brandName, Integer isNew) {

        GoodsCategory goodsCategory = goodsCategoryRepository.getById(categoryETO.getGoodsCategoryId());
        if(null == goodsCategory){
            return ResponseData.fail("商品类目不存在");
        }
        if(!GoodsCategoryLevelEnum.ONE.getCode().equals(goodsCategory.getGsCategoryLevel())){
            return ResponseData.fail("不是一级商品类目");
        }
        if(!goodsCategory.getGsCategoryName().equals(categoryETO.getGoodsCategoryName())){
            return ResponseData.fail("商品类目ID和名字不匹配");
        }
       if(TrueFalseEnum.是.getCode().equals(isNew)){
           QueryWrapper<GoodsBrand> goodsBrandQueryWrapper = MybatisPlusUtil.query();
           goodsBrandQueryWrapper.eq("brand_name",brandName);
           List<GoodsBrand> brandList = repository.list(goodsBrandQueryWrapper);
           if(ObjectUtils.isNotEmpty(brandList)){
               List<String> brandIdList = ListUtil.getIdList(GoodsBrand.class,brandList);
               QueryWrapper<CategoryBrand> categoryBrandQueryWrapper = MybatisPlusUtil.query();
               categoryBrandQueryWrapper.in("brand_id",brandIdList);
               List<CategoryBrand> categoryBrandList = categoryBrandRepository.list(categoryBrandQueryWrapper);
               if(ObjectUtils.isNotEmpty(categoryBrandList)){
                   for(CategoryBrand categoryBrand:categoryBrandList){
                       if(categoryBrand.getCategoryId().equals(categoryETO.getGoodsCategoryId())){
                           return ResponseData.fail("商品类目已有该品牌");
                       }
                   }
               }
           }
           return ResponseData.success();
       }else if(TrueFalseEnum.否.getCode().equals(isNew)){
           GoodsBrand goodsBrand  = repository.getById(brandId);
           if(null == goodsBrand){
               return ResponseData.fail("品牌不存在");
           }
           if(!goodsBrand.getBrandName().equals(brandName)){
               return ResponseData.fail("品牌ID与名字不匹配");
           }
           QueryWrapper<CategoryBrand> categoryBrandQueryWrapper = MybatisPlusUtil.query();
           categoryBrandQueryWrapper.eq("brand_id",brandId);
           categoryBrandQueryWrapper.eq("category_id",categoryETO.getGoodsCategoryId());
           List<CategoryBrand> categoryBrandList = categoryBrandRepository.list(categoryBrandQueryWrapper);
           if(ObjectUtils.isEmpty(categoryBrandList)){
               return ResponseData.fail("商品类目没有该品牌");
           }
           return ResponseData.success();
       }
        return ResponseData.fail("是否新品牌枚举错误");
    }

    @Override
    public boolean innerGetBrandVO(String brandName,String categoryId) {
        boolean flag = true;
        QueryWrapper<GoodsBrand> wrapper = MybatisPlusUtil.query();
        wrapper.eq("brand_name",brandName);
        wrapper.select("id","brand_name");
        GoodsBrand brand = repository.getOne(wrapper);
        if (ObjectUtils.isEmpty(brand)){
            flag = false;
        }
        QueryWrapper<CategoryBrand> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("category_id",categoryId);
        queryWrapper.eq("brand_id",brand.getId());
        CategoryBrand categoryBrand = categoryBrandRepository.getOne(queryWrapper);
        if (ObjectUtils.isEmpty(categoryBrand)){
            flag = false;
        }
        return flag;
    }

    private List<String> getChildrenCategoryIdList(List<String> categoryLevel1){
        QueryWrapper<GoodsCategory> wrapper = MybatisPlusUtil.query();
        wrapper.in("parent_id",categoryLevel1);
        List<GoodsCategory> categories = goodsCategoryRepository.list(wrapper);
        if (ObjectUtils.isEmpty(categories)){
            return new ArrayList<>();
        }
        List<String> idList = new ArrayList<>();
        //获取二级类目id列表
        List<String> level2IdList = ListUtil.getIdList(GoodsCategory.class,categories);
        idList.addAll(level2IdList);

        QueryWrapper<GoodsCategory> wrapper1 = MybatisPlusUtil.query();
        wrapper1.in("parent_id",level2IdList);
        List<GoodsCategory> categoryList = goodsCategoryRepository.list(wrapper1);
        if (ObjectUtils.isNotEmpty(categoryList)){
            List<String> level3IdList = ListUtil.getIdList(GoodsCategory.class,categoryList);
            idList.addAll(level3IdList);
        }
        return idList;
    }

    private List<String> getBrandIdList(List<String> categoryIdList){
        QueryWrapper<CategoryBrand> wrapper = MybatisPlusUtil.query();
        wrapper.in("category_id",categoryIdList);
        List<CategoryBrand> categoryBrands = categoryBrandRepository.list(wrapper);
        if (ObjectUtils.isEmpty(categoryBrands)){
            return new ArrayList<>();
        }
        List<String> idList = categoryBrands.stream().map(CategoryBrand::getBrandId).distinct().collect(Collectors.toList());
        return idList;
    }
}
