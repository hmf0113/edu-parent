package com.lagou.courseboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.courseboot.mapper.TeacherMapper;
import com.lagou.entity.*;
import com.lagou.courseboot.mapper.CourseMapper;
import com.lagou.courseboot.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public PageInfo<Course> findCourseList(CourseVO courseVO) {
        //设置分页参数
        PageHelper.startPage(courseVO.getCurrentPage(), courseVO.getPageSize());
        QueryWrapper<Course> qw = new QueryWrapper<>();
        if (courseVO.getCourseName() != null && !courseVO.getCourseName().isEmpty()){
            qw.like("course_name",courseVO.getCourseName());
        }
        if (courseVO.getStatus() != null){
            qw.eq("status",courseVO.getStatus());
        }
        qw.orderByAsc("sort_num");
        //执行查询结果
        List<Course> courses = courseMapper.selectList(qw);
        //封装数据到分页里面
        PageInfo<Course> pageInfo = new PageInfo<>(courses);
        return pageInfo;
    }


    private List<Course> getInitCourse(){
        QueryWrapper<Course> qw = new QueryWrapper<>();
        qw.eq("status",1);
        qw.eq("is_del",Boolean.FALSE);
        qw.orderByDesc("sort_num");
        return this.courseMapper.selectList(qw);
    }
    private void setTeacher(CourseDTO courseDTO){
        QueryWrapper qw = new QueryWrapper<>();
        qw.eq("course_id",courseDTO.getId());
        qw.eq("is_del",Boolean.FALSE);
        Teacher teacher = this.teacherMapper.selectOne(qw);
        courseDTO.setTeacher(teacher);
    }

    @Override
    public List<CourseDTO> findByCourseNameAndStatus(String courseName, Integer status) {

        QueryWrapper<Course> qw = new QueryWrapper<>();
        if (courseName != null && !courseName.isEmpty()){
            qw.like("course_name",courseName);
        }
        if (status != null){
            qw.eq("status",status);
        }
        if (status == null && (courseName == null || courseName.isEmpty())){
            return convertToDTOList(courseMapper.selectList(null));
        }

        qw.and(courseQueryWrapper -> courseQueryWrapper.like("course_name",courseName).or().eq("status",status));
        List<Course> courses = courseMapper.selectList(qw);
        ArrayList<CourseDTO> dtos = new ArrayList<>();
        for (Course cours : courses) {
            CourseDTO dto = new CourseDTO();
            dto.setPrice(cours.getPrice());
            dto.setId(cours.getId());
            dto.setCourseName(cours.getCourseName());
            dto.setStatus(cours.getStatus());
            dto.setSales(cours.getSales());
            dtos.add(dto);
        }
        return dtos;

    }


    private List<CourseDTO> convertToDTOList(List<Course> courses){
        ArrayList<CourseDTO> list = new ArrayList<>();
        for (Course cours : courses) {
            CourseDTO dto = new CourseDTO();
            dto.setPrice(cours.getPrice());
            dto.setId(cours.getId());
            dto.setCourseName(cours.getCourseName());
            dto.setStatus(cours.getStatus());
            dto.setSales(cours.getSales());
            list.add(dto);
        }
        return list;
    }

    @Override
    public void insertCourse(CourseDT courseDTO) {
        Course course = new Course();
        BeanUtils.copyProperties(courseDTO,course);
        Date date = new Date();

        course.setCreateTime(date);
        course.setUpdateTime(date);
        //course.setId(courseDTO.getId());
        courseMapper.insert(course);


        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(courseDTO,teacher);
        teacher.setCourseId(course.getId());
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacherMapper.insert(teacher);
    }

    @Override
    public void updateCourse(CourseDT courseDTO) {
        UpdateWrapper<Course> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",courseDTO.getId());
        Course course = new Course();
        BeanUtils.copyProperties(courseDTO,course);
        course.setUpdateTime(new Date());
        courseMapper.update(course,wrapper);
        UpdateWrapper<Teacher> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("course_id",courseDTO.getId());
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(courseDTO,teacher);
        teacherMapper.update(teacher, updateWrapper);
    }

    @Override
    public CourseDT findCourseById(Integer id) {

        return courseMapper.selectCourseWithTeacher(id);
    }

    @Override
    public int updateCourseStatus(Integer status, Integer courseId) {
        UpdateWrapper<Course> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",courseId);
        Course course = new Course();
        int sta = status;
        if (status == 0){
            sta=1;
        }else {
            sta=0;
        }
        course.setStatus(sta);
        courseMapper.update(course,wrapper);
        Course byId = courseMapper.selectById(courseId);
        return byId.getStatus();
    }


}
