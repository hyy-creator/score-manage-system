package com.school.scorebackend.service;

import com.school.scorebackend.entity.Clazz;
import java.util.List;

public interface ClazzService {
    List<Clazz> list(String clazzName);
    int add(Clazz clazz);
    int update(Clazz clazz);
    int delete(Integer id);
}