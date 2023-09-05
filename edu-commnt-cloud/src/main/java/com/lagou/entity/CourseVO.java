package com.lagou.entity;

import lombok.Data;

@Data
public class CourseVO {
    private Integer currentPage;
    private Integer pageSize;
    private String courseName;
    private  Integer status;
}
