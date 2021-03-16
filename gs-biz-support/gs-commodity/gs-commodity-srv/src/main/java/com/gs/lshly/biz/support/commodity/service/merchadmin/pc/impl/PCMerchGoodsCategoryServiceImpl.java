package com.gs.lshly.biz.support.commodity.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsCategory;
import com.gs.lshly.biz.support.commodity.repository.IGoodsCategoryRepository;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsCategoryService;
import com.gs.lshly.common.enums.GoodsCategoryLevelEnum;
import com.gs.lshly.common.enums.GoodsUsePlatformEnums;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.CommonShopDTO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsCategoryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsCategoryQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsCategoryVO;
import com.gs.lshly.common.struct.merchadmin.pc.stock.vo.PCMerchStockLogisticsCorpVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Starry
 * @Date 16:45 2020/10/19
 */
@Component
public class PCMerchGoodsCategoryServiceImpl implements IPCMerchGoodsCategoryService {
    @Autowired
    private IGoodsCategoryRepository categoryRepository;
    @DubboReference
    private ICommonShopRpc commonShopRpc;

    @Override
    public List<PCMerchGoodsCategoryVO.CategoryTreeVO> getCategoryVO(PCMerchGoodsCategoryDTO.IdListDTO dto) {
        //获取该店铺信息
        CommonShopVO.MerchantVO merchantVO = commonShopRpc.merchantWithCategoryByShopId(dto.getJwtShopId());
        if (ObjectUtils.isEmpty(merchantVO)){
            throw new BusinessException("店铺不存在或者数据异常！！");
        }
        //获取所有的一级类目信息
        QueryWrapper<GoodsCategory> wrapperBoost = MybatisPlusUtil.query();
        if (ObjectUtils.isNotEmpty(merchantVO.getCategoryList())){
           wrapperBoost.in("id",merchantVO.getCategoryList());
        }
        wrapperBoost.eq("gs_category_level",GoodsCategoryLevelEnum.ONE.getCode());
        List<GoodsCategory> lev1Categories = categoryRepository.list(wrapperBoost);
        return getCategories(lev1Categories);
    }

    @Override
    public List<PCMerchGoodsCategoryVO.CategoryTreeVO> getAllCategoryVO() {

        //获取所有的一级类目信息
        QueryWrapper<GoodsCategory> wrapperBoost = MybatisPlusUtil.query();
        wrapperBoost.eq("gs_category_level",GoodsCategoryLevelEnum.ONE.getCode());
        List<GoodsCategory> lev1Categories = categoryRepository.list(wrapperBoost);
        return getCategories(lev1Categories);
    }

    @Override
    public PCMerchGoodsCategoryVO.ListVO getListVOById(PCMerchGoodsCategoryDTO.IdDTO dto) {
       if (dto == null){
           throw new BusinessException("参数不能我空！");
       }
       QueryWrapper<GoodsCategory> queryWrapperBoost = MybatisPlusUtil.query();
       queryWrapperBoost.eq("id",dto.getId());
       GoodsCategory category = categoryRepository.getOne(queryWrapperBoost);
       if (category == null){
           throw new BusinessException("数据异常！");
       }
       PCMerchGoodsCategoryVO.ListVO listVO = new PCMerchGoodsCategoryVO.ListVO();
       BeanUtils.copyProperties(category,listVO);
        return listVO;
    }

    @Override
    public List<PCMerchGoodsCategoryVO.ListVO> level1Categories() {
        QueryWrapper<GoodsCategory> boost = MybatisPlusUtil.query();
        boost.eq("gs_category_level", GoodsCategoryLevelEnum.ONE.getCode());
        List<GoodsCategory> categories = categoryRepository.list(boost);
        if (ObjectUtils.isEmpty(categories)){
            return new ArrayList<>();
        }
        List<PCMerchGoodsCategoryVO.ListVO> listVOS = categories.stream()
                .map(e ->{
                    PCMerchGoodsCategoryVO.ListVO listVo = new PCMerchGoodsCategoryVO.ListVO();
                    BeanUtils.copyProperties(e,listVo);
                    return listVo;
                }).collect(Collectors.toList());
        return listVOS;
    }

    @Override
    public ResponseData<Void> innerCheckMerchantApplyCategory(List<CommonShopDTO.CategoryETO> categoryList) {
        List<String> goodsCategoryIdList = ListUtil.getIdList(CommonShopDTO.CategoryETO.class,categoryList,"goodsCategoryId");
        if(ObjectUtils.isEmpty(categoryList)){
            return ResponseData.fail("商品分类数组不能为空");
        }
        QueryWrapper<GoodsCategory> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.in("id",goodsCategoryIdList);
        queryWrapper.eq("gs_category_level",GoodsCategoryLevelEnum.ONE.getCode());
        List<GoodsCategory> goodsCategoryList = categoryRepository.list(queryWrapper);
        if(null == goodsCategoryList || goodsCategoryList.size() != goodsCategoryIdList.size()){
            return  ResponseData.fail("商品分类数据错误");
        }
        for(GoodsCategory category:goodsCategoryList){
            for(CommonShopDTO.CategoryETO categoryETO:categoryList){
                if(category.getId().equals(categoryETO.getGoodsCategoryId())){
                    if(!category.getGsCategoryName().equals(categoryETO.getGoodsCategoryName())){
                      return  ResponseData.fail("商品分类ID和名字不匹配");
                    }
                }
            }
        }
        return ResponseData.success();
    }

