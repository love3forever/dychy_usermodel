package com.dychy.model;

import org.springframework.data.annotation.Id;

/**
 * Created by eclipse on 2017/1/9.
 */
public class PrivilegeDef {
    @Id
    public String id;

    // 操作类型id
    public String orId;

    // 操作资源id
    public String rtId;

    // 权限编码
    public int priCode;

    // 权限键值
    public String priKey;

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

    public int orderIndex;

    public PrivilegeDef(String orId, String rtId, int priCode, String priKey, int forSystem, int forAny, int forObject, int forPaobj, String decInfo, int orderIndex) {
        this.orId = orId;
        this.rtId = rtId;
        this.priCode = priCode;
        this.priKey = priKey;
        this.forSystem = forSystem;
        this.forAny = forAny;
        this.forObject = forObject;
        this.forPaobj = forPaobj;
        this.decInfo = decInfo;
        this.orderIndex = orderIndex;
    }
}
