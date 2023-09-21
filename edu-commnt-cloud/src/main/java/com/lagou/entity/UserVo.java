package com.lagou.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户表(User)实体类
 *
 * @author makejava
 * @since 2023-09-08 15:38:05
 */
@Data
public class UserVo{

    private int currentPage;
    //当前页falseinteger(int32)
    private int pageSize;
    //每页显示条数falseinteger(int32)
    private String username;
    //用户名falseString输入手机号即可
    private Date startCreateTime;
    //注册起始时间false
    private Date DateendCreateTime;
}

