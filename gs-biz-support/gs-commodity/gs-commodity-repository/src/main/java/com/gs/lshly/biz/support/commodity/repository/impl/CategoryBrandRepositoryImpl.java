package com.gs.lshly.biz.support.commodity.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.commodity.entity.CategoryBrand;
import com.gs.lshly.biz.support.commodity.mapper.CategoryBrandMapper;
import com.gs.lshly.biz.support.commodity.repository.ICategoryBrandRepository;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Starry
 * @since 2020-09-29
 */
@Service
public class CategoryBrandRepositoryImpl extends ServiceImpl<CategoryBrandMapper, CategoryBrand> implements ICategoryBrandRepository {

}
