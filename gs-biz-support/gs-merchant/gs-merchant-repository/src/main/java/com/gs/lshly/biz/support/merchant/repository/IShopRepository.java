package com.gs.lshly.biz.support.merchant.repository;

import com.gs.lshly.biz.support.merchant.entity.Shop;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.lshly.common.struct.BaseDTO;

import java.util.List;

/**
 * <p>
 * 商家店铺 服务类
 * </p>
 *
 * @author xxfc
 * @since 2020-10-13
 */
public interface IShopRepository extends IService<Shop> {


    boolean checkIdExist(String id);

    String selectMerchantId(String shopId, BaseDTO baseDTO);


    List<Shop> selectShopByMerchantId(String merchantId, BaseDTO baseDTO);

}
