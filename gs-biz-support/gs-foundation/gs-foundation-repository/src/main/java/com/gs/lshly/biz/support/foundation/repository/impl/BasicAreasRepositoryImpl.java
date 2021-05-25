package com.gs.lshly.biz.support.foundation.repository.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.foundation.entity.BasicAreas;
import com.gs.lshly.biz.support.foundation.mapper.BasicAreasMapper;
import com.gs.lshly.biz.support.foundation.repository.IBasicAreasRepository;

/**
 * <p>
 * 企业证照 服务实现类
 * </p>
 *
 * @author xxfc
 * @since 2020-10-09
 */
@Service
public class BasicAreasRepositoryImpl extends ServiceImpl<BasicAreasMapper, BasicAreas> implements IBasicAreasRepository {

}
