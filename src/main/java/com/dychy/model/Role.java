package com.dychy.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by eclipse on 2017/1/9.
 */
public class Role {
    @Id
    private String id;

    // 角色名
    private String roleName;

    // 用户id
    private String userId;

    // 创建时间
    private Date createdTime;

    // 更新时间
    private Date updateTime;

    // 描述信息
    private String desc;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Role(String roleName, String userId, Date createdTime, Date updateTime, String desc) {
        this.roleName = roleName;
        this.userId = userId;
        this.createdTime = createdTime;
        this.updateTime = updateTime;
        this.desc = desc;
    }
}
