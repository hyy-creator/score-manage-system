package com.school.scorebackend.mapper;

import com.school.scorebackend.entity.Clazz;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface ClazzMapper {
    List<Clazz> selectAll(@Param("clazzName") String clazzName);
    int insert(Clazz clazz);
    int update(Clazz clazz);
    int deleteById(Integer id);
}