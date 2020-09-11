package com.zmn.census.admin.controller;

import com.zmn.census.action.api.ConfigService;
import com.zmn.census.api.vo.*;
import com.zmn.census.common.core.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhaozhirong on 2020/5/6.
 */
@RestController
@RequestMapping(value = "/config")
@Api(tags = "配置")
@Slf4j
public class ConfigController {


    @Autowired
    private ConfigService configService;

    @ApiOperation("根据key查询某个配置")
    @GetMapping(value = "/getByKey")
    public CommonResult<ConfigVO> getByKey(String key){
        return CommonResult.success(configService.getByKey(key));
    }


    @ApiOperation("查询所有配置")
    @GetMapping(value = "/list")
    public CommonResult<List<ConfigVO>> findList(){
       return CommonResult.success(configService.findList());
    }

    @ApiOperation("保存配置")
    @PostMapping(value = "/save")
    public CommonResult<ConfigVO> save(ConfigAddVO configAddVO){
        return CommonResult.success(configService.save(configAddVO));
    }

    @ApiOperation("修改配置")
    @PostMapping(value = "/edit")
    public CommonResult edit(ConfigVO configVO){
        configService.edit(configVO);
        return CommonResult.success();
    }

}
