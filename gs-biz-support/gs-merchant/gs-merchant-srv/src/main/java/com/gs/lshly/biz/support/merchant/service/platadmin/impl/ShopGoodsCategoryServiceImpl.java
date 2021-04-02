package com.gs.lshly.biz.support.merchant.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.merchant.entity.ShopGoodsCategory;
import com.gs.lshly.biz.support.merchant.mapper.ShopGoodsCategoryMapper;
import com.gs.lshly.biz.support.merchant.mapper.views.ShopGoodsCategoryView;
import com.gs.lshly.biz.support.merchant.repository.IShopGoodsCategoryRepository;
import com.gs.lshly.biz.support.merchant.service.platadmin.IShopGoodsCategoryService;
import com.gs.lshly.common.enums.GoodsCategoryLevelEnum;
import com.gs.lshly.common.enums.ShopTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsCategoryVO;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopGoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.ShopGoodsCategoryQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.ShopGoodsCategoryVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsCategoryRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-16
*/
@Component
public class ShopGoodsCategoryServiceImpl implements IShopGoodsCategoryService {

    @Autowired
    private IShopGoodsCategoryRepository repository;

    @Autowired
    private ShopGoodsCategoryMapper shopGoodsCategoryMapper;

    @DubboReference
    private IGoodsCategoryRpc categoryRpc;

    @Override
    public PageData<ShopGoodsCategoryVO.ListVO> pageData(ShopGoodsCategoryQTO.ShopIdQTO qto) {
        if(StringUtils.isBlank(qto.getShopId())){
            throw new BusinessException("店铺ID不能为空");
        }
        QueryWrapper<ShopGoodsCategory> wrapper = MybatisPlusUtil.query();
        wrapper.eq("shop_id",qto.getShopId());
        wrapper.orderByDesc("cdate");
        IPage<ShopGoodsCategoryView> page = MybatisPlusUtil.pager(qto);
        shopGoodsCategoryMapper.pagelist(page,wrapper);
        return MybatisPlusUtil.toPageData(qto, ShopGoodsCategoryVO.ListVO.class, page);
    }

    @Override
    public List<ShopGoodsCategoryVO.GoodsCategoryOneVO> list(ShopGoodsCategoryQTO.ShopIdQTO qto) {
        if (null == qto){
            throw new BusinessException("参数为空,异常！");
        }
        if (ObjectUtils.isEmpty(qto.getShopType())){
            throw new BusinessException("店铺类型不能为空，参数异常！");
        }
        if(StringUtils.isBlank(qto.getShopId())){
            throw new BusinessException("店铺ID不能为空");
        }
        List<ShopGoodsCategory> goodsCategoryList = new ArrayList<>();

        if (qto.getShopType().equals(ShopTypeEnum.运营商自营.getCode())){
            List<GoodsCategoryVO.InnerListVO> innerListVOS = categoryRpc.innerCategoryList(new BaseDTO());
            if (ObjectUtils.isEmpty(innerListVOS)){
                return new ArrayList<>();
            }
            for (GoodsCategoryVO.InnerListVO innerListVO : innerListVOS){
                ShopGoodsCategory goodsCategory = new ShopGoodsCategory();
                goodsCategory.setCategoryId(innerListVO.getId());
                goodsCategory.setCategoryLeve(innerListVO.getGsCategoryLevel());
                goodsCategory.setCategoryPid(StringUtils.isBlank(innerListVO.getParentId())?"":innerListVO.getParentId());
                goodsCategory.setFee(ObjectUtils.isEmpty(innerListVO.getGsCategoryFee())? BigDecimal.ZERO:innerListVO.getGsCategoryFee());
                goodsCategory.setCategoryName(StringUtils.isBlank(innerListVO.getGsCategoryName())?"":innerListVO.getGsCategoryName());
                goodsCategoryList.add(goodsCategory);
            }
        }else {
            QueryWrapper<ShopGoodsCategory> goodsCategoryQueryWrapper = MybatisPlusUtil.query();
            goodsCategoryQueryWrapper.eq("shop_id",qto.getShopId());
            goodsCategoryList = repository.list(goodsCategoryQueryWrapper);
        }
        if(ObjectUtils.isEmpty(goodsCategoryList)){
            return new ArrayList<>();
        }
        List<ShopGoodsCategoryVO.GoodsCategoryOneVO> goodsCategoryVoList = new ArrayList<>();
        for(ShopGoodsCategory goodsCategory:goodsCategoryList){
            if(GoodsCategoryLevelEnum.ONE.getCode().equals(goodsCategory.getCategoryLeve())){
                ShopGoodsCategoryVO.GoodsCategoryOneVO goodsCategoryOneVO = new ShopGoodsCategoryVO.GoodsCategoryOneVO();
                BeanUtils.copyProperties(goodsCategory,goodsCategoryOneVO);
                goodsCategoryVoList.add(goodsCategoryOneVO);
            }
        }
        for(ShopGoodsCategory goodsCategory:goodsCategoryList){
            if(GoodsCategoryLevelEnum.TWO.getCode().equals(goodsCategory.getCategoryLeve())){
                ShopGoodsCategoryVO.GoodsCategoryTwoVO goodsCategoryTwoVO = new ShopGoodsCategoryVO.GoodsCategoryTwoVO();
                BeanUtils.copyProperties(goodsCategory,goodsCategoryTwoVO);
                for(ShopGoodsCategoryVO.GoodsCategoryOneVO categoryOneVO:goodsCategoryVoList){
                    if(categoryOneVO.getCategoryId().equals(goodsCategoryTwoVO.getCategoryPid())){
                        categoryOneVO.getChildList().add(goodsCategoryTwoVO);
                        break;
                    }
                }
            }
        }
        for(ShopGoodsCategory goodsCategory:goodsCategoryList){
            if(GoodsCategoryLevelEnum.THREE.getCode().equals(goodsCategory.getCategoryLeve())){
                ShopGoodsCategoryVO.GoodsCategoryThreeVO goodsCategoryThreeVO = new ShopGoodsCategoryVO.GoodsCategoryThreeVO();
                BeanUtils.copyProperties(goodsCategory,goodsCategoryThreeVO);
                for(ShopGoodsCategoryVO.GoodsCategoryOneVO categoryOneVO:goodsCategoryVoList){
                   for(ShopGoodsCategoryVO.GoodsCategoryTwoVO categoryTwoVO:categoryOneVO.getChildList()){
                       if(categoryTwoVO.getCategoryId().equals(goodsCategoryThreeVO.getCategoryPid())){
                           categoryTwoVO.getChildList().add(goodsCategoryThreeVO);
                           break;
                       }
                   }
                }
            }
        }
        return goodsCategoryVoList;
    }

