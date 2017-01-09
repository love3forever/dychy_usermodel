package com.dychy.model;

import org.springframework.data.annotation.Id;

/**
 * Created by eclipse on 2017/1/9.
 * 部门用户关联表
 */
public class UserDeptRel {
    @Id
    private String id;

    // 用户id
    private String userId;

    // 部门id
    private String deptId;

    public UserDeptRel(String userId, String deptId) {
        this.userId = userId;
        this.deptId = deptId;
    }

    public UserDeptRel() {
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

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
}
