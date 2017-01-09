package com.dychy.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by eclipse on 2017/1/5.
 */
public class User {
    @Id
    public String id;

    // 登录名
    public String loginName;

    // 密码
    public String password;

    // 用户名
    public String userName;

    // 用户编号
    public String userNum;

    // email地址
    public String userEmail;

    // 地址
    public String userAddress;

    // 电话
    public String userPhone;

    // 用户创建时间
    public Date createdTime;

    // 最后一次登录时间
    public Date lastLoginTime;

    // 登录次数
    public int loginTimes;

    // 描述信息
    public String desc;

    public User(String loginName, String password, String userName, String userNum, String userEmail, String userAddress, String userPhone, Date createdTime, Date lastLoginTime, int loginTimes, String desc) {
        this.loginName = loginName;
        this.password = password;
        this.userName = userName;
        this.userNum = userNum;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.createdTime = createdTime;
        this.lastLoginTime = lastLoginTime;
        this.loginTimes = loginTimes;
        this.desc = desc;
    }

    public User() {
    }
}

