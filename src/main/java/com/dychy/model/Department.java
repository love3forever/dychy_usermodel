package com.dychy.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by eclipse on 2017/1/9.
 */
public class Department {
    // 标识符
    @Id
    public String id;

    // 部门编号
    public String departmentNum;

    // 部门名称
    public String departmentName;

    // 部门名称缩写
    public String departmentShortName;

    // 部门状态
    public int departmentStatus;

    // 部门领导
    public String departmentLeader;

    // 部门类型
    public int departmentType;

    // 上级部门id
    public String departmentParentID;

    public int departmentLevel;

    // 部门创建时间
    public Date effectiveTime;

    // 部门描述信息
    public String departmentDes;

    // 顺序号
    public int orderIndex;

    // 部门标识图片id
    public int iconID;


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

