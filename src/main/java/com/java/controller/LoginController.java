package com.java.controller;

import com.java.pojo.UserInfo;
import com.java.service.LoginService;
import com.java.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * description: 描述
 * author: ws
 * time: 2019/11/19 16:27
 */
@Controller
@ResponseBody

public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping("/fplogin")
    public UserInfo login(String userName, String userPassword){
        UserInfo userInfo1 = loginService.login(userName, userPassword);
//        System.out.println("=========================");
        if (userInfo1!=null){
            return userInfo1;   //传给界面的事userInfo1对象的json格式数据
        }
        return null;
    }

    @RequestMapping(value = "/fplogin2", method = RequestMethod.GET)
    public Response login(UserInfo userInfo, HttpServletRequest request){
        UserInfo userInfo1 = loginService.login(userInfo.getUserName(), userInfo.getUserPassword());
        if(userInfo1 != null){
            Map map = new HashMap();
            HttpSession session = request.getSession();
            session.setAttribute(userInfo1.getUuid(),userInfo1);
            map.put("uuid", userInfo1.getUuid());   //用户唯一识别号
            map.put("userRole", userInfo1.getUserRole());
            map.put("userName", userInfo1.getUserName());
            return Response.ok(map);
        }
        return Response.error("用户不存在或用户名/密码错误！");
    }




}
