package com.gs.lshly.biz.support.commodity.repository;

import com.gs.lshly.biz.support.commodity.entity.GoodsTempalteTemp;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.lshly.biz.support.commodity.mapper.GoodsTempalteMapper;
import com.gs.lshly.biz.support.commodity.mapper.GoodsTempalteTempMapper;

/**
 * <p>
 * 商品仓储物流配置临时表 服务类
 * </p>
 *
 * @author chenyang
 * @since 2021-06-09
 */
public interface IGoodsTempalteTempRepository extends IService<GoodsTempalteTemp> {

    GoodsTempalteTempMapper baseMapper();
}
