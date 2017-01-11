package com.dychy.model;

import org.springframework.data.annotation.Id;

/**
 * Created by eclipse on 2017/1/9.
 */
public class PrivilegeIns {
    @Id
    private String id;

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getRestypeId() {
        return restypeId;
    }

    public void setRestypeId(String restypeId) {
        this.restypeId = restypeId;
    }

    public String getOrId() {
        return orId;
    }

    public void setOrId(String orId) {
        this.orId = orId;
    }

    public int getForSystem() {
        return forSystem;
    }

    public void setForSystem(int forSystem) {
        this.forSystem = forSystem;
    }

    public int getForAny() {
        return forAny;
    }

    public void setForAny(int forAny) {
        this.forAny = forAny;
    }

    public int getForObject() {
        return forObject;
    }

    public void setForObject(int forObject) {
        this.forObject = forObject;
    }

    public int getForPaobj() {
        return forPaobj;
    }

    public void setForPaobj(int forPaobj) {
        this.forPaobj = forPaobj;
    }

    public String getDecInfo() {
        return decInfo;
    }

    public void setDecInfo(String decInfo) {
        this.decInfo = decInfo;
    }

    // 资源id
    private String resId;

    // 资源类型id
    private String restypeId;

    // 操作类型id
    private String orId;

    // 系统功能权限
    private int forSystem;

    // 资源类型功能权限
    private int forAny;

    // 对象权限
    private int forObject;

    // 上级对象权限
    private int forPaobj;

    // 描述信息
    private String decInfo;

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

    public String getId() {
        return id;
    }
}
