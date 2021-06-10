package com.gs.lshly.biz.support.commodity.repository.impl;

import com.gs.lshly.biz.support.commodity.entity.GoodsShopNavigationTemp;
import com.gs.lshly.biz.support.commodity.mapper.GoodsShopNavigationTempMapper;
import com.gs.lshly.biz.support.commodity.repository.IGoodsShopNavigationTempRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 店铺导航分类关联商品临时表 服务实现类
 * </p>
 *
 * @author chenyang
 * @since 2021-06-10
 */
@Service
public class GoodsShopNavigationTempRepositoryImpl extends ServiceImpl<GoodsShopNavigationTempMapper, GoodsShopNavigationTemp> implements IGoodsShopNavigationTempRepository {

}
