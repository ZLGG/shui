package com.gs.lshly.biz.support.commodity.repository.impl;

import com.gs.lshly.biz.support.commodity.entity.GoodsInfo;
import com.gs.lshly.biz.support.commodity.mapper.GoodsInfoMapper;
import com.gs.lshly.biz.support.commodity.repository.IGoodsInfoRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Starry
 * @since 2020-10-08
 */
@Service
public class GoodsInfoRepositoryImpl extends ServiceImpl<GoodsInfoMapper, GoodsInfo> implements IGoodsInfoRepository {

}
