package com.qijin.shui.mapper;

import com.qijin.shui.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zlg
 * @Description
 * @Date 2023/4/18 14:08
 */

@Mapper
public interface UserMapper {

    List<User> getAllUsers();

    User getUserById(Long id);

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);
}


