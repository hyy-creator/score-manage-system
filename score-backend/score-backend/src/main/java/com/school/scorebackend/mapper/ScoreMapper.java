package com.school.scorebackend.mapper;

import com.school.scorebackend.entity.Score;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface ScoreMapper {
    int insert(Score score);

    List<Score> selectByTeacherId(@Param("teacherId") Integer teacherId);

    List<Score> selectByStudentId(@Param("studentId") Integer studentId);

    // 获取教师选课学生列表
    List<Map<String,Object>> getTeacherSelectStudent(@Param("teacherId") Integer teacherId);

    // 根据学生+课程查询成绩，判断重复
    Score getByStuAndCourse(@Param("studentId") Integer studentId, @Param("courseId") Integer courseId);
}