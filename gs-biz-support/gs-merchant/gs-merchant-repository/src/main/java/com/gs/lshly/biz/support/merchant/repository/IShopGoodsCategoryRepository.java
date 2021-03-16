package com.gs.lshly.biz.support.merchant.repository;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.lshly.biz.support.merchant.entity.ShopGoodsCategory;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsCategoryVO;

import java.util.List;

/**
 * <p>
 * 店铺商品类目 服务类
 * </p>
 *
 * @author xxfc
 * @since 2020-10-16
 */
public interface IShopGoodsCategoryRepository extends IService<ShopGoodsCategory> {

    boolean checkIdExist(String id);


    /** 两个表的字段不统一，但是业务以接，不方便更改，这里做字段对应 **/
    void shopGoodsCategoryBatchSave(String merchantId,String shopId, List<GoodsCategoryVO.CategoryTreeVO> categoryTree);


}
