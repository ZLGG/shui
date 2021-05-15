package com.gs.lshly.biz.support.user.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.lshly.biz.support.user.entity.User;
import com.gs.lshly.common.struct.bbc.user.dto.UserDTO;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 会员 服务类
 * </p>
 *
 * @author xxfc
 * @since 2020-10-05
 */
@Repository
public interface IUserRepository extends IService<User> {

    void saveUserInfo(UserDTO.ETO user);
}
