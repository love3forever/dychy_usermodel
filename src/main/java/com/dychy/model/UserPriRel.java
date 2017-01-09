package com.dychy.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by eclipse on 2017/1/9.
 * 用户权限关联表
 */
public class UserPriRel {
    @Id
    private String id;

    // 用户id
    private String userId;

    // 权限实例id
    private String priInsId;

    // 应用id
    private String appId;

    // 描述信息
    private String desc;

    // 创建时间
    private Date createdTime;

    // 角色id
    private String roleId;

    public UserPriRel(String userId, String priInsId, String appId, String desc, Date createdTime, String roleId) {
        this.userId = userId;
        this.priInsId = priInsId;
        this.appId = appId;
        this.desc = desc;
        this.createdTime = createdTime;
        this.roleId = roleId;
    }

    public UserPriRel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPriInsId() {
        return priInsId;
    }

    public void setPriInsId(String priInsId) {
        this.priInsId = priInsId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
