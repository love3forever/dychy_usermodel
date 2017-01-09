package com.dychy.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by eclipse on 2017/1/9.
 */
public class Department {
    // 标识符
    @Id
    private String id;

    // 部门编号
    private String departmentNum;

    // 部门名称
    private String departmentName;

    // 部门名称缩写
    private String departmentShortName;

    // 部门状态
    private int departmentStatus;

    // 部门领导
    private String departmentLeader;

    // 部门类型
    private int departmentType;

    // 上级部门id
    private String departmentParentID;

    private int departmentLevel;

    // 部门创建时间
    private Date effectiveTime;

    // 部门描述信息
    private String departmentDes;

    // 顺序号
    private int orderIndex;

    // 部门标识图片id
    private int iconID;


    public String getDepartmentNum() {
        return departmentNum;
    }

    public void setDepartmentNum(String departmentNum) {
        this.departmentNum = departmentNum;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentShortName() {
        return departmentShortName;
    }

    public void setDepartmentShortName(String departmentShortName) {
        this.departmentShortName = departmentShortName;
    }

    public int getDepartmentStatus() {
        return departmentStatus;
    }

    public void setDepartmentStatus(int departmentStatus) {
        this.departmentStatus = departmentStatus;
    }

    public String getDepartmentLeader() {
        return departmentLeader;
    }

    public void setDepartmentLeader(String departmentLeader) {
        this.departmentLeader = departmentLeader;
    }

    public int getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(int departmentType) {
        this.departmentType = departmentType;
    }

    public String getDepartmentParentID() {
        return departmentParentID;
    }

    public void setDepartmentParentID(String departmentParentID) {
        this.departmentParentID = departmentParentID;
    }

    public int getDepartmentLevel() {
        return departmentLevel;
    }

    public void setDepartmentLevel(int departmentLevel) {
        this.departmentLevel = departmentLevel;
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getDepartmentDes() {
        return departmentDes;
    }

    public void setDepartmentDes(String departmentDes) {
        this.departmentDes = departmentDes;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public int getIconID() {
        return iconID;
    }

    public void setIconID(int iconID) {
        this.iconID = iconID;
    }

    public Department() {
    }


    public Department(String departmentNum, String departmentName, String departmentShortName, int departmentStatus, String departmentLeader, int departmentType, String departmentParentID, int departmentLevel, Date effectiveTime, String departmentDes, int orderIndex, int iconID) {
        this.departmentNum = departmentNum;
        this.departmentName = departmentName;
        this.departmentShortName = departmentShortName;
        this.departmentStatus = departmentStatus;
        this.departmentLeader = departmentLeader;
        this.departmentType = departmentType;
        this.departmentParentID = departmentParentID;
        this.departmentLevel = departmentLevel;
        this.effectiveTime = effectiveTime;
        this.departmentDes = departmentDes;
        this.orderIndex = orderIndex;
        this.iconID = iconID;
    }
}

