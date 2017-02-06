package com.dychy.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by eclipse on 2017/1/9.
 */
public class PrivilegeIns implements Comparable<PrivilegeIns> {
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

    public int getForURL() {
        return forURL;
    }

    public void setForURL(int forURL) {
        this.forURL = forURL;
    }

    public int getForData() {
        return forData;
    }

    public void setForData(int forData) {
        this.forData = forData;
    }

    public int getForService() {
        return forService;
    }

    public void setForService(int forService) {
        this.forService = forService;
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
    @NotEmpty(message="资源id不能为空")
    private String resId;

    // 资源类型id
    private String restypeId;

    public int getResType() {
        return resType;
    }

    public void setResType(int resType) {
        this.resType = resType;
    }

    // 资源类型
    private int resType;

    // 操作类型id
    private String orId;

    // 系统功能权限
    private int forURL;

    // 资源类型功能权限
    private int forData;

    // 对象权限
    private int forService;

    // 上级对象权限
    private int forPaobj;

    // 描述信息
    @NotEmpty(message="描述信息不能为空")
    private String decInfo;


    // 创建时间
    @NotEmpty(message="创建时间不能为空")
    private Date createdTime;

    public void setId(String id) {
        this.id = id;
    }

    public boolean isCanRead() {
        return canRead;
    }

    public void setCanRead(boolean canRead) {
        this.canRead = canRead;
    }

    public boolean isCanWrite() {
        return canWrite;
    }

    public void setCanWrite(boolean canWrite) {
        this.canWrite = canWrite;
    }

    public boolean isCanExcute() {
        return canExcute;
    }

    public void setCanExcute(boolean canExcute) {
        this.canExcute = canExcute;
    }

    // 读取权限
    private boolean canRead;

    // 写入权限
    private boolean canWrite;

    // 执行权限
    private boolean canExcute;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    // 用户id
    private String userid;

    // 角色id
    private String roleid;



    public PrivilegeIns(String resId, String restypeId, String orId, int forURL, int forData, int forService, int forPaobj, String decInfo) {
        this.resId = resId;
        this.restypeId = restypeId;
        this.orId = orId;
        this.forURL = forURL;
        this.forData = forData;
        this.forService = forService;
        this.forPaobj = forPaobj;
        this.decInfo = decInfo;
    }

    public PrivilegeIns() {
    }

    public String getId() {
        return id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public int compareTo(PrivilegeIns o) {
        return getCreatedTime().compareTo(o.getCreatedTime());
    }
}
