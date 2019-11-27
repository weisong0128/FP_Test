package com.java.service;

import com.java.pojo.AuthManage;
import com.java.util.Page;

import java.util.List;
import java.util.Map;

/**
 * description: 授权管理服务层
 * author: ws
 * time: 2019/11/20 20:22
 */
public interface AuthManageService {

    /**
     * 新增授权信息
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

    /**
     * 修改和删除授权
     * @param parames
     * @return
     */
    int updateAndDelete(Map<String, Object> parames);

    /**
     * 获取所有项目名称
     * @return  返回list列表
     */
    List<Map<String, Object>> getAllPjName();

    /**
     * 获取所有的安装地市
     * @param pjName
     * @return
     */
    List<Map<String, Object>> getAllCities(String pjName);

}
