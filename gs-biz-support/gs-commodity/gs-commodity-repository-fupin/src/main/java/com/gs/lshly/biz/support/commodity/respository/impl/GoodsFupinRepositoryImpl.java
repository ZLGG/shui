package com.gs.lshly.biz.support.commodity.respository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.commodity.entity.GoodsFupin;
import com.gs.lshly.biz.support.commodity.mapper.GoodsFupinMapper;
import com.gs.lshly.biz.support.commodity.respository.IGoodsFupinRepository;
import org.springframework.stereotype.Service;

@Service
public class GoodsFupinRepositoryImpl extends ServiceImpl<GoodsFupinMapper, GoodsFupin> implements IGoodsFupinRepository {

}
