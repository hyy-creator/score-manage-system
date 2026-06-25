package com.school.scorebackend.service.impl;

import com.school.scorebackend.entity.Clazz;
import com.school.scorebackend.mapper.ClazzMapper;
import com.school.scorebackend.service.ClazzService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Resource
    private ClazzMapper clazzMapper;

    @Override
    public List<Clazz> list(String clazzName) {
        return clazzMapper.selectAll(clazzName);
    }

    @Override
    public int add(Clazz clazz) {
        return clazzMapper.insert(clazz);
    }

    @Override
    public int update(Clazz clazz) {
        return clazzMapper.update(clazz);
    }

    @Override
    public int delete(Integer id) {
        return clazzMapper.deleteById(id);
    }
}