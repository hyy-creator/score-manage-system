package com.school.scorebackend.mapper;

import com.school.scorebackend.entity.Major;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface MajorMapper {
    List<Major> selectAll(@Param("majorName") String majorName);
    int insert(Major major);
    int update(Major major);
    int deleteById(Integer id);
}