package com.gs.lshly.biz.support.commodity.repository.impl;

import com.gs.lshly.biz.support.commodity.entity.GoodsServeCorTemp;
import com.gs.lshly.biz.support.commodity.mapper.GoodsServeCorTempMapper;
import com.gs.lshly.biz.support.commodity.repository.IGoodsServeCorTempRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品与服务关联列表临时表 服务实现类
 * </p>
 *
 * @author chenyang
 * @since 2021-06-10
 */
@Service
public class GoodsServeCorTempRepositoryImpl extends ServiceImpl<GoodsServeCorTempMapper, GoodsServeCorTemp> implements IGoodsServeCorTempRepository {

}
