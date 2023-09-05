package com.lagou.courseboot.controller;


import com.github.pagehelper.PageInfo;
import com.lagou.courseboot.service.CourseService;
import com.lagou.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("course")
@CrossOrigin
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Value("{upload.directory}")
    private String uploadDir;
    @PostMapping("findAllCourse")
    public ResponseResult findAllCourse (@RequestBody CourseVO courseVO){
        PageInfo<Course> courseList = courseService.findCourseList(courseVO);
        return new ResponseResult(true,200,"课程查询成功",courseList);
    }
    @PostMapping("findByCourseNameAndStatus")
    public ResponseResult findByCourseNameAndStatus(@RequestParam(value = "courseName",required = false) String courseName, @RequestParam(value = "status",required = false) Integer status){
        List<CourseDTO> andStatus = courseService.findByCourseNameAndStatus(courseName, status);
        return new ResponseResult(true,200,"查询课程成功",andStatus);

    }
    @PostMapping("courseSalesInfo")
    public ResponseResult courseSalesInfo(@RequestBody CourseDTO courseDTO){
        if (courseDTO.getId() == null){
            courseService.insertCourse(courseDTO);
            return new ResponseResult(true,200,"新增成功",StatusCode.SUCCESS);
        }else {
            courseService.updateCourse(courseDTO);
            return new ResponseResult(true,200,"修改成功",null);
        }

    }
    @PostMapping("/upload")
    public ResponseResult uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        if (file.isEmpty()) {
            throw  new RuntimeException();
        }
        String realPath = request.getServletContext().getRealPath("/");
        String substring = realPath.substring(0, realPath.indexOf("Temp"));
        String originalFilename = file.getOriginalFilename();
        String newFileName = System.currentTimeMillis()+originalFilename.substring(originalFilename.lastIndexOf("."));
        String uploadPath = substring+"upload\\";
        File targetFile = new File(uploadPath,newFileName);
        if (!targetFile.getParentFile().exists()){
            targetFile.getParentFile().mkdirs();
            System.out.println("创建目录"+targetFile);
        }
            file.transferTo(targetFile);
        Map<String, String> map = new HashMap<>();
        map.put("fileName",newFileName);
        map.put("filePath","http://localhost:8001/upload/"+newFileName);
        return new ResponseResult(true,200,"上传成功",map);
    }
    /**
     * 查询课程信息
     */
    @GetMapping("findCourseById")
    public ResponseResult findCourseById(@RequestParam("courseId") Integer courseId){
        CourseDTO courseById = courseService.findCourseById(courseId);
        return new ResponseResult(true,200,"查询课程成功",courseById);
    }
    /**
     * 修改课程状态
     */
    @GetMapping("updateCourseStatus")
    public ResponseResult updateCourseStatus(@RequestParam("status") Integer status,@RequestParam("courseId") Integer courseId){
        int i = courseService.updateCourseStatus(status, courseId);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("status",i);
        return new ResponseResult(true,200,"修改课程状态成功",map);
    }
}
