package com.dychy.model;

import org.springframework.data.annotation.Id;

/**
 * Created by eclipse on 2017/1/9.
 * 用户角色关联表
 */
public class UserRoleRel {
    @Id
    public String id;

    // 用户id
    public String userId;

    // 角色id
    public String roleId;

    public UserRoleRel(String userId, String roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public UserRoleRel() {
    }
}
