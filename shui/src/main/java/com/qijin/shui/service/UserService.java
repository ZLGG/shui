package com.qijin.shui.service;

import com.qijin.shui.entity.User;
import com.qijin.shui.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zlg
 * @Description
 * @Date 2023/4/18 14:14
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    public User createUser(User user) {
        userMapper.addUser(user);
        return user;
    }

    public User updateUser(User user) {
        userMapper.updateUser(user);
        return user;
    }

    public void deleteUser(Long id) {
        userMapper.deleteUser(id);
    }
}
