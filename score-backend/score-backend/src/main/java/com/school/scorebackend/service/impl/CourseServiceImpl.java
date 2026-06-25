package com.school.scorebackend.service.impl;

import com.school.scorebackend.entity.Course;
import com.school.scorebackend.mapper.CourseMapper;
import com.school.scorebackend.service.CourseService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseMapper courseMapper;

    @Override
    public List<Course> list(String courseName) {
        return courseMapper.selectAll(courseName);
    }

    @Override
    public List<Course> getByTeacherId(Integer teacherId) {
        return courseMapper.selectByTeacherId(teacherId);
    }

    @Override
    public List<Course> getByStudentId(Integer studentId) {
        return courseMapper.selectByStudentId(studentId);
    }

    @Override
    public int add(Course course) {
        return courseMapper.insert(course);
    }

    @Override
    public int update(Course course) {
        return courseMapper.update(course);
    }

    @Override
    public int delete(Integer id) {
        return courseMapper.deleteById(id);
    }
}