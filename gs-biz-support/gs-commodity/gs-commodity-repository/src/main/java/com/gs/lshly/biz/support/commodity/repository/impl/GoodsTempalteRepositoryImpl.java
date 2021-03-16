package com.gs.lshly.biz.support.commodity.repository.impl;

import com.gs.lshly.biz.support.commodity.entity.GoodsTempalte;
import com.gs.lshly.biz.support.commodity.mapper.GoodsTempalteMapper;
import com.gs.lshly.biz.support.commodity.repository.IGoodsTempalteRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Starry
 * @since 2020-10-13
 */
@Service
public class GoodsTempalteRepositoryImpl extends ServiceImpl<GoodsTempalteMapper, GoodsTempalte> implements IGoodsTempalteRepository {

    @Override
    public GoodsTempalteMapper baseMapper() {
        return super.getBaseMapper();
    }
}
