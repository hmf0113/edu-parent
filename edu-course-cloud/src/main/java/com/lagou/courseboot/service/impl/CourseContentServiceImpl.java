package com.lagou.courseboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lagou.courseboot.mapper.CourseLessonMapper;
import com.lagou.courseboot.mapper.CourseMapper;
import com.lagou.courseboot.mapper.CourseSectionMapper;
import com.lagou.courseboot.service.CourseContentService;
import com.lagou.entity.Course;
import com.lagou.entity.CourseLesson;
import com.lagou.entity.CourseSection;
import com.lagou.entity.CourseSectionDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CourseContentServiceImpl implements CourseContentService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CourseLessonMapper courseLessonMapper;
    @Autowired
    private CourseSectionMapper courseSectionMapper;

    @Override
    public List<CourseSectionDTO> findSectionAndLesson(Integer courseId) {
        List<CourseSection> initSection = getInitSection(courseId);
        List<CourseSectionDTO> arrayList = new ArrayList<>();
        for (CourseSection courseSection : initSection) {
            CourseSectionDTO sectionDTO = new CourseSectionDTO();
            BeanUtils.copyProperties(courseSection,sectionDTO);
            System.out.println(sectionDTO+"000");
            arrayList.add(sectionDTO);
            setCourseLesson(sectionDTO);
        }

        return arrayList;
    }

    /**
     * 根据课程ID查询
     * @param courseId
     * @return
     */

    @Override
    public Course findCourseById(Integer courseId) {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();

        wrapper.select("id","course_name");
        wrapper.eq("id",courseId);
        Course course = courseMapper.selectOne(wrapper);
        System.out.println(course);
        return course;
    }

    @Override
    public void saveSection(CourseSection courseSection) {
        courseSection.setCreateTime(new Date());
        courseSection.setUpdateTime(new Date());
        courseSectionMapper.insert(courseSection);
    }

    @Override
    public void updateSection(CourseSection courseSection) {
        UpdateWrapper<CourseSection> wq = new UpdateWrapper<>();
        wq.eq("id",courseSection.getId());
        wq.set("update_time",new Date());
        courseSectionMapper.update(courseSection,wq);
    }

    @Override
    public int updateSectionStatus(Integer id, Integer status) {
        UpdateWrapper<CourseSection> uw = new UpdateWrapper<>();
        uw.eq("id",id);
        uw.set("status",status);
        uw.set("update_time",System.nanoTime());
        courseSectionMapper.update(null,uw);
        CourseSection courseSection = courseSectionMapper.selectById(id);
        return courseSection.getStatus();
    }

    @Override
    public void saveLesson(CourseLesson courseLesson) {
        courseLesson.setCreateTime(new Date());
        courseLesson.setUpdateTime(new Date());
        courseLessonMapper.insert(courseLesson);
    }

    private List<CourseSection> getInitSection(Integer courseId){
        QueryWrapper<CourseSection> qw = new QueryWrapper<>();
        qw.eq("course_id",courseId);
        List<CourseSection> courseSections = courseSectionMapper.selectList(qw);
        return courseSections;
    }
    private void setCourseLesson(CourseSectionDTO courseSectionDTO){
        QueryWrapper<CourseLesson> qw = new QueryWrapper<>();
        qw.eq("section_id",courseSectionDTO.getId());
        List<CourseLesson> courseLessons = this.courseLessonMapper.selectList(qw);
        courseSectionDTO.setCourseLessons(courseLessons);
    }
}
