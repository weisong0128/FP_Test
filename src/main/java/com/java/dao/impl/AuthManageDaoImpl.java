package com.java.dao.impl;

import com.java.dao.AuthManageDao;
import com.java.pojo.AuthManage;
import com.java.util.EntityMapTransUtils;
import com.java.util.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * description: 数据库交互
 * author: ws
 * time: 2019/11/20 20:29
 */
@Repository
public class AuthManageDaoImpl implements AuthManageDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * 新增授权信息
     * @param authManage
     * @return
     */
    @Override
    public String createAuthManage(AuthManage authManage) {
        StringBuilder sql = new StringBuilder("INSERT INTO `fp_auth_management`(`uuid`, `project_name`, `envir_head`, `phone`, `provinces`, `cities`, `address`, `mac`," +
                " `master_ip`, `download_time`, `envir_note`, `sn_file`, `feedback`, `note`, `create_time`, `update_time`) VALUES (:uuid, :projectName, :envirHead, :phone, :provinces, :cities, :address, :mac," +
                " :masterIp, :downloadTime, :envirNote, :snFile, :feedback, :note, :createTime, :updateTime)");

        //指定部分字段值
        authManage.setUuid(UUID.randomUUID().toString().replaceAll("-",""));
        authManage.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        authManage.setUpdateTime(null);

        //EntityMapTransUtils.entityToMap1(authManage)实体类转map
        int count = namedParameterJdbcTemplate.update(sql.toString(), EntityMapTransUtils.entityToMap1(authManage));
        if (count > 0){
            System.out.println("authManage.getUuid()值为" + authManage.getUuid());
            return authManage.getUuid();
        } else {
            return "0";
        }

    }

    /**
     * 获取所有授权信息（包括条件查询）
     * @param page
     * @param authManage
     * @return
     */
    @Override
    public List<AuthManage> getAllAuthManage(Page page, AuthManage authManage) {
        //sql:返回所有数据
        StringBuilder sql = new StringBuilder("SELECT * FROM fp_auth_management where 1=1 and is_available != '1' ");
        Map<String, Object> parames = null;
//        Map<String, Object> map =


        return null;
    }


    /**
     * 封装条件查询方法
     * @param sql
     * @param authManage
     * @return
     */
    private Map<String, Object> concatSql(StringBuilder sql,AuthManage authManage){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> parames = new HashMap<>();
        if (authManage != null){ //如果对象值不为空
            if (StringUtils.isNotBlank(authManage.getProjectName())){ //如果项目名称不为空


            }

        }
        return null;
    }


}
