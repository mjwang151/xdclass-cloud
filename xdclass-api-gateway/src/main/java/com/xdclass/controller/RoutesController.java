package com.xdclass.controller;

import com.xdclass.config.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RoutesController
 * @Author mjwang
 * @Date 2021/4/8 17:23
 * @Description RoutesController
 * @Version 1.0
 */
@RestController
public class RoutesController {

    @Autowired
    RouteService routeService;


    @GetMapping("refresh/route")
    public String refresh2(){
        routeService.refresh();
        return "success2";
    }




}
