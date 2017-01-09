package com.dychy.model;

import org.springframework.data.annotation.Id;

/**
 * Created by eclipse on 2017/1/9.
 */
public class PrivilegeIns {
    @Id
    public String id;

    // 资源id
    public String resId;

    // 资源类型id
    public String restypeId;

    // 操作类型id
    public String orId;

    // 系统功能权限
    public int forSystem;

    // 资源类型功能权限
    public int forAny;

    // 对象权限
    public int forObject;

    // 上级对象权限
    public int forPaobj;

    // 描述信息
    public String decInfo;

    public PrivilegeIns(String resId, String restypeId, String orId, int forSystem, int forAny, int forObject, int forPaobj, String decInfo) {
        this.resId = resId;
        this.restypeId = restypeId;
        this.orId = orId;
        this.forSystem = forSystem;
        this.forAny = forAny;
        this.forObject = forObject;
        this.forPaobj = forPaobj;
        this.decInfo = decInfo;
    }

    public PrivilegeIns() {
    }
}
