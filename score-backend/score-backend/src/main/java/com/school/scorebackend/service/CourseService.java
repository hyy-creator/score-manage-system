package com.school.scorebackend.service;

import com.school.scorebackend.entity.Course;
import java.util.List;

public interface CourseService {
    List<Course> list(String courseName);
    List<Course> getByTeacherId(Integer teacherId);
    List<Course> getByStudentId(Integer studentId);
    int add(Course course);
    int update(Course course);
    int delete(Integer id);
}