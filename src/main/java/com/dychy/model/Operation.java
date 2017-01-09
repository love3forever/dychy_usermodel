package com.dychy.model;

import org.springframework.data.annotation.Id;

/**
 * Created by eclipse on 2017/1/9.
 */
public class Operation {
    @Id
    private String id;

    // 操作名称
    private String orName;

    // 操作键值
    private String orKey;

    // 描述信息
    private String descInfo;

    public String getOrName() {
        return orName;
    }

    public void setOrName(String orName) {
        this.orName = orName;
    }

    public String getOrKey() {
        return orKey;
    }

    public void setOrKey(String orKey) {
        this.orKey = orKey;
    }

    public String getDescInfo() {
        return descInfo;
    }

    public void setDescInfo(String descInfo) {
        this.descInfo = descInfo;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    private int orderIndex;

    public Operation(String orName, String orKey, String descInfo, int orderIndex) {
        this.orName = orName;
        this.orKey = orKey;
        this.descInfo = descInfo;
        this.orderIndex = orderIndex;
    }
}
