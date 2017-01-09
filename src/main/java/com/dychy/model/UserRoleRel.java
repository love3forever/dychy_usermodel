package com.dychy.model;

import org.springframework.data.annotation.Id;

/**
 * Created by eclipse on 2017/1/9.
 * 用户角色关联表
 */
public class UserRoleRel {
    @Id
    private String id;

    // 用户id
    private String userId;

    // 角色id
    private String roleId;

    public UserRoleRel(String userId, String roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public UserRoleRel() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
