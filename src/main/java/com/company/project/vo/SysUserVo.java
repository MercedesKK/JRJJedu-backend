package com.company.project.vo;

import java.io.Serializable;
import java.util.List;

public class SysUserVo implements Serializable {

    private Long userId;

    private String phone;

    private String email;

    private String token;

    private String roleId;

    private String channel;

    private String userName;

    //token过期时间
    private Long tokenExpireTime;

    private String password;

    //权限集合
    private List<Object> sysMenuList;

    public SysUserVo() {
        super();
    }

    public SysUserVo(Long userId, String phone, String email, String token, String roleId,
                     String channel, String userName,Long tokenExpireTime,String password,List<Object> sysMenuList) {
        this.userId = userId;
        this.phone = phone;
        this.email = email;
        this.token = token;
        this.roleId = roleId;
        this.channel = channel;
        this.userName = userName;
        this.tokenExpireTime = tokenExpireTime;
        this.password = password;
        this.sysMenuList = sysMenuList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Object> getSysMenuList() {
        return sysMenuList;
    }

    public void setSysMenuList(List<Object> sysMenuList) {
        this.sysMenuList = sysMenuList;
    }

    public Long getTokenExpireTime() {
        return tokenExpireTime;
    }

    public void setTokenExpireTime(Long tokenExpireTime) {
        this.tokenExpireTime = tokenExpireTime;
    }
}
