package com.dychy.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by eclipse on 2017/1/9.
 */
public class Role {
    @Id
    public String id;

    // 角色名
    public String roleName;

    // 用户id
    public String userId;

    // 创建时间
    public Date createdTime;

    // 更新时间
    public Date updateTime;

    // 描述信息
    public String desc;

}
