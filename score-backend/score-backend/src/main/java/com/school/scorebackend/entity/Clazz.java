package com.school.scorebackend.entity;

import lombok.Data;

@Data
public class Clazz {
    private Integer id;
    private String clazzName;
    // 所属专业id
    private Integer majorId;
}
