package com.lagou.courseboot;

import com.lagou.courseboot.service.CourseContentService;
import com.lagou.entity.CourseSection;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = "com.lagou.courseboot.service")
@SpringBootTest
public class Test1 {
    @Autowired
    private CourseContentService courseContentService;



}
