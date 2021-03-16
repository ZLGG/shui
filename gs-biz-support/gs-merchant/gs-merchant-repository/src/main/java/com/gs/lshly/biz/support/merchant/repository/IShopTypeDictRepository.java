package com.gs.lshly.biz.support.merchant.repository;

import com.gs.lshly.biz.support.merchant.entity.ShopTypeDict;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 店铺类型 服务类
 * </p>
 *
 * @author xxfc
 * @since 2020-10-14
 */
public interface IShopTypeDictRepository extends IService<ShopTypeDict> {

    boolean checkIdExist(String id);

}
