package com.gs.lshly.biz.support.commodity.repository.impl;

import com.baomidou.mybatisplus.core.mapper.Mapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.commodity.entity.GoodsBrand;
import com.gs.lshly.biz.support.commodity.mapper.GoodsBrandMapper;
import com.gs.lshly.biz.support.commodity.repository.IGoodsBrandRepository;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品品牌 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-09-17
 */
@Service
public class GoodsBrandRepositoryImpl extends ServiceImpl<GoodsBrandMapper, GoodsBrand> implements IGoodsBrandRepository {

    @Override
    public Mapper<GoodsBrand> getMapper() {
        return this.getBaseMapper();
    }
}
