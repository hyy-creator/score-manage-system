package com.school.scorebackend.service.impl;

import com.school.scorebackend.entity.Score;
import com.school.scorebackend.entity.User;
import com.school.scorebackend.mapper.ScoreMapper;
import com.school.scorebackend.service.ScoreService;
import com.school.scorebackend.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Resource
    private ScoreMapper scoreMapper;
    // 注入用户服务用于角色校验
    @Resource
    private UserService userService;

    @Override
    public int add(Score score) {
        // 1. 根据studentId查询用户
        User stu = userService.getById(score.getStudentId());
        // 2. 校验：用户不存在 或者 角色不是学生(学生role=2)，直接抛出异常
        if(stu == null || !stu.getRole().equals(2)){
            throw new RuntimeException("输入的ID不是学生，禁止录入成绩");
        }
        return scoreMapper.insert(score);
    }

    @Override
    public List<Score> getTeacherScore(Integer teacherId) {
        return scoreMapper.selectByTeacherId(teacherId);
    }

    @Override
    public List<Score> getStudentScore(Integer studentId) {
        return scoreMapper.selectByStudentId(studentId);
    }

    @Override
    public List<Map<String, Object>> getTeacherSelectStudent(Integer teacherId) {
        return scoreMapper.getTeacherSelectStudent(teacherId);
    }

    @Override
    public Score getByStuAndCourse(Integer studentId, Integer courseId) {
        return scoreMapper.getByStuAndCourse(studentId, courseId);
    }
}