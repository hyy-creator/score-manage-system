package com.school.scorebackend.controller;

import com.school.scorebackend.entity.Course;
import com.school.scorebackend.entity.User;
import com.school.scorebackend.service.CourseService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Resource
    private CourseService courseService;

    // 管理员：全部课程
    @GetMapping("/list")
    public Map<String, Object> list(@RequestParam(required = false) String courseName) {
        Map<String, Object> res = new HashMap<>();
        List<Course> list = courseService.list(courseName);
        res.put("code", 200);
        res.put("data", list);
        return res;
    }

    // 教师：自己授课课程（从Session获取登录教师ID）
    @GetMapping("/teacher")
    public Map<String, Object> teacherCourse(HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            res.put("code", 500);
            res.put("msg", "请先登录");
            return res;
        }
        List<Course> list = courseService.getByTeacherId(loginUser.getId());
        res.put("code", 200);
        res.put("data", list);
        return res;
    }

    // 学生：自己选课（从Session获取登录学生ID）
    @GetMapping("/student")
    public Map<String, Object> studentCourse(HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            res.put("code", 500);
            res.put("msg", "请先登录");
            return res;
        }
        List<Course> list = courseService.getByStudentId(loginUser.getId());
        res.put("code", 200);
        res.put("data", list);
        return res;
    }

    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody Course course) {
        Map<String, Object> res = new HashMap<>();
        int rows = courseService.add(course);
        if (rows > 0) {
            res.put("code", 200);
            res.put("msg", "新增课程成功");
        } else {
            res.put("code", 500);
            res.put("msg", "新增课程失败");
        }
        return res;
    }

    @PutMapping("/update")
    public Map<String, Object> update(@RequestBody Course course) {
        Map<String, Object> res = new HashMap<>();
        int rows = courseService.update(course);
        if (rows > 0) {
            res.put("code", 200);
            res.put("msg", "修改课程成功");
        } else {
            res.put("code", 500);
            res.put("msg", "修改课程失败");
        }
        return res;
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Object> delete(@PathVariable Integer id) {
        Map<String, Object> res = new HashMap<>();
        int rows = courseService.delete(id);
        if (rows > 0) {
            res.put("code", 200);
            res.put("msg", "删除课程成功");
        } else {
            res.put("code", 500);
            res.put("msg", "删除课程失败，存在关联成绩无法删除");
        }
        return res;
    }

    // 新增兼容接口：前端传id查询，解决刷新session丢失无数据
    @GetMapping("/teacherById")
    public Map<String, Object> teacherCourseById(@RequestParam Integer teacherId) {
        Map<String, Object> res = new HashMap<>();
        List<Course> list = courseService.getByTeacherId(teacherId);
        res.put("code", 200);
        res.put("data", list);
        return res;
    }

    @GetMapping("/studentById")
    public Map<String, Object> studentCourseById(@RequestParam Integer studentId) {
        Map<String, Object> res = new HashMap<>();
        List<Course> list = courseService.getByStudentId(studentId);
        res.put("code", 200);
        res.put("data", list);
        return res;
    }
}