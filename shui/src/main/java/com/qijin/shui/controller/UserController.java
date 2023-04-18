package com.qijin.shui.controller;

import com.qijin.shui.entity.User;
import com.qijin.shui.request.UserRequest;
import com.qijin.shui.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Api(tags = "用户管理") // 添加 Swagger 标签
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    @ApiOperation("根据ID获取用户信息") // 添加 Swagger API 操作描述
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    @ApiOperation("获取所有用户信息") // 添加 Swagger API 操作描述
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/add")
    @ApiOperation("添加用户") // 添加 Swagger API 操作描述
    public ResponseEntity<User> addUser(@RequestBody UserRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        User addedUser = userService.createUser(user);
        return ResponseEntity.ok(addedUser);
    }

    @PutMapping("/updateUser")
    @ApiOperation("更新用户信息") // 添加 Swagger API 操作描述
    public ResponseEntity<User> updateUser( @RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据ID删除用户信息") // 添加 Swagger API 操作描述
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
