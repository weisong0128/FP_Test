package com.java.dao;

import com.java.pojo.UserInfo;

/**
 * description: 描述
 * author: ws
 * time: 2019/11/19 16:43
 */

public interface LoginDao {
    /**
     * 用户登录
     * @param userName
     * @param userPassword
     * @return
     */
    UserInfo login(String userName, String userPassword);
}
