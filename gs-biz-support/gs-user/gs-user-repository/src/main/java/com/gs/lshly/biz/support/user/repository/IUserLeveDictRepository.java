package com.gs.lshly.biz.support.user.repository;

import com.gs.lshly.biz.support.user.entity.UserLeveDict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 会员等级数据 服务类
 * </p>
 *
 * @author xxfc
 * @since 2020-10-05
 */
public interface IUserLeveDictRepository extends IService<UserLeveDict> {

    boolean checkNameIsNotEmptyOnInsert(String name,Integer leveType);

    boolean checkNameIsNotEmptyOnUpdate(String id,String name,Integer leveType);

    boolean checkIdExist(String id);

}
