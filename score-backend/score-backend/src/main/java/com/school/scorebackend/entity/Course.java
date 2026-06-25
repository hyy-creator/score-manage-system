package com.school.scorebackend.entity;

import lombok.Data;

@Data
public class Course {
    private Integer id;
    private String courseName;
    private Integer teacherId;
    private Integer credit;
    // 新增：授课教师名称，用于学生页面展示
    private String teacherName;
}