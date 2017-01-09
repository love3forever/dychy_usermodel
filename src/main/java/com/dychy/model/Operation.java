package com.dychy.model;

import org.springframework.data.annotation.Id;

/**
 * Created by eclipse on 2017/1/9.
 */
public class Operation {
    @Id
    public String id;

    // 操作名称
    public String orName;

    // 操作键值
    public String orKey;

    // 描述信息
    public String descInfo;

    public int orderIndex;

    public Operation(String orName, String orKey, String descInfo, int orderIndex) {
        this.orName = orName;
        this.orKey = orKey;
        this.descInfo = descInfo;
        this.orderIndex = orderIndex;
    }
}
