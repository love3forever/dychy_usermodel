package com.dychy.model;

import org.springframework.data.annotation.Id;

/**
 * Created by eclipse on 2017/1/9.
 */
public class ResourceType {
    @Id
    public String id;

    // 资源类型id
    public String rtName;

    // 资源键值
    public String rtKey;

    // 上级资源id
    public String parentId;

    // 描述信息
    public String desc;

    public ResourceType(String rtName, String rtKey, String parentId, String desc) {
        this.rtName = rtName;
        this.rtKey = rtKey;
        this.parentId = parentId;
        this.desc = desc;
    }

    public ResourceType() {
    }
}
