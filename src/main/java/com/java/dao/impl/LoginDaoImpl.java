package com.java.dao.impl;

import com.java.dao.LoginDao;
import com.java.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description: 描述
 * author: ws
 * time: 2019/11/19 16:44
 */
@Repository("LoginDao")
public class LoginDaoImpl implements LoginDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserInfo login(String userName, String userPassword) {
        StringBuilder sql = new StringBuilder("select * from fp_user where user_state='0' and user_name=? and user_password=?");
        List<UserInfo> userInfos = jdbcTemplate.query(sql.toString(), new Object[]{userName,userPassword}, new BeanPropertyRowMapper<>(UserInfo.class));
        UserInfo userInfo = null;
        //返回的userInfos值为:[UserInfo{uuid='10001', userId='13956585448', userName='xunbaobao', userPassword='123123', userRole='1', userState='0', createTime='2019-11-19 19:01:01', updateTime='null'}]
        //返回的userInfo值为:UserInfo{uuid='10001', userId='13956585448', userName='xunbaobao', userPassword='123123', userRole='1', userState='0', createTime='2019-11-19 19:01:01', updateTime='null'}
        if(userInfos.size()>0){
            //库中有数据，往下执行
            System.out.println("1-userInfos返回值为：" + userInfos);
            userInfo = userInfos.get(0);
        }
        System.out.println("2-userInfo返回值为：" + userInfo);
        return userInfo;
    }
}
