package com.lagou.courseboot.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lagou.entity.Course;
import com.lagou.entity.CourseDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface CourseMapper extends BaseMapper<Course> {
    @Select("SELECT c.*, t.teacher_name, t.position, t.description FROM course c LEFT JOIN teacher t ON c.id = t.course_id WHERE c.id = #{id}")
    public CourseDTO selectCourseWithTeacher(@Param("id")Integer id);
}
