package com.java.controller;

import com.java.pojo.AuthManage;

import java.util.HashMap;
import java.util.Map;

/**
 * description:
 * author: ws
 * time: 2019/11/20 22:59
 */
public class Test {
    public static void main(String[] args) {
        AuthManage authManage = new AuthManage();
        System.out.println(authManage);
        Map<String, Object> map = new HashMap<>();
        map.put("name", "weisong");
        map.put("nation","han");
        System.out.println(map.get("name"));

    }
}
