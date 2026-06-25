package com.school.scorebackend.service.impl;

import com.school.scorebackend.entity.User;
import com.school.scorebackend.mapper.UserMapper;
import com.school.scorebackend.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        return userMapper.selectByUsernameAndPwd(username, password);
    }

    @Override
    public List<User> list(String username) {
        return userMapper.selectAll(username);
    }

    @Override
    public int add(User user) {
        // 1. 校验用户名是否重复
        User existUser = userMapper.selectByUsername(user.getUsername());
        if (existUser != null) {
            throw new RuntimeException("用户名已存在，无法新增");
        }
        // 2. 学生角色(role=2)必须传入班级clazzId
        if (user.getRole() == 2 && user.getClazzId() == null) {
            throw new RuntimeException("学生账号必须选择所属班级");
        }
        return userMapper.insert(user);
    }

    @Override
    public int update(User user) {
        // 更新时同样校验学生班级
        if (user.getRole() == 2 && user.getClazzId() == null) {
            throw new RuntimeException("学生账号必须选择所属班级");
        }
        return userMapper.update(user);
    }

    @Override
    public int delete(Integer id) {
        return userMapper.deleteById(id);
    }

    // 新增实现
    @Override
    public User getById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<User> searchByUsername(String username) {
        return userMapper.searchByUsername(username);
    }
}