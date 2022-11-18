package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Date:2022/7/7
 * Author:ybc
 * Description:
 */
@Controller
public class HelloController {

    @RequestMapping("/")
    public String protal(){
        //将逻辑视图返回，即跳转到index页面，注意配置文件
        return "index";
    }

    @RequestMapping("/hello")
    public String hello(){
        return "success";
    }

}
