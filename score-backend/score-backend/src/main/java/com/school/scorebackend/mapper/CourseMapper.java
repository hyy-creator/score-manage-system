package com.school.scorebackend.mapper;

import com.school.scorebackend.entity.Course;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface CourseMapper {
    List<Course> selectAll(@Param("courseName") String courseName);
    List<Course> selectByTeacherId(Integer teacherId);
    List<Course> selectByStudentId(Integer studentId);
    int insert(Course course);
    int update(Course course);
    int deleteById(Integer id);
}