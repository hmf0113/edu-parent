package com.lagou.courseboot.service;

import com.github.pagehelper.PageInfo;
import com.lagou.entity.Course;
import com.lagou.entity.CourseDTO;
import com.lagou.entity.CourseVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseService {
   PageInfo<Course> findCourseList(CourseVO courseVO);

   /**
    * 课程查询功能
    * @param userName 课程名称
    * @param status 状态
    * @return 返回课程列表
    */
   List<CourseDTO> findByCourseNameAndStatus(String userName, Integer status);

   /**
    * 新增课程
    * @param courseDTO
    * @return
    */
   void insertCourse(CourseDTO courseDTO);

   /**
    * 修改课程
    * @param courseDTO
    * @return
    */
   void updateCourse(CourseDTO courseDTO);

   CourseDTO findCourseById(Integer id);

   int updateCourseStatus(Integer status,Integer courseId);

}