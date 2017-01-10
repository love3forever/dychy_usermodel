package com.dychy.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by eclipse on 2017/1/5.
 */
public class User {
    @Id
    private String id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    // 登录名
    private String username;

    // 密码
    private String password;

    // 用户名
    private String nickname;

    // 用户编号
    private String userNum;

    // email地址
    private String userEmail;

    // 地址
    private String userAddress;

    // 电话
    private String userPhone;

    // 用户创建时间
    private Date createdTime;

    // 最后一次登录时间
    private Date lastLoginTime;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public int getLoginTimes() {
        return loginTimes;
    }

    public void setLoginTimes(int loginTimes) {
        this.loginTimes = loginTimes;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    // 登录次数
    public int loginTimes;

    // 描述信息
    public String desc;

    public User(String loginName, String password, String userName, String userNum, String userEmail, String userAddress, String userPhone, Date createdTime, Date lastLoginTime, int loginTimes, String desc) {
        this.username = loginName;
        this.password = password;
        this.nickname = userName;
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

    public User(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.userEmail = user.getUserEmail();
    }

//    @Override
//    public String toString() {
//        return String.format("UserLoginName:{} ,UserEmail:{} ,UserName:{}", this.getLoginName(), this.getUserEmail(), this.getUserName());
//    }

    public String getId() {
        return id;
    }
}