    @Override
    public PCMerchGoodsCategoryVO.innerCategoryVO innerGetListVo(String categoryName,String parentId) {
        QueryWrapper<GoodsCategory> wrapper = MybatisPlusUtil.query();
        wrapper.eq("gs_category_name",categoryName);
        if (StringUtils.isNotBlank(parentId)){
            wrapper.eq("parent_id",parentId);
        }
        wrapper.last("limit 0,1");
        GoodsCategory category = categoryRepository.getOne(wrapper);
        if (ObjectUtils.isNotEmpty(category)){
            PCMerchGoodsCategoryVO.innerCategoryVO categoryVO = new PCMerchGoodsCategoryVO.innerCategoryVO();
            BeanUtils.copyProperties(category,categoryVO);
            return categoryVO;
        }
        return null;
    }

    @Override
    public PCMerchGoodsCategoryVO.innerCategoryVO innerByIdListVo(String categoryId) {
        GoodsCategory category = categoryRepository.getById(categoryId);
        if (ObjectUtils.isNotEmpty(category)){
            PCMerchGoodsCategoryVO.innerCategoryVO categoryVO = new PCMerchGoodsCategoryVO.innerCategoryVO();
            BeanUtils.copyProperties(category,categoryVO);
            return categoryVO;
        }
        return null;
    }


    private List<PCMerchGoodsCategoryVO.CategoryTreeVO> getCategories(List<GoodsCategory> lev1Categories){
        List<PCMerchGoodsCategoryVO.CategoryTreeVO> list = new ArrayList<>();

        if (ObjectUtils.isNotEmpty(lev1Categories)){
            list = ListUtil.listCover(PCMerchGoodsCategoryVO.CategoryTreeVO.class,lev1Categories);

            for (int i =0;i< list.size();i++) {
                //查询一级类目下的二级类目
                QueryWrapper<GoodsCategory> queryWrapperBoost = MybatisPlusUtil.query();
                queryWrapperBoost.eq("parent_id", list.get(i).getId());
                List<GoodsCategory> lev2Categories = categoryRepository.list(queryWrapperBoost);

                List<PCMerchGoodsCategoryVO.CategoryTreeVO> lev2List = new ArrayList<>();
                for (int j = 0; j < lev2Categories.size(); j++) {
                    PCMerchGoodsCategoryVO.CategoryTreeVO lev2 = new PCMerchGoodsCategoryVO.CategoryTreeVO();
                    BeanUtils.copyProperties(lev2Categories.get(j), lev2);
                    lev2List.add(lev2);
                }
                list.get(i).setList(lev2List);
            }
            for (int i = 0; i<list.size();i++){
                for (int j =0 ;j<list.get(i).getList().size();j++){

                    List<PCMerchGoodsCategoryVO.CategoryTreeVO> lev3List = new ArrayList<>();

                    //查询二级类目下的三级类目
                    QueryWrapper<GoodsCategory> boost = MybatisPlusUtil.query();
                    boost.eq("parent_id",list.get(i).getList().get(j).getId());
                    List<GoodsCategory> lev3Categories = categoryRepository.list(boost);
                    for (int z = 0;z<lev3Categories.size();z++){
                        PCMerchGoodsCategoryVO.CategoryTreeVO lev3 = new PCMerchGoodsCategoryVO.CategoryTreeVO();
                        BeanUtils.copyProperties(lev3Categories.get(z),lev3);
                        if (lev3Categories.get(z).getUseFiled().equals(GoodsUsePlatformEnums.B商城.getCode())){
                            lev3.setGsCategoryName(lev3Categories.get(z).getGsCategoryName()+"(2B)");
                        }
                        if (lev3Categories.get(z).getUseFiled().equals(GoodsUsePlatformEnums.B商城和C商城.getCode())){
                            lev3.setGsCategoryName(lev3Categories.get(z).getGsCategoryName()+"(2B和2C)");
                        }
                        if (lev3Categories.get(z).getUseFiled().equals(GoodsUsePlatformEnums.C商城.getCode())){
                            lev3.setGsCategoryName(lev3Categories.get(z).getGsCategoryName()+"(2C)");
                        }
                        lev3List.add(lev3);
                    }
                    list.get(i).getList().get(j).setList(lev3List);
                }
            }
            return list;
        }
        return new ArrayList<>();
    }
}
