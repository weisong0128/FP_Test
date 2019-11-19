package com.java.service.impl;

import com.java.dao.LoginDao;
import com.java.pojo.UserInfo;
import com.java.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description: 描述
 * author: ws
 * time: 2019/11/19 16:41
 */
@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    private LoginDao loginDao;
    @Override
    public UserInfo login(String userName, String userPassword) {
        UserInfo userInfo = loginDao.login(userName, userPassword);
        return userInfo;
    }
}
