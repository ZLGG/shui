package com.gs.lshly.biz.support.merchant.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.merchant.entity.MerchantApplyCategory;
import com.gs.lshly.biz.support.merchant.entity.Shop;
import com.gs.lshly.biz.support.merchant.entity.ShopGoodsCategory;
import com.gs.lshly.biz.support.merchant.enums.MerchantApplyCategoryTypeEnum;
import com.gs.lshly.biz.support.merchant.enums.MerchantApplyStateEnum;
import com.gs.lshly.biz.support.merchant.enums.MerchantShopCategoryApplyStateEnum;
import com.gs.lshly.biz.support.merchant.mapper.MerchantApplyCategoryMapper;
import com.gs.lshly.biz.support.merchant.mapper.views.MerchantApplyCategoryView;
import com.gs.lshly.biz.support.merchant.repository.IMerchantApplyCategoryRepository;
import com.gs.lshly.biz.support.merchant.repository.IShopGoodsCategoryRepository;
import com.gs.lshly.biz.support.merchant.repository.IShopRepository;
import com.gs.lshly.biz.support.merchant.service.platadmin.IMerchantShopCategoryApplyService;
import com.gs.lshly.common.enums.GoodsCategoryLevelEnum;
import com.gs.lshly.common.enums.ShopTypeEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsCategoryVO;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantShopCategoryApplyDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.MerchantShopCategoryApplyQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantShopCategoryApplyVO;
import com.gs.lshly.common.utils.EnumUtil;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsCategoryRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
public class MerchantShopCategoryApplyServiceImpl implements IMerchantShopCategoryApplyService {

    @Autowired
    private IMerchantApplyCategoryRepository repository;

    @Autowired
    private IShopGoodsCategoryRepository shopGoodsCategoryRepository;

    @Autowired
    private IShopRepository shopRepository;

    @Autowired
    private IMerchantApplyCategoryRepository applyCategoryRepository;


    @Autowired
    private MerchantApplyCategoryMapper merchantApplyCategoryMapper;

    @DubboReference
    private IGoodsCategoryRpc goodsCategoryRpc;

    @Override
    public PageData<MerchantShopCategoryApplyVO.ListVO> pageData(MerchantShopCategoryApplyQTO.QTO qto) {
        if (!EnumUtil.checkEnumCode(qto.getState(), MerchantApplyStateEnum.class)) {
            throw new BusinessException("审核状态枚举错误");
        }
        QueryWrapper<MerchantApplyCategoryView> wrapper = MybatisPlusUtil.query();
        if (!MerchantApplyStateEnum.全部.getCode().equals(qto.getState())) {
            wrapper.eq("state", qto.getState());
        }
        wrapper.eq("apply_type", MerchantApplyCategoryTypeEnum.店铺申请.getCode());
        wrapper.orderByDesc("cdate");
        IPage<MerchantApplyCategoryView> page = MybatisPlusUtil.pager(qto);
        merchantApplyCategoryMapper.mapperPageList(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, MerchantShopCategoryApplyVO.ListVO.class, page);
    }

    @Override
    public MerchantShopCategoryApplyVO.DetailVO details(MerchantShopCategoryApplyDTO.IdDTO dto) {
        //店铺信息 +  商品类目信息
        QueryWrapper<MerchantApplyCategoryView> wrapper = MybatisPlusUtil.query();
        wrapper.eq("ac.id", dto.getId());
        MerchantApplyCategoryView view = merchantApplyCategoryMapper.details(wrapper);
        if (null == view) {
            return null;
        }
        MerchantShopCategoryApplyVO.DetailVO detailVO = new MerchantShopCategoryApplyVO.DetailVO();
        BeanUtils.copyProperties(view, detailVO);
        //店铺已有类目 申请的是一级分类
        QueryWrapper<ShopGoodsCategory> shopGoodsCategoryQueryWrapper = MybatisPlusUtil.query();
        shopGoodsCategoryQueryWrapper.eq("shop_id", view.getShopId());
        shopGoodsCategoryQueryWrapper.eq("category_leve", GoodsCategoryLevelEnum.ONE.getCode());
        List<ShopGoodsCategory> shopGoodsCategoryList = shopGoodsCategoryRepository.list(shopGoodsCategoryQueryWrapper);
        if (ObjectUtils.isNotEmpty(shopGoodsCategoryList)) {
            List<String> list = ListUtil.getIdList(ShopGoodsCategory.class,shopGoodsCategoryList,"categoryId");
           List<GoodsCategoryVO.CategoryTreeVO> categoryTreeVOS = goodsCategoryRpc.listCategoryTree(list);
           detailVO.setCategoryTreeVOS(categoryTreeVOS);
        }
        return detailVO;
    }

