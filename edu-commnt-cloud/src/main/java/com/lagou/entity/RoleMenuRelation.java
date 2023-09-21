package com.lagou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "Role_menu_relation")
public class RoleMenuRelation {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer menuId;
    private Integer roleId;
    private Date createdTime;
    private Date updatedTime;
    private String createdBy;

    private String updatedBy;


}
