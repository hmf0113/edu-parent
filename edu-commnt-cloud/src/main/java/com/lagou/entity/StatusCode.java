package com.lagou.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public enum StatusCode {
    /**
     * 成功和失败信息
     */
    SUCCESS(1,"success"),FAIL(0,"fail");
    private int code;
    private String message;

}
