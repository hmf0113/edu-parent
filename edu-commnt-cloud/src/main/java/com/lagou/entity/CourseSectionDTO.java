package com.lagou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
public class CourseSectionDTO {
        /**
         * id
         */
        @TableId(type = IdType.AUTO)
        private Integer id;

        /**
         * 课程id
         */
        @TableField("course_id")
        private Integer courseId;

        /**
         * 章节名
         */
        @TableField("section_name")
        private String sectionName;

        /**
         * 章节描述
         */
        private String description;

        /**
         * 记录创建时间
         */
        private Date createTime;

        /**
         * 更新时间
         */
        private Date updateTime;

        /**
         * 是否删除
         */
        private Boolean isDel;

        /**
         * 排序字段
         */
        private Integer orderNum;

        /**
         * 状态，0:隐藏；1：待更新；2：已发布
         */
        private Integer status;

        //    @TableField(exist = false)
        private List<CourseLesson> courseLessons;
        private static final long serialVersionUID = 1L;

}
