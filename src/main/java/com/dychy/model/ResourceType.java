package com.dychy.model;

import org.springframework.data.annotation.Id;

/**
 * Created by eclipse on 2017/1/9.
 */
public class ResourceType {
    @Id
    private String id;

    public String getRtName() {
        return rtName;
    }

    public void setRtName(String rtName) {
        this.rtName = rtName;
    }

    public String getRtKey() {
        return rtKey;
    }

    public void setRtKey(String rtKey) {
        this.rtKey = rtKey;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    // 资源类型id
    private String rtName;

    // 资源键值
    private String rtKey;

    // 上级资源id
    private String parentId;

    // 描述信息
    private String desc;

    public ResourceType(String rtName, String rtKey, String parentId, String desc) {
        this.rtName = rtName;
        this.rtKey = rtKey;
        this.parentId = parentId;
        this.desc = desc;
    }

    public ResourceType() {
    }
}
