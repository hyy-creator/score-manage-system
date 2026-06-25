package com.school.scorebackend.service;

import com.school.scorebackend.entity.User;
import java.util.List;

public interface UserService {
    User login(String username, String password);
    List<User> list(String username);
    int add(User user);
    int update(User user);
    int delete(Integer id);
    // 新增：根据id查询用户
    User getById(Integer id);
    List<User> searchByUsername(String username);
}