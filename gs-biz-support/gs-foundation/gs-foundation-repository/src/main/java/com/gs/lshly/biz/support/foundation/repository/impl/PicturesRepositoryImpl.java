package com.gs.lshly.biz.support.foundation.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.foundation.entity.Pictures;
import com.gs.lshly.biz.support.foundation.mapper.PicturesMapper;
import com.gs.lshly.biz.support.foundation.repository.IPicturesRepository;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Starry
 * @since 2020-10-06
 */
@Service
public class PicturesRepositoryImpl extends ServiceImpl<PicturesMapper, Pictures> implements IPicturesRepository {

}
