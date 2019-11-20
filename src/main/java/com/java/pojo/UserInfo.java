package com.java.pojo;

import io.swagger.annotations.ApiModelProperty;

/**
 * description: 用户信息类(8个字段)
 * author: ws
 * time: 2019/11/19 16:28
 */

public class UserInfo {
    /**
     * 唯一标识
     */
    private String uuid;
    /**
     * 用户账户（手机号）
     */
    private String userId;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;
    /**
     * 用户密码
     */
    @ApiModelProperty(value = "密码")
    private String userPassword;
    /**
     * 用户角色
     */
    private String userRole;
    /**
     * 用户状态（0 启用   1 禁用   2  删除 ，默认：启用）
     */
    private String userState;
    /**
     * 用户创建时间（不填，自动生成）
     */
    private String createTime;
    /**
     * 用户修改时间（不填，自动生成）
     */
    private String updateTime;


    /**
     * 账号登录状态
     */
    private String loginStatus;

    private String getLoginStatus() {
        return loginStatus;
    }

    private void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }

//判断是否修改过用户名    true 改过用户名   false  没修改
    private String isEdit = "true";

    public String getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit;
    }

    public UserInfo(String uuid, String userId, String userName, String userPassword, String userRole, String userState, String createTime, String updateTime) {
        this.uuid = uuid;
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userRole = userRole;
        this.userState = userState;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public UserInfo() {
    }


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
