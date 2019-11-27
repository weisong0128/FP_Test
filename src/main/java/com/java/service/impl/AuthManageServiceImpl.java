package com.java.service.impl;

import com.java.dao.AuthManageDao;
import com.java.pojo.AuthManage;
import com.java.service.AuthManageService;
import com.java.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
        return authManageDao.getAllAuthManage(page, authManage);
    }

    /**
     * 修改和删除授权
     * @param parames
     * @return
     */
    @Override
    public int updateAndDelete(Map<String, Object> parames) {
        return authManageDao.updateAndDelete(parames);
    }

    /**
     * 获取所有项目名称
     * @return
     */
    @Override
    public List<Map<String, Object>> getAllPjName() {
        return authManageDao.getAllPjName();
    }

    /**
     * 获取所有的安装地市
     * @param pjName
     * @return
     */
    @Override
    public List<Map<String, Object>> getAllCities(String pjName) {
        return authManageDao.getAllCities(pjName);
    }
}
