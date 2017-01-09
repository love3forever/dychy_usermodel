package com.dychy.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by eclipse on 2017/1/9.
 * 角色权限关联表
 */
public class RolePriRel {
    @Id
    private String id;

    // 角色id
    private String roleId;

    // 权限实例
    private String priInsId;

    // 应用系统id
    private String appId;

    // 描述信息
    private String desc;

    // 创建时间
    private Date createdTime;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
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

    public RolePriRel(String roleId, String priInsId, String appId, String desc, Date createdTime) {
        this.roleId = roleId;
        this.priInsId = priInsId;
        this.appId = appId;
        this.desc = desc;
        this.createdTime = createdTime;
    }

    public RolePriRel() {
    }
}
