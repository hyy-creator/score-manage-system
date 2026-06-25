package com.school.scorebackend.controller;

import com.school.scorebackend.entity.Score;
import com.school.scorebackend.service.ScoreService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/score")
public class ScoreController {
    @Resource
    private ScoreService scoreService;

    // 新增成绩（增加重复校验）
    @PostMapping("/add")
    public Map<String,Object> add(@RequestBody Score score){
        Map<String,Object> res = new HashMap<>();
        try{
            // 先查询该学生这门课是否已有成绩
            Score existScore = scoreService.getByStuAndCourse(score.getStudentId(), score.getCourseId());
            if(existScore != null){
                res.put("code",500);
                res.put("msg","该学生此课程已经录入过成绩，不可重复提交");
                return res;
            }
            int rows = scoreService.add(score);
            res.put("code",200);
            res.put("msg","成绩录入成功");
        }catch (Exception e){
            res.put("code",500);
            res.put("msg",e.getMessage());
        }
        return res;
    }

    // 查询教师名下选课学生（录入页面使用）
    @GetMapping("/teacher-student")
    public Map<String,Object> getTeacherStu(@RequestParam Integer teacherId){
        Map<String,Object> res = new HashMap<>();
        List<Map<String,Object>> list = scoreService.getTeacherSelectStudent(teacherId);
        res.put("code",200);
        res.put("data",list);
        return res;
    }

    // 教师查看自己全部成绩
    @GetMapping("/teacher")
    public Map<String,Object> teacherScore(@RequestParam Integer teacherId){
        Map<String,Object> res = new HashMap<>();
        List<Score> list = scoreService.getTeacherScore(teacherId);
        res.put("code",200);
        res.put("data",list);
        return res;
    }

    // 学生查看自己成绩
    @GetMapping("/student")
    public Map<String,Object> studentScore(@RequestParam Integer studentId){
        Map<String,Object> res = new HashMap<>();
        List<Score> list = scoreService.getStudentScore(studentId);
        res.put("code",200);
        res.put("data",list);
        return res;
    }
}