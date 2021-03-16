package com.gs.lshly.biz.support.user.repository.impl;

import com.gs.lshly.biz.support.user.entity.UserLabel;
import com.gs.lshly.biz.support.user.mapper.UserLabelMapper;
import com.gs.lshly.biz.support.user.repository.IUserLabelRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员标签关系 服务实现类
 * </p>
 *
 * @author xxfc
 * @since 2020-10-05
 */
@Service
public class UserLabelRepositoryImpl extends ServiceImpl<UserLabelMapper, UserLabel> implements IUserLabelRepository {

}
