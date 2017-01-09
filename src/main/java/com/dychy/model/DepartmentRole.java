package com.dychy.model;

import org.springframework.data.annotation.Id;

/**
 * Created by eclipse on 2017/1/9.
 */
public class DepartmentRole {
    @Id
    private String id;

    // 角色id
    private String roleId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    // 部门id
    private String departmentId;

    public DepartmentRole(String roleId, String departmentId) {
        this.roleId = roleId;
        this.departmentId = departmentId;
    }

    public DepartmentRole() {
    }
}
