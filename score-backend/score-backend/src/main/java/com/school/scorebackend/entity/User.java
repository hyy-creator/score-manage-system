package com.school.scorebackend.entity;

import lombok.Data;
import java.util.Date;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String realName;
    // 0管理员 1教师 2学生
    private Integer role;
    // 学生所属班级id，教师/管理员为null
    private Integer clazzId;
    private Date createTime;
}

