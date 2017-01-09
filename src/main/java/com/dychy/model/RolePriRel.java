package com.dychy.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by eclipse on 2017/1/9.
 * 角色权限关联表
 */
public class RolePriRel {
    @Id
    public String id;

    // 角色id
    public String roleId;

    // 权限实例
    public String priInsId;

    // 应用系统id
    public String appId;

    // 描述信息
    public String desc;

    // 创建时间
    public Date createdTime;


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
