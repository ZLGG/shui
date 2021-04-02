package com.gs.lshly.biz.support.commodity.repository;

import com.baomidou.mybatisplus.core.mapper.Mapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.lshly.biz.support.commodity.entity.GoodsCategory;
import com.gs.lshly.biz.support.commodity.mapper.GoodsCategoryMapper;

/**
 * @Author Starry
 * @Date 15:19 2020/9/27
 */
public interface IGoodsCategoryRepository extends IService<GoodsCategory> {

    GoodsCategoryMapper getMapper();

}