    @Override
    public List<String> listShopGoodsCategoryLv1(ShopGoodsCategoryQTO.ShopIdQTO qto) {
        QueryWrapper<ShopGoodsCategory> wrapper = MybatisPlusUtil.query();
        wrapper.eq("shop_id",qto.getShopId());
        List<ShopGoodsCategory> shopGoodsCategoryList = repository.list(wrapper);
        return  ListUtil.getIdList(ShopGoodsCategory.class,shopGoodsCategoryList,"categoryId");
    }


    @Override
    public ShopGoodsCategoryVO.DetailVO detailShopGoodsCategory(ShopGoodsCategoryDTO.IdDTO dto) {

        return null;
    }



    @Override
    public void editShopGoodsCategoryPrice(ShopGoodsCategoryDTO.ListPriceETO eto) {
        if(StringUtils.isBlank(eto.getShopId())){
            throw new BusinessException("店铺ID不能为空");
        }
        if(ObjectUtils.isEmpty(eto.getList())){
            throw new BusinessException("编辑的类目数组不能为空");
        }
        List<String> idList = ListUtil.getIdList(ShopGoodsCategoryDTO.PriceETO.class,eto.getList());
        if(ObjectUtils.isEmpty(idList)){
            throw new BusinessException("编辑数据ID不能为空");
        }
        QueryWrapper<ShopGoodsCategory> shopGoodsCategoryQueryWrapper = MybatisPlusUtil.query();
        shopGoodsCategoryQueryWrapper.in("id",idList);
        shopGoodsCategoryQueryWrapper.eq("shop_id",eto.getShopId());
        List<ShopGoodsCategory> goodsCategoryList = repository.list(shopGoodsCategoryQueryWrapper);
        if(null == goodsCategoryList || goodsCategoryList.size() != idList.size()){
            throw new BusinessException("编辑数据不合格,非法数据");
        }
        for(ShopGoodsCategory goodsCategory:goodsCategoryList){
            if(!GoodsCategoryLevelEnum.THREE.getCode().equals(goodsCategory.getCategoryLeve())){
                throw new BusinessException("只能编辑三级商品类目费用");
            }
            for(ShopGoodsCategoryDTO.PriceETO priceETO:eto.getList()){
                if(goodsCategory.getId().equals(priceETO.getId())){
                    goodsCategory.setFee(priceETO.getFee());
                }
            }
        }
        repository.updateBatchById(goodsCategoryList);
    }
}
