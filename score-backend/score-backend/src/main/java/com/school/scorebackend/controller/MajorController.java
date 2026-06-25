package com.school.scorebackend.controller;

import com.school.scorebackend.entity.Major;
import com.school.scorebackend.service.MajorService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/major")
public class MajorController {
    @Resource
    private MajorService majorService;

    @GetMapping("/list")
    public Map<String, Object> list(@RequestParam(required = false) String majorName) {
        Map<String, Object> res = new HashMap<>();
        List<Major> list = majorService.list(majorName);
        res.put("code", 200);
        res.put("data", list);
        return res;
    }

    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody Major major) {
        Map<String, Object> res = new HashMap<>();
        int rows = majorService.add(major);
        if (rows > 0) {
            res.put("code", 200);
            res.put("msg", "新增专业成功");
        } else {
            res.put("code", 500);
            res.put("msg", "新增专业失败");
        }
        return res;
    }

    @PutMapping("/update")
    public Map<String, Object> update(@RequestBody Major major) {
        Map<String, Object> res = new HashMap<>();
        int rows = majorService.update(major);
        if (rows > 0) {
            res.put("code", 200);
            res.put("msg", "修改专业成功");
        } else {
            res.put("code", 500);
            res.put("msg", "修改专业失败");
        }
        return res;
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Object> delete(@PathVariable Integer id) {
        Map<String, Object> res = new HashMap<>();
        int rows = majorService.delete(id);
        if (rows > 0) {
            res.put("code", 200);
            res.put("msg", "删除专业成功");
        } else {
            res.put("code", 500);
            res.put("msg", "删除专业失败，存在关联班级/课程无法删除");
        }
        return res;
    }
}