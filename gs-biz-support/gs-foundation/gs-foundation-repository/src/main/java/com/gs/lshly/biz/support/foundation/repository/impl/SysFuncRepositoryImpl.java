package com.gs.lshly.biz.support.foundation.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.foundation.entity.SysFunc;
import com.gs.lshly.biz.support.foundation.mapper.SysFuncMapper;
import com.gs.lshly.biz.support.foundation.repository.ISysFuncRepository;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 平台功能树 服务实现类
 * </p>
 *
 * @author lxus
 * @since 2020-12-12
 */
@Service
public class SysFuncRepositoryImpl extends ServiceImpl<SysFuncMapper, SysFunc> implements ISysFuncRepository {

    @Override
    public SysFuncMapper baseMapper() {
        return this.getBaseMapper();
    }
}
