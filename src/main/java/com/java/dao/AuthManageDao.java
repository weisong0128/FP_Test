package com.java.dao;

import com.java.pojo.AuthManage;
import com.java.util.Page;

import java.util.List;

/**
 * description: 授权管理dao层
 * author: ws
 * time: 2019/11/20 20:27
 */
public interface AuthManageDao {

    /**
     * 创建授权管理
     * @param authManage
     * @return
     */
    String createAuthManage(AuthManage authManage);

    /**
     * 获取所有授权信息（包括条件查询）
     * @param page
     * @param authManage
     * @return
     */
    List<AuthManage> getAllAuthManage(Page page, AuthManage authManage);
}
