package com.gs.lshly.biz.support.user.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.user.entity.UserLeveDict;
import com.gs.lshly.biz.support.user.mapper.UserLeveDictMapper;
import com.gs.lshly.biz.support.user.repository.IUserLeveDictRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 会员等级数据 服务实现类
 * </p>
 *
 * @author xxfc
 * @since 2020-10-05
 */
@Service
public class UserLeveDictRepositoryImpl extends ServiceImpl<UserLeveDictMapper, UserLeveDict> implements IUserLeveDictRepository {

    @Override
    public boolean checkNameIsNotEmptyOnInsert(String name, Integer leveType) {
        QueryWrapper<UserLeveDict> wrapper = new QueryWrapper();
        wrapper.eq("name",name);
        wrapper.eq("leve_type",leveType);
        int count = this.count(wrapper);
        return count > 0;
    }

    @Override
    public boolean checkNameIsNotEmptyOnUpdate(String id, String name, Integer leveType) {
        QueryWrapper<UserLeveDict> wrapper = new QueryWrapper();
        wrapper.eq("name",name);
        wrapper.eq("leve_type",leveType);
        wrapper.ne("id",id);
        int count = this.count(wrapper);
        return count > 0;
    }

    @Override
    public boolean checkIdExist(String id) {
        UserLeveDict entity =  this.getById(id);
        if(ObjectUtils.isNull(entity)){
            return false;
        }
        return true;
    }
}
