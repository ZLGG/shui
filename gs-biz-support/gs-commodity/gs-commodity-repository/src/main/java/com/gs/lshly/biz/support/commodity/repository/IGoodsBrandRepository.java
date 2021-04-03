package com.gs.lshly.biz.support.commodity.repository;

import com.baomidou.mybatisplus.core.mapper.Mapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.lshly.biz.support.commodity.entity.GoodsBrand;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品品牌 服务类
 * </p>
 *
 * @author 
 * @since 2020-09-17
 */
public interface IGoodsBrandRepository extends IService<GoodsBrand> {

    Mapper<GoodsBrand> getMapper();
    
    public IPage<GoodsBrand> listByCategory(IPage<GoodsBrand> page, @Param(value = "ew") QueryWrapper<GoodsBrand> qw);
}
