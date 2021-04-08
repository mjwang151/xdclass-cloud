package com.xdclass.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 旭瑶&小滴课堂 xdclass.net
 * @Author 二当家小D  代码、笔记和技术指导联系我即可
 * @Version 1.0
 **/

@RestController
@RefreshScope
public class TestController {




    @RequestMapping("/test")
    public Object save(){
    	System.out.println("请求进来了。！");
    	return "test";

    }







}
