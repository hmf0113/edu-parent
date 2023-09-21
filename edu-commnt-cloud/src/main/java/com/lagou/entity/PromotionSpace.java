package com.lagou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 广告区域表(PromotionSpace)实体类
 *
 * @author makejava
 * @since 2023-09-06 22:42:26
 */
@Data
@TableName(value = "promotion_space")
public class PromotionSpace implements Serializable {
    private static final long serialVersionUID = 572815006045322019L;
    @TableId(type = IdType.NONE)
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 广告位key
     */
    private String spaceKey;
    
    private Date createTime;
    
    private Date updateTime;
    
    private Integer isDel;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpaceKey() {
        return spaceKey;
    }

    public void setSpaceKey(String spaceKey) {
        this.spaceKey = spaceKey;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

}

