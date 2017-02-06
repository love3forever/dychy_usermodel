package com.dychy.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by eclipse on 2017/2/6.
 */
public class Resource {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getResType() {
        return resType;
    }

    public void setResType(int resType) {
        this.resType = resType;
    }

    public String getResURL() {
        return resURL;
    }

    public void setResURL(String resURL) {
        this.resURL = resURL;
    }

    public String getResDesc() {
        return resDesc;
    }

    public void setResDesc(String resDesc) {
        this.resDesc = resDesc;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    @Id
    private String id;

    @NotEmpty(message="资源类型不能为空")
    private int resType;

    private String resURL;

    private String resDesc;

    private Date createdTime;

    private String ownerId;

    private String depId;

    public Resource(int resType, String resURL, String resDesc, Date createdTime, String ownerId, String depId) {
        this.resType = resType;
        this.resURL = resURL;
        this.resDesc = resDesc;
        this.createdTime = createdTime;
        this.ownerId = ownerId;
        this.depId = depId;
    }

    public Resource() {
    }
}
