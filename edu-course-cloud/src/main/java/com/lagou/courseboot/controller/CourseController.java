package com.lagou.courseboot.controller;


import com.github.pagehelper.PageInfo;
import com.github.tobato.fastdfs.service.TrackerClient;
import com.lagou.courseboot.service.CourseService;
import com.lagou.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("course")
@CrossOrigin
public class CourseController {
    @Autowired
    private CourseService courseService;

/*    @Value("{upload.directory}")
    private String uploadDir;*/
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

    @PostMapping("upload")
    public ResponseResult uploadFile(MultipartHttpServletRequest request) throws IOException {
        FileSystem fileSystem = new FileSystem();
        MultipartFile file = request.getFile("fname");
        String oldName = file.getOriginalFilename();
        String hou = oldName.substring(oldName.lastIndexOf(".") + 1);
        String newFileName = UUID.randomUUID().toString() + "." + hou;
        File toSaveFile = new File("D:/pullCloud/" + newFileName);
        file.transferTo(toSaveFile);
        String absolutePath = toSaveFile.getAbsolutePath();
        return null;

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
