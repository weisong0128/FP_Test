package com.java.controller;

import com.java.pojo.AuthManage;
import com.java.service.AuthManageService;
import com.java.util.Page;
import com.java.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * description:授权管理
 * author: ws
 * time: 2019/11/20 21:06
 */
@Api(value = "授权管理界面", description = "授权管理")
@Controller
@ResponseBody
@RequestMapping("/manage/")

public class AuthManageController {
    //调服务层
    @Autowired
    private AuthManageService authManageService;

    @ApiOperation(value = "新增授权信息", notes = "新增授权信息")
    @RequestMapping(value = "createAuthManage", method = RequestMethod.POST)
    public Response createAuthManage(AuthManage authManage){
        //ok里的参数是daoimpl层对应方法的返回值!然后将这个返回值作为ok()方法的实参！再返回ok()方法.
        return Response.ok(authManageService.createAuthManage(authManage));
    }

    @ApiOperation(value = "获取授权管理列表", notes = "获取授权管理列表")
    @RequestMapping(value = "getAllAuthManage", method = RequestMethod.GET)
    public Response getAllAuthManage(Page page, String projectName, String cities, String envirNote, String feedback, String keyWord, String sortField, String startTime, String endTime){
    //方法中传参为前端页面传递的条件查询参数值
        AuthManage authManage = new AuthManage(projectName,cities,envirNote,feedback,keyWord,sortField,startTime,endTime);
        return Response.ok(authManageService.getAllAuthManage(page,authManage));    //data类型为List
    }

}
