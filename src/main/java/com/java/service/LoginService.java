package com.java.service;

import com.java.pojo.UserInfo;

/**
 * description: 用户登录接口
 * author: ws
 * time: 2019/11/19 16:39
 */

public interface LoginService {
    /**
     * 用户登录
     * @param userName
     * @param userPassword
     * @return
     */
    UserInfo login(String userName, String userPassword);
}
