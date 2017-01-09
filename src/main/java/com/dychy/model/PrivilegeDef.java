package com.dychy.model;

import org.springframework.data.annotation.Id;

/**
 * Created by eclipse on 2017/1/9.
 */
public class PrivilegeDef {
    @Id
    private String id;

    public String getOrId() {
        return orId;
    }

    public void setOrId(String orId) {
        this.orId = orId;
    }

    public String getRtId() {
        return rtId;
    }

    public void setRtId(String rtId) {
        this.rtId = rtId;
    }

    public int getPriCode() {
        return priCode;
    }

    public void setPriCode(int priCode) {
        this.priCode = priCode;
    }

    public String getPriKey() {
        return priKey;
    }

    public void setPriKey(String priKey) {
        this.priKey = priKey;
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

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    // 操作类型id
    private String orId;

    // 操作资源id
    private String rtId;

    // 权限编码
    private int priCode;

    // 权限键值
    private String priKey;

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

    private int orderIndex;

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
