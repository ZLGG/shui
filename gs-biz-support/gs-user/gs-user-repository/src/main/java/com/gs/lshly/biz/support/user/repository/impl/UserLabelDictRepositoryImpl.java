package com.gs.lshly.biz.support.user.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.user.entity.UserLabelDict;
import com.gs.lshly.biz.support.user.mapper.UserLabelDictMapper;
import com.gs.lshly.biz.support.user.repository.IUserLabelDictRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员标签数据 服务实现类
 * </p>
 *
 * @author xxfc
 * @since 2020-10-05
 */
@Service
public class UserLabelDictRepositoryImpl extends ServiceImpl<UserLabelDictMapper, UserLabelDict> implements IUserLabelDictRepository {

    @Override
    public boolean checkNameIsNotEmptyOnInsert(String name) {
        QueryWrapper<UserLabelDict> wrapper = new QueryWrapper();
        wrapper.eq("label_name",name);
        int count = this.count(wrapper);
        return count > 0;
    }

    @Override
    public boolean checkNameIsNotEmptyOnUpdate(String id, String name) {
        QueryWrapper<UserLabelDict> wrapper = new QueryWrapper();
        wrapper.eq("label_name",name);
        wrapper.ne("id",id);
        int count = this.count(wrapper);
        return count > 0;
    }
}
