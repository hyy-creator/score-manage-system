package com.school.scorebackend.service;

import com.school.scorebackend.entity.Major;
import java.util.List;

public interface MajorService {
    List<Major> list(String majorName);
    int add(Major major);
    int update(Major major);
    int delete(Integer id);
}