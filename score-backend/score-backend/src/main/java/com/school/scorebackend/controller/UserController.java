package com.school.scorebackend.controller;

import com.school.scorebackend.entity.User;
import com.school.scorebackend.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    // 登录接口：新增Session存储登录用户
    @GetMapping("/login")
    public Map<String, Object> login(@RequestParam String username,
                                     @RequestParam String password,
                                     HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        User user = userService.login(username, password);
        if (user != null) {
            session.setAttribute("loginUser", user);
            res.put("code", 200);
            res.put("msg", "登录成功");
            res.put("data", user);
        } else {
            res.put("code", 500);
            res.put("msg", "账号或密码错误");
        }
        return res;
    }

    // 查询用户列表（支持模糊搜索）
    @GetMapping("/list")
    public Map<String, Object> list(@RequestParam(required = false) String username) {
        Map<String, Object> res = new HashMap<>();
        List<User> list = userService.list(username);
        res.put("code", 200);
        res.put("data", list);
        return res;
    }

    // 新增用户
    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody User user) {
        Map<String, Object> res = new HashMap<>();
        int rows = userService.add(user);
        if (rows > 0) {
            res.put("code", 200);
            res.put("msg", "新增用户成功");
        } else {
            res.put("code", 500);
            res.put("msg", "新增用户失败");
        }
        return res;
    }

    // 修改用户
    @PutMapping("/update")
    public Map<String, Object> update(@RequestBody User user) {
        Map<String, Object> res = new HashMap<>();
        int rows = userService.update(user);
        if (rows > 0) {
            res.put("code", 200);
            res.put("msg", "修改用户成功");
        } else {
            res.put("code", 500);
            res.put("msg", "修改用户失败");
        }
        return res;
    }
    // 模糊搜索用户
    @GetMapping("/search")
    public List<User> searchUser(@RequestParam String username) {
        return userService.searchByUsername(username);
    }
    // 根据id删除用户

    @DeleteMapping("/delete/{id}")
    public Map<String, Object> delete(@PathVariable Integer id) {
        Map<String, Object> res = new HashMap<>();
        int rows = userService.delete(id);
        if (rows > 0) {
            res.put("code", 200);
            res.put("msg", "删除用户成功");
        } else {
            res.put("code", 500);
            res.put("msg", "删除用户失败");
        }
        return res;
    }
}