package com.school.scorebackend.service.impl;

import com.school.scorebackend.entity.Major;
import com.school.scorebackend.mapper.MajorMapper;
import com.school.scorebackend.service.MajorService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MajorServiceImpl implements MajorService {
    @Resource
    private MajorMapper majorMapper;

    @Override
    public List<Major> list(String majorName) {
        return majorMapper.selectAll(majorName);
    }

    @Override
    public int add(Major major) {
        return majorMapper.insert(major);
    }

    @Override
    public int update(Major major) {
        return majorMapper.update(major);
    }

    @Override
    public int delete(Integer id) {
        return majorMapper.deleteById(id);
    }
}