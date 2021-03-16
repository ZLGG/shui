package com.gs.lshly.biz.support.commodity.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.commodity.entity.GoodsCategory;
import com.gs.lshly.biz.support.commodity.mapper.GoodsCategoryMapper;
import com.gs.lshly.biz.support.commodity.repository.IGoodsCategoryRepository;
import org.springframework.stereotype.Service;

/**
 * @Author Starry
 * @Date 15:19 2020/9/27
 */
@Service
public class GoodsCategoryRepositoryImpl extends ServiceImpl<GoodsCategoryMapper, GoodsCategory> implements IGoodsCategoryRepository {
}
