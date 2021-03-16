package com.gs.lshly.biz.support.commodity.repository;

import com.gs.lshly.biz.support.commodity.entity.SkuGoodInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.lshly.biz.support.commodity.mapper.SkuGoodInfoMapper;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Starry
 * @since 2020-10-08
 */
public interface ISkuGoodInfoRepository extends IService<SkuGoodInfo> {

    SkuGoodInfoMapper baseMapper();
}
