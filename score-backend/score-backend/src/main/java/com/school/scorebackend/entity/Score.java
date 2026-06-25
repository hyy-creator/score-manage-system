package com.school.scorebackend.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Score {
    private Integer id;
    private Integer studentId;
    private Integer courseId;
    private Integer teacherId;
    private Integer score;
    private LocalDateTime createTime;

    // 页面联表查询展示用（数据库无此字段，仅前端展示）
    private String studentName;
    private String courseName;
    // 新增教师名称字段，接收联表查询的t.username
    private String teacherName;
}