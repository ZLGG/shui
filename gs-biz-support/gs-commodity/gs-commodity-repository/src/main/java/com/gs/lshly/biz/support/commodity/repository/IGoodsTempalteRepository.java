package com.gs.lshly.biz.support.commodity.repository;

import com.gs.lshly.biz.support.commodity.entity.GoodsTempalte;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.lshly.biz.support.commodity.mapper.GoodsTempalteMapper;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Starry
 * @since 2020-10-13
 */
public interface IGoodsTempalteRepository extends IService<GoodsTempalte> {

    GoodsTempalteMapper baseMapper();

}
