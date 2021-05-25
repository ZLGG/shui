package com.gs.lshly.biz.support.commodity.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.commodity.entity.GoodsServe;
import com.gs.lshly.biz.support.commodity.mapper.GoodsServeMapper;
import com.gs.lshly.biz.support.commodity.repository.IGoodsServeRepository;
import org.springframework.stereotype.Service;

/**
 * 商品服务 服务实现类
 *
 * @author hanly
 * @since 2021-05-25
 */
@Service
public class GoodsServeRepositoryImpl extends ServiceImpl<GoodsServeMapper, GoodsServe> implements IGoodsServeRepository {
}
