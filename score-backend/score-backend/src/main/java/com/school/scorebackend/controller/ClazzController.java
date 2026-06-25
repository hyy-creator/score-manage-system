package com.school.scorebackend.controller;

import com.school.scorebackend.entity.Clazz;
import com.school.scorebackend.service.ClazzService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clazz")
public class ClazzController {
    @Resource
    private ClazzService clazzService;

    @GetMapping("/list")
    public Map<String, Object> list(@RequestParam(required = false) String clazzName) {
        Map<String, Object> res = new HashMap<>();
        List<Clazz> list = clazzService.list(clazzName);
        res.put("code", 200);
        res.put("data", list);
        return res;
    }

    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody Clazz clazz) {
        Map<String, Object> res = new HashMap<>();
        int rows = clazzService.add(clazz);
        if (rows > 0) {
            res.put("code", 200);
            res.put("msg", "新增班级成功");
        } else {
            res.put("code", 500);
            res.put("msg", "新增班级失败");
        }
        return res;
    }

    @PutMapping("/update")
    public Map<String, Object> update(@RequestBody Clazz clazz) {
        Map<String, Object> res = new HashMap<>();
        int rows = clazzService.update(clazz);
        if (rows > 0) {
            res.put("code", 200);
            res.put("msg", "修改班级成功");
        } else {
            res.put("code", 500);
            res.put("msg", "修改班级失败");
        }
        return res;
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Object> delete(@PathVariable Integer id) {
        Map<String, Object> res = new HashMap<>();
        int rows = clazzService.delete(id);
        if (rows > 0) {
            res.put("code", 200);
            res.put("msg", "删除班级成功");
        } else {
            res.put("code", 500);
            res.put("msg", "删除班级失败，存在关联数据无法删除");
        }
        return res;
    }
}