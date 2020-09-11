package com.zmn.census.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhaozhirong on 2020/5/6.
 */
@Controller
@RequestMapping(value = "/")
@Api(tags = "首页")
public class IndexController {


    @Value("${env.name}")
    private String env;

    @ApiOperation("ping")
    @GetMapping(value = "/ping")
    public String ping(){
        return "ping-success";
    }



    @ApiOperation("env")
    @GetMapping(value = "/env")
    public String env(){
        return env;
    }



}