    @Override
    public void deleteBatchMerchantShopCategoryApply(MerchantShopCategoryApplyDTO.IdListDTO dto) {
        if (ObjectUtils.isEmpty(dto.getIdList())) {
            throw new BusinessException("删除的ID数组不能为空");
        }
        repository.removeByIds(dto.getIdList());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void apply(MerchantShopCategoryApplyDTO.ApplyDTO dto) {
        if (StringUtils.isBlank(dto.getId())) {
            throw new BusinessException("ID不能为空");
        }
        if (!EnumUtil.checkEnumCodeWithExcludes(dto.getState(), MerchantShopCategoryApplyStateEnum.class,
                MerchantShopCategoryApplyStateEnum.待审.getCode())) {
            throw new BusinessException("审核状态错误");
        }
        MerchantApplyCategory merchantShopCategoryApply = repository.getById(dto.getId());
        if (null == merchantShopCategoryApply) {
            throw new BusinessException("无效的ID");
        }
        if (dto.getState().equals(MerchantShopCategoryApplyStateEnum.拒审.getCode())) {
            merchantShopCategoryApply.setState(dto.getState());
            merchantShopCategoryApply.setRevokeWhy(dto.getRevokeWhy());
            repository.updateById(merchantShopCategoryApply);
        } else if (dto.getState().equals(MerchantShopCategoryApplyStateEnum.通过.getCode())) {
            QueryWrapper<ShopGoodsCategory> countQuerWrapper = MybatisPlusUtil.query();
            countQuerWrapper.eq("category_id", merchantShopCategoryApply.getGoodsCategoryId());
            countQuerWrapper.eq("shop_id",merchantShopCategoryApply.getShopId());
            int count = shopGoodsCategoryRepository.count(countQuerWrapper);
            if (count > 0) {
                throw new BusinessException("申请的商品类目店铺已存在");
            }
            //把商品分类1-3级复制到店铺申请的分类下（自营店是全类目也不需要编辑费用，没有复制）
            List<String> categoryIdList = new ArrayList<>();
            categoryIdList.add(merchantShopCategoryApply.getGoodsCategoryId());
            List<GoodsCategoryVO.CategoryTreeVO> categoryTree = goodsCategoryRpc.listCategoryTree(categoryIdList);
            if (ObjectUtils.isEmpty(categoryTree)) {
                throw new BusinessException("商品类目数据错误");
            }
            if (ObjectUtils.isNotEmpty(categoryTree)) {
                shopGoodsCategoryRepository.shopGoodsCategoryBatchSave(merchantShopCategoryApply.getMerchantId(),merchantShopCategoryApply.getShopId(),categoryTree);
                merchantShopCategoryApply.setState(MerchantShopCategoryApplyStateEnum.通过.getCode());
                repository.updateById(merchantShopCategoryApply);

                //如果店铺类型为类目专营店，申请成功后店铺类型升级成多品类
                Shop shop =  shopRepository.getById(merchantShopCategoryApply.getShopId());
                if (null == shop){
                    throw new BusinessException("店铺不存在！");
                }
                if (shop.getShopType().equals(ShopTypeEnum.类目专营店.getCode())){
                    Shop shop1 = new Shop();
                    shop1.setId(shop.getId());
                    shop1.setShopType(ShopTypeEnum.多品类通用.getCode());
                    shopRepository.updateById(shop1);
                }
            }
        }
    }

    @Override
    public List<String> innerGetApplyCategoryIdList(String applyId) {
        List<String> categoryIdList = new ArrayList<>();
        QueryWrapper<MerchantApplyCategory> wrapper = MybatisPlusUtil.query();
        wrapper.eq("apply_id",applyId);
        List<MerchantApplyCategory> categories = repository.list(wrapper);
        if (ObjectUtils.isNotEmpty(categories)){
            categoryIdList = ListUtil.getIdList(MerchantApplyCategory.class,categories,"goodsCategoryId");
        }
       return categoryIdList;
    }


}