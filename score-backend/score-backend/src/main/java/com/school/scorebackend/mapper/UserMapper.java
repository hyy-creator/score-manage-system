package com.school.scorebackend.mapper;

import com.school.scorebackend.entity.User;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface UserMapper {
    // 登录
    User selectByUsernameAndPwd(@Param("username") String username, @Param("password") String password);
    // 查询列表（支持模糊参数）
    List<User> selectAll(@Param("username") String username);
    // 新增
    int insert(User user);
    // 修改
    int update(User user);
    // 删除
    int deleteById(Integer id);
    // 根据id查询用户
    User selectById(@Param("id") Integer id);
    // 用户名查重
    User selectByUsername(@Param("username") String username);
    // 单独模糊搜索
    List<User> searchByUsername(@Param("username") String username);
}