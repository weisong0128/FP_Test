package com.java.controller;

import com.java.pojo.UserInfo;
import com.java.service.LoginService;
import com.java.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * description: 登录界面
 * author: ws
 * time: 2019/11/19 16:27
 */
@Api(value = "FP登录", description = "用户登录")
@Controller
@ResponseBody
@RequestMapping("/fplogin/")

public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "fplogin" , method = RequestMethod.GET)
    public UserInfo login(String userName, String userPassword){
        UserInfo userInfo1 = loginService.login(userName, userPassword);
//        System.out.println("=========================");
        if (userInfo1!=null){
            return userInfo1;   //传给界面的事userInfo1对象的json格式数据
        }
        return null;
    }

    @ApiOperation(value = "登录方法", notes = "用户登录")
//    @ApiParam(value = "userName", required = true)
    @RequestMapping(value = "/fplogin2", method = RequestMethod.POST)
    public Response login(UserInfo userInfo, HttpServletRequest request){
        UserInfo userInfo1 = loginService.login(userInfo.getUserName(), userInfo.getUserPassword());
        if(userInfo1 != null){
            Map map = new HashMap();
            HttpSession session = request.getSession(); //获取Session对象
            session.setAttribute(userInfo1.getUuid(),userInfo1);
            Object obj = session.getAttribute(userInfo1.getUuid());
            System.out.println("controller层：obj(userInfo1)对象为" + obj);
            map.put("uuid", userInfo1.getUuid());   //用户唯一识别号
            map.put("userRole", userInfo1.getUserRole());
            map.put("userName", userInfo1.getUserName());
            return Response.ok(map);
        }
        return Response.error("用户不存在或用户名/密码错误！");
    }


}
