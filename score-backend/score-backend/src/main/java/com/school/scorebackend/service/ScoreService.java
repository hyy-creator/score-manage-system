package com.school.scorebackend.service;

import com.school.scorebackend.entity.Score;
import java.util.List;
import java.util.Map;

public interface ScoreService {
    // 录入成绩
    int add(Score score);
    // 教师查看自己学生成绩
    List<Score> getTeacherScore(Integer teacherId);
    // 学生查看自己成绩
    List<Score> getStudentScore(Integer studentId);
    // 获取教师授课选课学生
    List<Map<String,Object>> getTeacherSelectStudent(Integer teacherId);
    // 根据学生+课程查询成绩
    Score getByStuAndCourse(Integer studentId, Integer courseId);
}