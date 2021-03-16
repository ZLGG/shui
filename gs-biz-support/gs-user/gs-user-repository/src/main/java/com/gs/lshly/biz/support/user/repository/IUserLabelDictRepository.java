package com.gs.lshly.biz.support.user.repository;

import com.gs.lshly.biz.support.user.entity.UserLabelDict;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员标签数据 服务类
 * </p>
 *
 * @author xxfc
 * @since 2020-10-05
 */
public interface IUserLabelDictRepository extends IService<UserLabelDict> {

    boolean checkNameIsNotEmptyOnInsert(String name);

    boolean checkNameIsNotEmptyOnUpdate(String id,String name);

}
