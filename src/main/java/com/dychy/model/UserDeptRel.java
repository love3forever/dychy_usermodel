package com.dychy.model;

import org.springframework.data.annotation.Id;

/**
 * Created by eclipse on 2017/1/9.
 * 部门用户关联表
 */
public class UserDeptRel {
    @Id
    public String id;

    // 用户id
    public String userId;

    // 部门id
    public String deptId;

    public UserDeptRel(String userId, String deptId) {
        this.userId = userId;
        this.deptId = deptId;
    }

    public UserDeptRel() {
    }
}
