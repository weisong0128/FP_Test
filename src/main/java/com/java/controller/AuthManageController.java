package com.java.controller;

import com.java.pojo.AuthManage;
import com.java.service.AuthManageService;
import com.java.util.EntityMapTransUtils;
import com.java.util.Page;
import com.java.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Response createAuthManage(AuthManage authManage) {
        //ok里的参数是daoimpl层对应方法的返回值!然后将这个返回值作为ok()方法的实参！再返回ok()方法.
        return Response.ok(authManageService.createAuthManage(authManage));
    }

    @ApiOperation(value = "获取授权管理列表", notes = "获取授权管理列表")
    @RequestMapping(value = "getAllAuthManage", method = RequestMethod.GET)
    public Response getAllAuthManage(Page page, String projectName, String cities, String envirNote, String feedback, String keyWord, String sortField, String startTime, String endTime){
    //方法中传参为前端页面传递的条件查询参数值,为dao层sql查询传递参数值
        AuthManage authManage = new AuthManage(projectName,cities,envirNote,feedback,keyWord,sortField,startTime,endTime);
        return Response.ok(authManageService.getAllAuthManage(page,authManage));    //data类型为List,里面为10条数据
    }

    @ApiOperation(value = "修改授权信息", notes = "授权管理实体")
    @RequestMapping(value = "updateAuthManage", method = RequestMethod.POST)
    public Response updateAuthManage(AuthManage authManage) {
        return Response.ok(authManageService.updateAndDelete(EntityMapTransUtils.entityToMap1(authManage)));
    }

    @ApiOperation(value = "删除授权信息", notes = "参数说明：传个字符串，如：'430105f0ba634519982caec19f3d29a4','bf49eaf70bd3408690dd62833979b68c'")
    @RequestMapping(value = "deleteAuthManage", method = RequestMethod.GET)
    public Response deleteAuthManage(String uuids) {
        List<String> uuid = EntityMapTransUtils.StringToList(uuids);    //将字符串转list集合（因为可能传多个uuid，以逗号分隔）
        Map<String, Object> map = new HashMap<>();
        map.put("uuids", uuid); //将uuid值指定具体key: uuids
        return Response.ok(authManageService.updateAndDelete(map));
    }

    @ApiOperation(value = "获取项目名称或根据项目名称获取安装地市", notes = "")
    @RequestMapping(value = "getAllPjNameAndCities", method = RequestMethod.GET)
    public Response getAllPjNameAndCities(String pjName) {
        List<Map<String, Object>> allCities = null;
        List<String> cities = null;
        List list = new ArrayList();
        List<Map<String, Object>> provinces = authManageService.getAllPjName(); //得到去重后的项目名list列表
        if (provinces.size() > 0) {
            //遍历所有项目
            for (int i = 0; i < provinces.size() ; i++){
                cities = new ArrayList<>();
                //对list列表中所有项目（Map类型）进行遍历
                for (Object value : provinces.get(i).values()){ //value:项目名
                    allCities = authManageService.getAllCities((String)value);  //返回根据指定项目名称查到的所在地市list列表
                    //遍历 List<Map<String, Object>> allCities值
                    for (int j = 0; j < allCities.size(); j++) {
                        if (allCities.size() > 0) {
                            for (Object city : allCities.get(j).values()) {
                                cities.add((String) city);
                            }
                        }
                    }
                    Map map = new HashMap();
                    map.put("pjName", value);   //遍历到的项目名值
                    map.put("pjLocationList", cities);  //一个项目所在的地市list列表
                    list.add(map);      //将项目名及对应的地市列表作为一个Map放到List中
                }
            }
        }
        return Response.ok(list);
    }
}
