package com.lagou.courseboot.service;

import com.lagou.entity.Course;
import com.lagou.entity.CourseLesson;
import com.lagou.entity.CourseSection;
import com.lagou.entity.CourseSectionDTO;

import java.util.List;

public interface CourseContentService {
    List<CourseSectionDTO> findSectionAndLesson(Integer courseId);
    Course findCourseById(Integer courseId);
    /**
     * 新增章节
     * CourseSection
     */
    void saveSection(CourseSection courseSectionDTO);
    /**
     * 修改章节
     */
    void updateSection(CourseSection courseSectionDTO);
    /**
     * 修改章节状态
     *
     */
    int updateSectionStatus(Integer id,Integer status);

    /**
     * 新增课时信息
     * @param courseLesson
     */
    void saveLesson(CourseLesson courseLesson);
}
