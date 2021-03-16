package com.gs.lshly.biz.support.merchant.repository.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.merchant.entity.ShopGoodsCategory;
import com.gs.lshly.biz.support.merchant.mapper.ShopGoodsCategoryMapper;
import com.gs.lshly.biz.support.merchant.repository.IShopGoodsCategoryRepository;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsCategoryVO;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 店铺商品类目 服务实现类
 * </p>
 *
 * @author xxfc
 * @since 2020-10-16
 */
@Service
public class ShopGoodsCategoryRepositoryImpl extends ServiceImpl<ShopGoodsCategoryMapper, ShopGoodsCategory> implements IShopGoodsCategoryRepository {

    @Override
    public boolean checkIdExist(String id) {
        ShopGoodsCategory entity =  this.getById(id);
        if(ObjectUtils.isNull(entity)){
            return false;
        }
        return true;
    }
    @Override
    public void shopGoodsCategoryBatchSave(String merchantId,String shopId, List<GoodsCategoryVO.CategoryTreeVO> categoryTree) {
        List<ShopGoodsCategory> shopGoodsCategoryBatchList = new ArrayList<>();
        for (GoodsCategoryVO.CategoryTreeVO categoryTree001 : categoryTree) {
            ShopGoodsCategory shopGoodsCategory = new ShopGoodsCategory();
            shopGoodsCategory.setCategoryId(categoryTree001.getId());
            shopGoodsCategory.setCategoryName(categoryTree001.getGsCategoryName());
            shopGoodsCategory.setCategoryLeve(categoryTree001.getGsCategoryLevel());
            shopGoodsCategory.setSharePrice(categoryTree001.getGsCategoryMoney());
            shopGoodsCategory.setIdx(categoryTree001.getIdx());
            shopGoodsCategory.setCategoryPid(categoryTree001.getParentId());
            shopGoodsCategory.setMerchantId(merchantId);
            shopGoodsCategory.setShopId(shopId);
            shopGoodsCategoryBatchList.add(shopGoodsCategory);
            if (ObjectUtils.isNotEmpty(categoryTree001.getList())) {
                for (GoodsCategoryVO.CategoryTreeVO categoryTree002 : categoryTree001.getList()) {
                    ShopGoodsCategory shopGoodsCategory2 = new ShopGoodsCategory();
                    shopGoodsCategory2.setCategoryId(categoryTree002.getId());
                    shopGoodsCategory2.setCategoryName(categoryTree002.getGsCategoryName());
                    shopGoodsCategory2.setCategoryLeve(categoryTree002.getGsCategoryLevel());
                    shopGoodsCategory2.setIdx(categoryTree002.getIdx());
                    shopGoodsCategory2.setCategoryPid(categoryTree002.getParentId());
                    shopGoodsCategory2.setMerchantId(merchantId);
                    shopGoodsCategory2.setShopId(shopId);
                    shopGoodsCategoryBatchList.add(shopGoodsCategory2);
                    if (ObjectUtils.isNotEmpty(categoryTree002.getList())) {
                        for (GoodsCategoryVO.CategoryTreeVO categoryTree003 : categoryTree002.getList()) {
                            ShopGoodsCategory shopGoodsCategory3 = new ShopGoodsCategory();
                            shopGoodsCategory3.setCategoryId(categoryTree003.getId());
                            shopGoodsCategory3.setCategoryName(categoryTree003.getGsCategoryName());
                            shopGoodsCategory3.setCategoryLeve(categoryTree003.getGsCategoryLevel());
                            shopGoodsCategory3.setFee(categoryTree003.getGsCategoryFee());
                            shopGoodsCategory3.setUseFiled(categoryTree003.getUseFiled());
                            shopGoodsCategory3.setTerminal(categoryTree003.getUseFiled());
                            shopGoodsCategory3.setIdx(categoryTree003.getIdx());
                            shopGoodsCategory3.setCategoryPid(categoryTree003.getParentId());
                            shopGoodsCategory3.setMerchantId(merchantId);
                            shopGoodsCategory3.setShopId(shopId);
                            shopGoodsCategoryBatchList.add(shopGoodsCategory3);
                        }
                    }
                }
            }
        }
        this.saveBatch(shopGoodsCategoryBatchList);
    }


}
