package com.dychy.model;

import org.springframework.data.annotation.Id;

/**
 * Created by eclipse on 2017/1/9.
 */
public class DepartmentRole {
    @Id
    public String id;

    // 角色id
    public String roleId;

    // 部门id
    public String departmentId;

    public DepartmentRole(String roleId, String departmentId) {
        this.roleId = roleId;
        this.departmentId = departmentId;
    }

    public DepartmentRole() {
    }
}
