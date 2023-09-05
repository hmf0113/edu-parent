package com.lagou.courseboot.controller;

import com.lagou.courseboot.service.CourseContentService;
import com.lagou.courseboot.service.CourseService;
import com.lagou.entity.CourseLesson;
import com.lagou.entity.CourseSection;
import com.lagou.entity.CourseSectionDTO;
import com.lagou.entity.ResponseResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("courseContent")
@CrossOrigin
public class CourseContentController {
    @Autowired
    private CourseContentService courseContentService;

    @GetMapping("findSectionAndLesson")
    public ResponseResult findSectionAndLesson(@Param("courseId") Integer courseId){
        return new ResponseResult(true,200,"查询所有课程成功",courseContentService.findSectionAndLesson(courseId));
    }

    @GetMapping("findCourseById")
    public ResponseResult findCourseById(@RequestParam("courseId") Integer courseId){
        return new ResponseResult(true,200,"课程查询成功",courseContentService.findCourseById(courseId));
    }

    @PostMapping("saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){
        if (courseSection.getId()  != null){
            courseContentService.updateSection(courseSection);
            return new ResponseResult(true,200,"修改成功",null);
        }
        courseContentService.saveSection(courseSection);
        return new ResponseResult(true,200,"新增章节信息成果",null);
    }
    @GetMapping("updateSectionStatus")
    public ResponseResult updateSectionStatus(@RequestParam("id") Integer id,@RequestParam("status") Integer status){
        int i = courseContentService.updateSectionStatus(id, status);
        Map<String , Integer> map = new HashMap<>();
        map.put("status",i);
        return new ResponseResult(true,200,"修改章节状态成功",map);
    }
    @PostMapping("saveLesson")
    public ResponseResult saveLesson(@RequestBody CourseLesson courseLesson){
        courseContentService.saveLesson(courseLesson);
        return new ResponseResult(true,200,"新增课时成功",null);
    }
}
