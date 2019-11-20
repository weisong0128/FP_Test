package com.java.service.impl;

import com.java.dao.AuthManageDao;
import com.java.pojo.AuthManage;
import com.java.service.AuthManageService;
import com.java.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description:
 * author: ws
 * time: 2019/11/20 20:25
 */
@Service
public class AuthManageServiceImpl implements AuthManageService{
    @Autowired(required = false)
    private AuthManageDao authManageDao;

    /**
     * 新增授权信息
     * @param authManage
     * @return
     */
    @Override
    public String createAuthManage(AuthManage authManage) {
        return authManageDao.createAuthManage(authManage);
    }

    /**
     * 获取所有授权信息（包括条件查询）
     * @param page
     * @param authManage
     * @return
     */
    @Override
    public List<AuthManage> getAllAuthManage(Page page, AuthManage authManage) {
        return null;
    }
}
