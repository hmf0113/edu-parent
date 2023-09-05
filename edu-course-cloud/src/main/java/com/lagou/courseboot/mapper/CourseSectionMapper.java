package com.lagou.courseboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lagou.entity.CourseLesson;
import com.lagou.entity.CourseSection;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CourseSectionMapper extends BaseMapper<CourseSection> {

   /* @Select("SELECT cs.*,cl.id lessonId,cl.course_id,cl.section_id,cl.theme,cl.duration,cl.create_time,cl.update_time,cl.is_del,cl.order_num,cl.status FROM course_section cs " +
            "LEFT JOIN course_lesson cl ON cs.id = cl.section_id " +
            "WHERE cs.course_id = #{courseId}" +
            "order by cl.order_num")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "course_id",property = "courseId"),
            @Result(column = "section_id",property = "sectionId"),
            @Result(column = "description",property = "description"),
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "update_time",property = "updateTime"),
            @Result(column = "is_del",property = "isDel"),
            @Result(column = "order_num",property = "orderNum"),
            @Result(property = "courseLessons",javaType = CourseLesson.class,
            column = "section_id",@Many)
    })
    public List<CourseSection> findSectionAndLesson(@Param("courseId") Integer courseId);*/
    /**@Select("select * from course_section")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "courseId",column = "course_id"),
            @Result(property = "sectionName",column = "section_name"),
            @Result(property = "description",column = "description"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "updateTime",column = "update_time"),
            @Result(property = "isDel",column = "is_del"),
            @Result(property = "orderNum",column = "order_num"),
            @Result(property = "status",column = "status"),
            @Result(property = "courseLessons",column = "section_id",javaType = CourseLesson.class,
            many = @Many(select = "com.lagou.courseboot.mapper.CourseLessonMapper.findAllLesson"))
            })*/
    public List<CourseSection> findSectionAndLesson(Integer id);
}
