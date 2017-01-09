package com.dychy.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by eclipse on 2017/1/9.
 * 用户权限关联表
 */
public class UserPriRel {
    @Id
    public String id;

    // 用户id
    public String userId;

    // 权限实例id
    public String priInsId;

    // 应用id
    public String appId;

    // 描述信息
    public String desc;

    // 创建时间
    public Date createdTime;

    // 角色id
    public String roleId;

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
}
