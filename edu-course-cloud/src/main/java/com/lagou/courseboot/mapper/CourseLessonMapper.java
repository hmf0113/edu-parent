package com.lagou.courseboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lagou.entity.CourseLesson;
import com.lagou.entity.CourseSection;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CourseLessonMapper extends BaseMapper<CourseLesson> {
  /*  @Select("SELECT cs.*,cl.id lessonId,cl.course_id,cl.section_id,cl.theme,cl.duration,cl.create_time,cl.update_time,cl.is_del,cl.order_num,cl.status FROM course_section cs " +
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

    /**
     * 实际上
     * @param courseId 是多少
     * @return 十大
     */
    /**@Select("SELECT cs.*,cl.id lessonId,cl.course_id,cl.section_id,cl.theme,cl.duration,cl.create_time,cl.update_time,cl.is_del,cl.order_num,cl.status FROM course_section cs \" +\n" +
            "            \"LEFT JOIN course_lesson cl ON cs.id = cl.section_id \" +\n" +
            "            \"WHERE cs.course_id = #{courseId}\" +\n" +
            "            \"order by cl.order_num")*/
    public List<CourseLesson> findAllLesson(@Param("courseId")Integer courseId);
}
