package com.gs.lshly.biz.support.commodity.repository.impl;

import com.gs.lshly.biz.support.commodity.entity.SkuGoodInfo;
import com.gs.lshly.biz.support.commodity.mapper.SkuGoodInfoMapper;
import com.gs.lshly.biz.support.commodity.repository.ISkuGoodInfoRepository;
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
public class SkuGoodInfoRepositoryImpl extends ServiceImpl<SkuGoodInfoMapper, SkuGoodInfo> implements ISkuGoodInfoRepository {

    @Override
    public SkuGoodInfoMapper baseMapper() {
        return this.getBaseMapper();
    }
}
