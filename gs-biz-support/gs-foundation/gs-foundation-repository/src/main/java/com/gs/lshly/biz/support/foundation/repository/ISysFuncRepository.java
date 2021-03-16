package com.gs.lshly.biz.support.foundation.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.lshly.biz.support.foundation.entity.SysFunc;
import com.gs.lshly.biz.support.foundation.mapper.SysFuncMapper;

/**
 * <p>
 * 平台功能树 服务类
 * </p>
 *
 * @author lxus
 * @since 2020-12-12
 */
public interface ISysFuncRepository extends IService<SysFunc> {

    SysFuncMapper baseMapper();
}
